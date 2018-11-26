package dgcplg.tradeshift.trianglechallenge.util;

import java.net.URL;
import java.net.URLClassLoader;

public class JarClassLoader extends URLClassLoader {

	public JarClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

}

