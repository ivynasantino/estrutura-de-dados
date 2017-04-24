package forma;

public class Quadrado implements Forma{

	private double lado;
	
	public Quadrado (double lado) {
		this.lado = lado;
	}
	@Override
	public double area() {
		return getLado() * getLado();
	}
	
	public void setLado(double lado) {
		this.lado = lado;
	}

	public double getLado(){
		return lado;
	}
}
