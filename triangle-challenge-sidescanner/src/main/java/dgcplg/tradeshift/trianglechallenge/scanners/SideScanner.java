package dgcplg.tradeshift.trianglechallenge.scanners;

import dgcplg.tradeshift.trianglechallenge.ResultFormatter;
import dgcplg.tradeshift.trianglechallenge.ScanResult;
import dgcplg.tradeshift.trianglechallenge.Triangle;
import dgcplg.tradeshift.trianglechallenge.TriangleScanner;
import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;
import dgcplg.tradeshift.trianglechallenge.formatters.SideFormatter;
import dgcplg.tradeshift.trianglechallenge.triangles.EquilateralTriangle;
import dgcplg.tradeshift.trianglechallenge.triangles.IsoscelesTriangle;
import dgcplg.tradeshift.trianglechallenge.triangles.ScaleneTriangle;

public class SideScanner extends TriangleScanner {

	@Override
	public ScanResult scan(Triangle triangle) throws ScanException {
		ScanResult scanResult = new ScanResult();
		double a = triangle.getSideA();
		double b = triangle.getSideB();
		double c = triangle.getSideC();
		if (a == b) {
			if (b == c) {
				scanResult.setTriangle(new EquilateralTriangle(a));
			} else {
				scanResult.setTriangle(new IsoscelesTriangle(a, c));
			}
		} else {
			if (b == c) {
				scanResult.setTriangle(new IsoscelesTriangle(b, a));
			} else {
				scanResult.setTriangle(new ScaleneTriangle(a, b, c));
			}
		}
		return scanResult;
	}

	@Override
	public ResultFormatter getFormatter() {
		return new SideFormatter();
	}
}
