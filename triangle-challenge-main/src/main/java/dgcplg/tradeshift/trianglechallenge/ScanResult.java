package dgcplg.tradeshift.trianglechallenge;

import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;

public class ScanResult {
	private Triangle triangle;
	private ScanException scanException;

	public Triangle getTriangle() {
		return triangle;
	}
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
	public ScanException getScanException() {
		return scanException;
	}
	public void setScanException(ScanException scanException) {
		this.scanException = scanException;
	}
}
