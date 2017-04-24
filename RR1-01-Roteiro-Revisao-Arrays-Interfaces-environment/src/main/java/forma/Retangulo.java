package forma;

public class Retangulo implements Forma{

	private double comprimento;
	private double altura;
	
	public Retangulo (double comprimento, double altura) {
		this.comprimento = comprimento;
		this.altura = altura;
	}
	

	@Override
	public double area() {
		return getComprimento() * getAltura();
	}


	public double getComprimento() {
		return comprimento;
	}


	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}


	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	
}
