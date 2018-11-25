package dgcplg.tradeshift.trianglechallenge.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dgcplg.tradeshift.trianglechallenge.TriangleScanner;

public class DefaultScannerProvider implements ScannerProvider {

	private static final String CURRENT_DIR = ".";
	private static final String PKG_PREFIX = "dgcplg/tradeshift/trianglechallenge";
	private static final String CLASS_EXT = ".class";

	@Override
	public List<TriangleScanner> getTriangleScanners() {
		List<Class<? extends TriangleScanner>> triangleScannerClasses = new ArrayList<>();
		List<TriangleScanner> triangleScanners = new ArrayList<>();
		File currentDir = new File(CURRENT_DIR);
		Path thisJarFilePath = null;
		try {
			thisJarFilePath = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			System.err.println("Error while converting this jar file's path to URI: " + e.getMessage());
			return Collections.emptyList();
		}
		String thisJarFilename = thisJarFilePath.toFile().getName();
		File[] files = currentDir.listFiles((File f, String fname) -> !f.isFile() && f.canRead() && !f.isHidden()
				&& !fname.equals(thisJarFilename) && fname.endsWith(".jar"));

		URL[] fileURLs = new URL[files.length];
		for (int i=0; i<files.length; i++) {
			try {
				fileURLs[i] = files[i].toURI().toURL();
			} catch (MalformedURLException e) {
				System.err.println("ERROR while forming URL from files!!! "+e.getMessage());
			}
		}
		ClassLoader classLoader = URLClassLoader.newInstance(fileURLs, getClass().getClassLoader());

		for (File file : files) {
			String filename = file.getName();
			System.out.println("Scanning filename \""+filename+"\"");
			JarFile jarFile = null;
			try {
				jarFile = new JarFile(filename);
			} catch (IOException e) {
				System.err.println("I/O error for file "+filename+": "+e.getMessage());
			}
			Enumeration<JarEntry> jarEntries = jarFile.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();
				String jarEntryName = jarEntry.getName();
				System.out.println("\tjar entry: "+jarEntryName);
				if (jarEntry.getName().startsWith(PKG_PREFIX) && jarEntry.getName().endsWith(CLASS_EXT)) {
					String jarClassName = jarEntryName.replaceAll("/", "\\.").substring(0,
							jarEntryName.length() - CLASS_EXT.length());
					System.out.println("\tjar class name: " + jarClassName);
					Class<?> jarClass = null;
					try {
						jarClass = classLoader.loadClass(jarClassName);
						System.out.println("\tjar class loaded: " + jarClass.getName());
					} catch (ClassNotFoundException e) {
						System.err.println("Could not find class: "+e.getMessage());
					}
					if (TriangleScanner.class.isAssignableFrom(jarClass)) {
						System.out.println("\tFound valid TriangleScanner subclass " + jarClass.getName());
						triangleScannerClasses.add(jarClass.asSubclass(TriangleScanner.class));
					}
				}
			}
		}
		for (Class<? extends TriangleScanner> triangleScannerClass : triangleScannerClasses) {
			try {
				triangleScanners.add(triangleScannerClass.newInstance());
			} catch (InstantiationException e) {
				System.err.println("Error while instantiating TriangleScanner class: " + e.getMessage());
				continue;
			} catch (IllegalAccessException e) {
				System.err.println("Error while accessing TriangleScanner class: " + e.getMessage());
				continue;
			}
		}
		return triangleScanners;
	}
}