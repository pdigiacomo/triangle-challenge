package dgcplg.tradeshift.trianglechallenge;

import java.util.Set;

import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;
import dgcplg.tradeshift.trianglechallenge.util.DefaultScannerProvider;
import dgcplg.tradeshift.trianglechallenge.util.ScannerProvider;
import dgcplg.tradeshift.trianglechallenge.util.ScannerProviderException;

public class TriangleChallenge {
	private Set<TriangleScanner> scanners;
	
	public static void main(String[] args) {
		TriangleChallenge triangleChallenge = new TriangleChallenge();
		triangleChallenge.run();
	}
	
	public TriangleChallenge() {
		ScannerProvider scannerProvider = new DefaultScannerProvider();
		try {
			scanners = scannerProvider.getTriangleScanners();
		} catch (ScannerProviderException e) {
			String warnMsg = "WARNING: One or more scanners might not have been loaded. Please see log file for details";
			System.out.format("\n%s.\n\n", warnMsg);
		}
	}
	
	public void run() {
		Triangle triangle = new Triangle();
		
		for (TriangleScanner scanner: scanners) {
			ScannerFormatter scFormatter = scanner.getFormatter();
			try {
				scanner.init(triangle);
				System.out.format("\n%s\n", scanner.getName());
				System.out.format(scFormatter.formatInput());
				scFormatter.readInput();
				triangle = scanner.scan();
			} catch (ScanException e) {
				System.out.format("\n%s\n\n", scFormatter.formatError(e));
				continue;
			}
			System.out.format("\n%s\n\n", scFormatter.formatOutput(triangle));
		}
	}
}
