package dgcplg.tradeshift.trianglechallenge.triangles;

import dgcplg.tradeshift.trianglechallenge.Triangle;

public class EquilateralTriangle extends Triangle {

	public EquilateralTriangle(Double side) {
		getDataMap().put("A", side);
		getDataMap().put("B", side);
		getDataMap().put("C", side);
		getDataMap().put("alpha", 60.0);
		getDataMap().put("beta", 60.0);
		getDataMap().put("gamma", 60.0);
	}
}
