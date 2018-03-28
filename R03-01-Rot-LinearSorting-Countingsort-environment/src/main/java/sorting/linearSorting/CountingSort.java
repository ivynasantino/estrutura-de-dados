package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	public static final int ZERO = 0;
	
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (verifica(array, leftIndex, rightIndex)) {
			int maxValue = maxValue(array, leftIndex, rightIndex);
			
			Integer[] values = new Integer[maxValue + 1];
			
			for (int i = 0; i < values.length; i++) {
				values[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				values[array[i]]++;
			}
			
			int count = leftIndex;
			for (int i = 0; i < values.length; i++) {
				while (values[i] > 0) {
					array[count++] = i;
					values[i]--;
				}
			}
		}
	       
	}

	private int maxValue(Integer[] array, int leftIndex, int rightIndex) {
		int maxValue = array[leftIndex];
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (maxValue < array[i]) {
				maxValue = array[i];
				
			}
		}
		
		return maxValue;
	}
	
	private boolean verifica(Integer[] array, int leftIndex, int rightIndex) {
		boolean result = true;
		
		if (array == null || array.length <= ZERO) {
			result = false;
		} else if (leftIndex >= rightIndex || leftIndex < ZERO) {
			result = false;
		} else if (rightIndex > array.length || leftIndex >= array.length
				|| rightIndex <= ZERO) {
			result = false;
		}
		
		return result;
	}
	
}
