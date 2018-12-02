package dgcplg.tradeshift.trianglechallenge.triangles;

import dgcplg.tradeshift.trianglechallenge.Triangle;

public class ScaleneTriangle extends Triangle {

	public ScaleneTriangle(Double sideA, Double sideB, Double sideC) {
		getDataMap().put("A", sideA);
		getDataMap().put("B", sideB);
		getDataMap().put("C", sideC);
	}
}
