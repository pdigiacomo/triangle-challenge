package dgcplg.tradeshift.trianglechallenge.triangles;

import dgcplg.tradeshift.trianglechallenge.Triangle;

public class IsoscelesTriangle extends Triangle {

	public IsoscelesTriangle(double sideAB, double sideC) {
		super(sideAB, sideAB, sideC);
	}

	@Override
	public String getSideType() {
		return "isosceles";
	}

}
