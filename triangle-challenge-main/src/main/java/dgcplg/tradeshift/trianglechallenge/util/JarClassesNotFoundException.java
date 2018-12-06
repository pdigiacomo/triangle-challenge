package dgcplg.tradeshift.trianglechallenge.util;

import java.util.List;

/**
 * Jar-level class exception that holds collective information about all the
 * {@code ClassNotFoundException}s occurred while loading classes from a jar
 * file. <br>
 * This exception may have multiple causes, namely the
 * {@code ClassNotFoundException}s reported, but an aggregated and/or custom
 * cause may be provided with the constructors that accept a {@code Throwable}.
 * 
 * @author Pierluigi
 *
 */
public class JarClassesNotFoundException extends ReflectiveOperationException {
	private static final long serialVersionUID = -6799406196195149430L;

	private List<ClassNotFoundException> cnfeList;
	private String jarFile;
	private Throwable cause;

	public JarClassesNotFoundException(List<ClassNotFoundException> cnfeList, String jarFile) {
		this.cnfeList = cnfeList;
		this.jarFile = jarFile;
	}

	public JarClassesNotFoundException(String message, List<ClassNotFoundException> cnfeList, String jarFile) {
		super(message);
		this.cnfeList = cnfeList;
		this.jarFile = jarFile;
	}

	public JarClassesNotFoundException(String message, Throwable cause, List<ClassNotFoundException> cnfeList,
			String jarFile) {
		super(message, null);
		this.cnfeList = cnfeList;
		this.cause = cause;
		this.jarFile = jarFile;
	}

	public JarClassesNotFoundException(Throwable cause, List<ClassNotFoundException> cnfeList, String jarFile) {
		super((Throwable) null);
		this.cnfeList = cnfeList;
		this.cause = cause;
		this.jarFile = jarFile;
	}

	public List<ClassNotFoundException> getClassNotFoundExceptions() {
		return cnfeList;
	}

	public String getJarFile() {
		return jarFile;
	}

	public Throwable getCause() {
		return cause;
	}
}
