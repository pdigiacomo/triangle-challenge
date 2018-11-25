package dgcplg.tradeshift.trianglechallenge.formatters;

import dgcplg.tradeshift.trianglechallenge.ResultFormatter;
import dgcplg.tradeshift.trianglechallenge.ScanResult;
import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;

public class ValidityFormatter extends ResultFormatter {

	@Override
	public void format(ScanResult scanResult) {
		ScanException e = scanResult.getScanException();
		if (e != null) {
			System.out.format("Error: %s\n", e.getMessage());
		} else {
			System.out.format("ValidityScanner: Triangle is valid\n");
		}
	}
}
