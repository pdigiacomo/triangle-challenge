package dgcplg.tradeshift.trianglechallenge;

public class Triangle {
	public Triangle(double sideA, double sideB, double sideC) {
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
	}
	
	public Triangle(double sideA, double sideB, double sideC, double angleA, double angleB, double angleC) {
		this(sideA, sideB, sideC);
		this.angleA = angleA;
		this.angleB = angleB;
		this.angleC = angleC;
	}
	
	private double sideA;
	private double sideB;
	private double sideC;
	private double angleA;
	private double angleB;
	private double angleC;
	
	public String getSideType() {
		return "generic";
	}

	public double getSideA() {
		return sideA;
	}

	public void setSideA(double sideA) {
		this.sideA = sideA;
	}

	public double getSideB() {
		return sideB;
	}

	public void setSideB(double sideB) {
		this.sideB = sideB;
	}

	public double getSideC() {
		return sideC;
	}

	public void setSideC(double sideC) {
		this.sideC = sideC;
	}

	public double getAngleA() {
		return angleA;
	}

	public void setAngleA(double angleA) {
		this.angleA = angleA;
	}

	public double getAngleB() {
		return angleB;
	}

	public void setAngleB(double angleB) {
		this.angleB = angleB;
	}

	public double getAngleC() {
		return angleC;
	}

	public void setAngleC(double angleC) {
		this.angleC = angleC;
	}
}
