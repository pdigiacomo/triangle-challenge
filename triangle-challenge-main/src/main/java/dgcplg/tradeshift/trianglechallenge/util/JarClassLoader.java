package dgcplg.tradeshift.trianglechallenge.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JarClassLoader implements Closeable {
	private static Logger logger = LoggerFactory.getLogger(JarClassLoader.class);

	private URLClassLoader urlClassLoader;

	public JarClassLoader(File[] jarFiles, ClassLoader parent) {
		URL[] fileURLs = new URL[jarFiles.length];
		for (int i = 0; i < jarFiles.length; i++) {
			try {
				fileURLs[i] = jarFiles[i].toURI().toURL();
			} catch (MalformedURLException e) {
				logger.warn("Could not get URL for jar file '{}'", jarFiles[i].getName(), e);
				continue;
			}
		}
		if (parent == null) {
			urlClassLoader = new URLClassLoader(fileURLs);
		} else {
			urlClassLoader = new URLClassLoader(fileURLs, parent);
		}
	}

	public JarClassLoader(File[] jarFiles) {
		this(jarFiles, null);
	}

	public Set<Class<?>> loadJarClass(File jarFile) throws JarClassesNotFoundException, IOException {
		Set<Class<?>> loadedClasses = new HashSet<>();
		List<ClassNotFoundException> cnfeList = new ArrayList<>();
		try (FilteredJarFile filteredJarFile = new FilteredJarFile(jarFile.getName())) {
			Enumeration<JarEntry> jarClassFiles = filteredJarFile
					.entries((JarEntry jarEntry) -> jarEntry.getName().endsWith(".class"));
			while (jarClassFiles.hasMoreElements()) {
				String classEntry = jarClassFiles.nextElement().getName();
				String className = classEntry.replaceAll("/", "\\.").substring(0, classEntry.length() - ".class".length());
				try {
					Class<?> loadedClass = loadJarClass(className);
					loadedClasses.add(loadedClass);
				} catch (ClassNotFoundException e) {
					logger.warn("Error loading class {} from file {}, class not loaded", className, jarFile.getName(), e);
					cnfeList.add(e);
				}
			}
		}
		if (!cnfeList.isEmpty()) {
			throw new JarClassesNotFoundException("One or more ClassNotFoundExceptions occurred", cnfeList, jarFile.getName());
		}
		return loadedClasses;
	}

	public Class<?> loadJarClass(String className) throws ClassNotFoundException {
		logger.trace("Attempting to load class '{}'", className);
		return urlClassLoader.loadClass(className);
	}

	@Override
	public void close() throws IOException {
		urlClassLoader.close();
	}
}
