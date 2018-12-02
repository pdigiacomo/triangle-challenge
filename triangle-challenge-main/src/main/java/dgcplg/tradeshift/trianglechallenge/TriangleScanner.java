package dgcplg.tradeshift.trianglechallenge;

import java.util.List;
import java.util.Map;
import java.util.Set;

import dgcplg.tradeshift.trianglechallenge.exceptions.InvalidTriangleException;
import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;

public abstract class TriangleScanner {
	private Triangle triangle;

	public abstract Triangle scan() throws ScanException;
	public abstract ScannerFormatter getFormatter();
	public abstract List<String> getInputParams();
	public abstract void checkData() throws InvalidTriangleException;
	
	public final void init(Triangle triangle) {
		this.triangle = triangle;
		Map<String,Double> dataList = triangle.getDataMap();
		Set<String> dataNameList = dataList.keySet();
		List<String> inputList = getInputParams();
		for (String dataName: dataNameList) {
			if (inputList.contains(dataName) && dataList.get(dataName)!=null) {
				inputList.remove(dataName);
			}
		}
	}
	
	public final void updateData(Map<String,Double> newDataMap) throws InvalidTriangleException {
		for (String dataName: newDataMap.keySet()) {
			triangle.getDataMap().put(dataName, newDataMap.get(dataName));
		}
		checkData();
	}
	
	public String getName() {
		return getClass().getSimpleName();
	}
	
	public Triangle getTriangle() {
		return triangle;
	}
	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}
}