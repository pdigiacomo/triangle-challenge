package dgcplg.tradeshift.trianglechallenge;

import java.util.HashMap;
import java.util.Map;

public class Triangle {
	private Map<String, Double> dataMap;

	public Triangle() {
		dataMap = new HashMap<>();
	}

	public Map<String, Double> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Double> dataMap) {
		this.dataMap = dataMap;
	}
}
