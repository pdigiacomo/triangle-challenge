package dgcplg.tradeshift.trianglechallenge.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

import dgcplg.tradeshift.trianglechallenge.TriangleScanner;

public class DefaultScannerProvider implements ScannerProvider {

	private static final String CURRENT_DIR = ".";
	private static final String PKG_PREFIX = "dgcplg/tradeshift/trianglechallenge";
	private static final String CLASS_EXT = ".class";
	
	@Override
	public List<TriangleScanner> getTriangleScanners() {
//		System.out.println("\nSearching for scanner plugins..");
		List<TriangleScanner> triangleScanners = new ArrayList<>();
		List<Class<? extends TriangleScanner>> triangleScannerClasses = new ArrayList<>();
		List<File> jarFiles = null;
		try {
			jarFiles = findJarFiles(CURRENT_DIR);
		} catch (URISyntaxException e) {
//			System.err.println("Error while getting this jar's file name: "+e.getMessage()+"\nPlugin search aborted.\n");
			return triangleScanners;
		}
		try (URLClassLoader classLoader = getJarClassLoader(jarFiles)) {
			for (File jarFile: jarFiles) {
				triangleScannerClasses = loadClassFiles(jarFile, classLoader);
			}
		} catch (IOException e) {
//			System.err.println("I/O error on jar files: "+e.getMessage()+"\nplugin search aborted.\n");
			return triangleScanners;
		}
		for (Class<? extends TriangleScanner> triangleScannerClass : triangleScannerClasses) {
			try {
//				System.out.format("Found scanner plugin: %s ", triangleScannerClass.getSimpleName());
				triangleScanners.add(triangleScannerClass.newInstance());
//				System.out.format("[loaded]\n");
			} catch (InstantiationException | IllegalAccessException e) {
//				System.out.format("[error]\n");
				continue;
			}
		}
//		System.out.format("%d plugins loaded.\n", triangleScanners.size());
		return triangleScanners;
	}
	
	private List<File> findJarFiles(String dirpath) throws URISyntaxException {
		File searchdir = new File(dirpath);
		if (dirpath == null || !searchdir.isDirectory()) {
			throw new IllegalArgumentException("Argument must be a valid directory path");
		}
		String thisJarFilename = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toFile().getName();
		File[] files = searchdir.listFiles((File f, String fname) -> !f.isFile() && f.canRead() && !f.isHidden()
				&& !fname.equals(thisJarFilename) && fname.endsWith(".jar"));
		return Arrays.asList(files);
	}

	private URLClassLoader getJarClassLoader(List<File> jarFiles) throws MalformedURLException {
		URL[] fileURLs = new URL[jarFiles.size()];
		for (int i=0; i<jarFiles.size(); i++) {
			fileURLs[i] = jarFiles.get(i).toURI().toURL();
		}
		return new URLClassLoader(fileURLs, getClass().getClassLoader());
	}
	
	private List<Class<? extends TriangleScanner>> loadClassFiles(File jarFile, ClassLoader classLoader) throws IOException {
		List<Class<? extends TriangleScanner>> triangleScannerClasses = new ArrayList<>();
		Enumeration<JarEntry> jarClassFiles = null;
		try (FilteredJarFile filteredJarFile = new FilteredJarFile(jarFile.getName())) {
			jarClassFiles = filteredJarFile.entries((JarEntry jarEntry) -> jarEntry.getName().startsWith(PKG_PREFIX) && jarEntry.getName().endsWith(CLASS_EXT));
			while (jarClassFiles.hasMoreElements()) {
				String classEntry = jarClassFiles.nextElement().getName();
				String className = classEntry.replaceAll("/", "\\.").substring(0,
						classEntry.length() - CLASS_EXT.length());
				Class<?> klass = null;
				try {
					klass = classLoader.loadClass(className);
				} catch (ClassNotFoundException e) {
					continue;
				}
				if (TriangleScanner.class.isAssignableFrom(klass)) {
					triangleScannerClasses.add(klass.asSubclass(TriangleScanner.class));
				}
			}
		}
		return triangleScannerClasses;
	}
}