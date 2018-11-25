package dgcplg.tradeshift.trianglechallenge;

import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;

public abstract class TriangleScanner {
	public abstract ScanResult scan(Triangle triangle) throws ScanException;
	public abstract ResultFormatter getFormatter();
}
