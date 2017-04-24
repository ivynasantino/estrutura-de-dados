package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	public static final int ZERO = 0;
	
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length <= 1)
			return;
		if (leftIndex >= rightIndex || rightIndex > array.length)
			return;
		if(leftIndex < ZERO)
			return;
		
	
		int maxValue = maxValue(array, leftIndex, rightIndex);
		int minValue = minValue(array, leftIndex, rightIndex);
		
		int size = maxValue - minValue + 1;
		
		Integer[] values = new Integer[size];
		
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
		}
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			values[array[i] - minValue]++;
		}
		
		int count = leftIndex;
		for (int i = 0; i < values.length; i++) {
			while (values[i] > 0) {
				array[count++] = i + minValue;
				values[i]--;
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
	
	private int minValue(Integer[] array, int leftIndex, int rightIndex) {
		int minValue = array[leftIndex];
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (minValue > array[i]) {
				minValue = array[i];
			}
		}
		
		return minValue;
	}
}
