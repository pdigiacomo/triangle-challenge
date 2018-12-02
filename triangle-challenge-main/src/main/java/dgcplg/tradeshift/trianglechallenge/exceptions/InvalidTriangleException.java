package dgcplg.tradeshift.trianglechallenge.exceptions;

public class InvalidTriangleException extends ScanException {
	private static final long serialVersionUID = -6267262390175594465L;
	
	public InvalidTriangleException() {
	}
	
	public InvalidTriangleException(String message) {
		super(message);
	}
	
	public InvalidTriangleException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidTriangleException(Throwable cause) {
		super(cause);
	}
}
