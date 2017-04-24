package forma;

public class Circulo implements Forma{

	private double raio;
	public static final double PI = Math.PI;
	
	public Circulo (double raio) {
		this.raio = raio;
	}

	public double getRaio() {
		return raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	@Override
	public double area() {
		return getRaio() * getRaio() * PI;
	}
	
	
}

