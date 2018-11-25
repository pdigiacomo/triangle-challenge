package dgcplg.tradeshift.trianglechallenge.scanners;

import dgcplg.tradeshift.trianglechallenge.ResultFormatter;
import dgcplg.tradeshift.trianglechallenge.ScanResult;
import dgcplg.tradeshift.trianglechallenge.Triangle;
import dgcplg.tradeshift.trianglechallenge.TriangleScanner;
import dgcplg.tradeshift.trianglechallenge.exceptions.InvalidTriangleException;
import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;
import dgcplg.tradeshift.trianglechallenge.formatters.ValidityFormatter;

public class ValidityScanner extends TriangleScanner {

	@Override
	public ScanResult scan(Triangle triangle) throws ScanException {
		double a = triangle.getSideA();
		double b = triangle.getSideB();
		double c = triangle.getSideC();
		if (a + b <= c || b + c <= a || a + c <= b) {
			throw new InvalidTriangleException("Not a valid triangle");
		}
		ScanResult scanResult = new ScanResult();
		scanResult.setTriangle(triangle);
		return scanResult;
	}

	@Override
	public ResultFormatter getFormatter() {
		return new ValidityFormatter();
	}
}
