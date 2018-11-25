package dgcplg.tradeshift.trianglechallenge.formatters;

import dgcplg.tradeshift.trianglechallenge.ResultFormatter;
import dgcplg.tradeshift.trianglechallenge.ScanResult;
import dgcplg.tradeshift.trianglechallenge.Triangle;

public class SideFormatter extends ResultFormatter {

	@Override
	public void format(ScanResult scanResult) {
		Triangle triangle = scanResult.getTriangle();
		System.out.format("Triangle is %s", triangle.getSideType());
	}
}
