package dgcplg.tradeshift.trianglechallenge.triangles;

import dgcplg.tradeshift.trianglechallenge.Triangle;

public class EquilateralTriangle extends Triangle {

	public EquilateralTriangle(double sideABC) {
		super(sideABC, sideABC, sideABC);
	}

	@Override
	public String getSideType() {
		return "equilateral";
	}

}
