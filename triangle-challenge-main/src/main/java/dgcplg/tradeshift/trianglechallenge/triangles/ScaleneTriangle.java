package dgcplg.tradeshift.trianglechallenge.triangles;

import dgcplg.tradeshift.trianglechallenge.Triangle;

public class ScaleneTriangle extends Triangle {

	public ScaleneTriangle(double sideA, double sideB, double sideC) {
		super(sideA, sideB, sideC);
	}

	@Override
	public String getSideType() {
		return "scalene";
	}
}
