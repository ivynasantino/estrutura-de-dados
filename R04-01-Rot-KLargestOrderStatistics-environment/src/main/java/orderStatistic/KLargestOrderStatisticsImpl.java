package orderStatistic;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{
	
	@Override
	public T[] getKLargest(T[] array, int k) {
		if (array == null || k <= 0 || array.length < k) {
			return null;
		}
		
		int cont = 0;
		int size = k;
		T[] result = (T[]) new Comparable[size];
		int order = array.length;
	
		while (cont < k) {
			result[cont] = orderStatistics(array, order);
			order--;
			cont++;
		}
		
		return result;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if (array == null || k <= 0) {
			return null;
		}
		
		if (array.length < k) {
			return null;
		}
	
		int indexK = array.length - k;
		int maxIndex = 0;
		
		for (int i = 0; i < (array.length - k + 1); i++) {
			maxIndex = i;
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[maxIndex]) > 0) {
					maxIndex = j;
				}
			}
			Util.swap(array, i, maxIndex);
			
		}
		
		return array[indexK];
	}
}
