package dgcplg.tradeshift.trianglechallenge.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FilteredJarFile extends JarFile {

	public FilteredJarFile(String name) throws IOException {
		super(name);
	}

	public static FilteredJarFile fromJarFile(JarFile jarFile) throws IOException {
		return new FilteredJarFile(jarFile.getName());
	}

	public Enumeration<JarEntry> entries(JarEntryFilter filter) {
		Enumeration<JarEntry> allEntries = super.entries();
		List<JarEntry> filteredEntries = new ArrayList<>();
		while (allEntries.hasMoreElements()) {
			JarEntry entry = allEntries.nextElement();
			if (filter.accept(entry)) {
				filteredEntries.add(entry);
			}
		}
		return Collections.enumeration(filteredEntries);
	}
}
