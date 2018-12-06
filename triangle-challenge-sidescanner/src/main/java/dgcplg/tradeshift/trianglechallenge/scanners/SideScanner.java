package dgcplg.tradeshift.trianglechallenge.scanners;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dgcplg.tradeshift.trianglechallenge.ScannerFormatter;
import dgcplg.tradeshift.trianglechallenge.Triangle;
import dgcplg.tradeshift.trianglechallenge.TriangleScanner;
import dgcplg.tradeshift.trianglechallenge.exceptions.InvalidTriangleException;
import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;
import dgcplg.tradeshift.trianglechallenge.formatters.SideFormatter;
import dgcplg.tradeshift.trianglechallenge.triangles.EquilateralTriangle;
import dgcplg.tradeshift.trianglechallenge.triangles.IsoscelesTriangle;
import dgcplg.tradeshift.trianglechallenge.triangles.ScaleneTriangle;

public class SideScanner extends TriangleScanner {
	private SideFormatter sideFormatter;
	private List<String> inputParams;
	
	public SideScanner() {
		sideFormatter = new SideFormatter(this);
		inputParams = new LinkedList<>(Arrays.asList(new String[] {"A", "B", "C"}));
	}
	
	@Override
	public Triangle scan() throws ScanException {
		Map<String,Double> dataMap = getTriangle().getDataMap();
		Double a = dataMap.get("A");
		Double b = dataMap.get("B");
		Double c = dataMap.get("C");
		if (a.equals(b)) {
			if (b.equals(c)) {
				return new EquilateralTriangle(a);
			} else {
				return new IsoscelesTriangle(a, c);
			}
		} else {
			if (b.equals(c)) {
				return new IsoscelesTriangle(b, a);
			} else if (a.equals(c)) {
				return new IsoscelesTriangle(a, b);
			} else {
				return new ScaleneTriangle(a,b,c);
			}
		}
	}
	
	public void checkData() throws InvalidTriangleException {
		Map<String,Double> dataMap = getTriangle().getDataMap();
		double a = dataMap.get("A");
		double b = dataMap.get("B");
		double c = dataMap.get("C");
		if (a + b <= c) {
			throw new InvalidTriangleException("Degenerate triangle: A("+a+") + B("+b+") <= C("+c+")");
		} else if (b + c <= a) {
			throw new InvalidTriangleException("Degenerate triangle: B("+b+") + C("+c+") <= A("+a+")");
		} else if (a + c <= b) {
			throw new InvalidTriangleException("Degenerate triangle: A("+a+") + C("+c+") <= B("+b+")");
		}
	}
	
	@Override
	public ScannerFormatter getFormatter() {
		return sideFormatter;
	}

	@Override
	public List<String> getInputParams() {
		return inputParams;
	}
}
