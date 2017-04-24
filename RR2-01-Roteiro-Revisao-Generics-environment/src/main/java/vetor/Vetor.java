package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor {

	// O array interno onde os objetos manipulados são guardados
	private Object[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(Object o) {
		if (!isCheio()) {
			indice++;
			arrayInterno[indice] = o;
		}
	}

	// Remove um objeto do vetor
	public Object remover(Object o) {
		Object retorno = null;
		if (!isVazio()) {
			
			for (int i = 0; i < arrayInterno.length; i++) {
				if (arrayInterno[i] == o) {
				
					retorno = arrayInterno[i];
					arrayInterno[i] = null;
					indice--;
				}
			}
			
			int size = arrayInterno.length;
			Object[] auxInterno = new Object[size];
			
			for (int i = 0; i < size; i++) {
				if (arrayInterno[i] != null) {
				auxInterno[i] = arrayInterno[i];
				
				}
			}
			
			arrayInterno = auxInterno;
			
		}
		
		return retorno;
	}

	// Procura um elemento no vetor
	public Object procurar(Object o) {
		if (!isVazio()) {
			
			for (int i = 0; i < arrayInterno.length; i++) {
				if (arrayInterno[i] == o) {
					return o;
				}
			}
		}
		
		return null;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return (indice <= -1);
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return (indice >= tamanho);
		
	}

}
