package dgcplg.tradeshift.trianglechallenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dgcplg.tradeshift.trianglechallenge.exceptions.InvalidTriangleException;
import dgcplg.tradeshift.trianglechallenge.exceptions.ScanException;

public abstract class ScannerFormatter {
	TriangleScanner scanner;
	Map<String,Double> inputData;
	
	public ScannerFormatter(TriangleScanner scanner) {
		this.scanner = scanner;
	}
	
	public void readInput() throws InvalidTriangleException {
		inputData = new HashMap<String,Double>();
		List<String> inputParams = scanner.getInputParams();
		try (Scanner sc = new Scanner(System.in)) {
			for (String param: inputParams) {
				inputData.put(param, sc.nextDouble());
			}
		}
		scanner.updateData(inputData);
	}
	
	public abstract String formatInput();
	
	public String formatOutput(Triangle triangle) {
		return "Result: ";
	}
	
	public String formatError(ScanException e) {
		return "Error: "+ e.getMessage();
	}

	public Map<String, Double> getInputData() {
		return inputData;
	}

	public void setInputData(Map<String, Double> inputData) {
		this.inputData = inputData;
	}


	public TriangleScanner getScanner() {
		return scanner;
	}


	public void setScanner(TriangleScanner scanner) {
		this.scanner = scanner;
	}
	
	
}
