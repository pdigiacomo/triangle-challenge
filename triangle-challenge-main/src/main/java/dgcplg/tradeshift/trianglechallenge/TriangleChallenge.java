package dgcplg.tradeshift.trianglechallenge;

import java.util.List;

import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;
import dgcplg.tradeshift.trianglechallenge.scanners.ValidityScanner;
import dgcplg.tradeshift.trianglechallenge.util.DefaultScannerProvider;
import dgcplg.tradeshift.trianglechallenge.util.ScannerProvider;

public class TriangleChallenge {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: java -jar [this_jar_name.jar] no1 no2 no3");
		}
		double[] sideLengths = new double[3];
		for (int i=0; i<3; i++) {
			try {
				sideLengths[i] = Double.parseDouble(args[i]);
			} catch (NumberFormatException e) {
				System.out.format("Length %d is invalid or not numerical", i+1);
				return;
			}
		}
		Triangle genericTriangle = new Triangle(sideLengths[0], sideLengths[1], sideLengths[2]);
		TriangleScanner validityScanner = new ValidityScanner();
		ScanResult validityResult = null;
		try {
			validityResult = validityScanner.scan(genericTriangle);
		} catch (ScanException e) {
			validityResult = new ScanResult();
			validityResult.setScanException(e);
			validityScanner.getFormatter().format(validityResult);
			return;
		}
		System.out.format("\n%s: ", validityScanner.getName());
		validityScanner.getFormatter().format(validityResult);
		
		
		ScannerProvider scannerProvider = new DefaultScannerProvider();
		List<TriangleScanner> scanners = scannerProvider.getTriangleScanners();
		
		for (TriangleScanner scanner: scanners) {
			ScanResult result = null;
			try {
				result = scanner.scan(genericTriangle);
			} catch (ScanException e) {
				result = new ScanResult();
				result.setScanException(e);
			}
			System.out.format("\n%s: ",scanner.getName());
			scanner.getFormatter().format(result);
		}
	}
}
