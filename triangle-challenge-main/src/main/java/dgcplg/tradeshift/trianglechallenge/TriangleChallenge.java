package dgcplg.tradeshift.trianglechallenge;

import java.util.List;

import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;
import dgcplg.tradeshift.trianglechallenge.util.DefaultScannerProvider;
import dgcplg.tradeshift.trianglechallenge.util.ScannerProvider;

public class TriangleChallenge {
	public static void main(String[] args) {
		ScannerProvider scannerProvider = new DefaultScannerProvider();
		List<TriangleScanner> scanners = scannerProvider.getTriangleScanners();
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
