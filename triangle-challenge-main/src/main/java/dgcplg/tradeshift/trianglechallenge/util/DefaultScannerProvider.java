package dgcplg.tradeshift.trianglechallenge.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dgcplg.tradeshift.trianglechallenge.TriangleScanner;

public class DefaultScannerProvider implements ScannerProvider {
	private static Logger logger = LoggerFactory.getLogger(DefaultScannerProvider.class);
	
	private static final String SEARCH_DIR = ".";

	@Override
	public Set<TriangleScanner> getTriangleScanners() throws ScannerProviderException {
		boolean excOccurred = false;
		logger.info("Searching for scanner plugins..");
		Set<Class<?>> loadedClasses = new HashSet<>();
		Set<Class<? extends TriangleScanner>> triangleScannerClasses = new HashSet<>();
		Set<TriangleScanner> triangleScanners = new HashSet<>();
		File[] jarFiles = findJarFiles(SEARCH_DIR);
		try (JarClassLoader classLoader = new JarClassLoader(jarFiles)) {
			for (File jarFile : jarFiles) {
				try {
					loadedClasses.addAll(classLoader.loadJarClass(jarFile));
				} catch (IOException e) {
					logger.warn("Error accessing jar file {}, skipping file..", jarFile.getName(), e);
					excOccurred = true;
				} catch (JarClassesNotFoundException e) {
					String excMsg = e.getClassNotFoundExceptions().stream().map(exc -> exc.getMessage()).collect(Collectors.joining("; "));
					logger.warn("Error while loading classes from jar file {}: {}", e.getJarFile(), excMsg);
					excOccurred = true;
				}
			}
		} catch (IOException e) {
			logger.warn("Class loader not properly closed", e);
			excOccurred = true;
		}
		triangleScannerClasses = loadedClasses.stream()
				.filter(klazz -> TriangleScanner.class.isAssignableFrom(klazz))
				.map(tsKlazz -> (Class<? extends TriangleScanner>)tsKlazz.asSubclass(TriangleScanner.class))
				.collect(Collectors.toSet());
		int nLoaded = triangleScannerClasses.size();
		for (Class<? extends TriangleScanner> triangleScannerClass : triangleScannerClasses) {
			try {
				triangleScanners.add(triangleScannerClass.newInstance());
				logger.info("Found scanner plugin: {} [loaded]", triangleScannerClass.getSimpleName());
			} catch (InstantiationException | IllegalAccessException e) {
				logger.info("Found scanner plugin: {} [error: {}]", triangleScannerClass.getSimpleName(), e.getMessage());
				nLoaded --;
				excOccurred = true;
			}
		}
		logger.info("{} plugins loaded.", nLoaded);
		if (excOccurred) {
			throw new ScannerProviderException("Errors occurred while loading scanners");
		}
		return triangleScanners;
	}

	private File[] findJarFiles(String dirpath) {
		File searchdir = new File(dirpath);
		if (dirpath == null || !searchdir.isDirectory()) {
			throw new IllegalArgumentException(dirpath+" is not a valid directory path");
		}
		String thisJarsFilePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String thisJarsFilename = thisJarsFilePath.substring(thisJarsFilePath.lastIndexOf("/")+1);
		File[] files = searchdir.listFiles(
				(File f, String fname) -> !f.isFile() && f.canRead() && !f.isHidden() && !fname.equals(thisJarsFilename) && fname.endsWith(".jar"));
		return files;
	}
}