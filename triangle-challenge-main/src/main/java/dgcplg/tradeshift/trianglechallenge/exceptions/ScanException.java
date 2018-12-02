package dgcplg.tradeshift.trianglechallenge.exceptions;

public class ScanException extends Exception {
	private static final long serialVersionUID = -3896664507211772469L;

	public ScanException() {
	}
	
	public ScanException(String message) {
		super(message);
	}
	
	public ScanException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ScanException(Throwable cause) {
		super(cause);
	}
}
