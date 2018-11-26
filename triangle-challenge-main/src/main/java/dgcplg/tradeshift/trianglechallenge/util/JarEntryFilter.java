package dgcplg.tradeshift.trianglechallenge.util;

import java.util.jar.JarEntry;

@FunctionalInterface
public interface JarEntryFilter {
	public boolean accept(JarEntry jarEntry);
}
