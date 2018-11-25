package dgcplg.tradeshift.trianglechallenge;

import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;

public class ResultFormatter {
	public void format(ScanResult scanResult) {
		ScanException e = scanResult.getScanException();
		if (e != null) {
			System.out.format("Error: %s\n", e.getMessage());
		} else {
			System.out.format("Result: ");
		}
	}
}
