package dgcplg.tradeshift.trianglechallenge.triangles;

import dgcplg.tradeshift.trianglechallenge.Triangle;

public class IsoscelesTriangle extends Triangle {

	public IsoscelesTriangle(Double sideAB, Double sideC) {
		getDataMap().put("A", sideAB);
		getDataMap().put("B", sideAB);
		getDataMap().put("C", sideC);
	}
}
