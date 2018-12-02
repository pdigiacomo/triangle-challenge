package dgcplg.tradeshift.trianglechallenge.formatters;

import java.util.List;

import dgcplg.tradeshift.trianglechallenge.ScannerFormatter;
import dgcplg.tradeshift.trianglechallenge.Triangle;
import dgcplg.tradeshift.trianglechallenge.scanners.SideScanner;
import dgcplg.tradeshift.trianglechallenge.triangles.EquilateralTriangle;
import dgcplg.tradeshift.trianglechallenge.triangles.IsoscelesTriangle;
import dgcplg.tradeshift.trianglechallenge.triangles.ScaleneTriangle;

public class SideFormatter extends ScannerFormatter {

	public SideFormatter(SideScanner scanner) {
		super(scanner);
	}
	
	@Override
	public String formatOutput(Triangle triangle) {
		return "Triangle is "+ getSideType(triangle);
	}
	
	private String getSideType(Triangle triangle) {
		if (triangle instanceof EquilateralTriangle) {
			return "Equilateral";
		} else if (triangle instanceof IsoscelesTriangle) {
			return "Isosceles";
		} else if (triangle instanceof ScaleneTriangle) {
			return "Scalene";
		} else {
			return "Unknown";
		}
	}
	
	@Override
	public String formatInput() {
		List<String> inputParams = getScanner().getInputParams();
		if (inputParams.isEmpty()) {
			return "";
		}
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Please input side ");
		for (int i=0; i<inputParams.size(); i++) {
			strBuilder.append(inputParams.get(i));
			if (i<inputParams.size()-1) {
				strBuilder.append(", ");
			}
		}
		strBuilder.append(": ");
		return strBuilder.toString();
	}
}
