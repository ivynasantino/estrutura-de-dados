package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verifica(array, leftIndex, rightIndex)) {
		
			for (int i = leftIndex + 1; i <= rightIndex; i++){
				T aux = array[i];
				int j = i - 1;
				
				while (j > leftIndex - 1 && array[j].compareTo(aux) > 0) {
					Util.swap(array, j+1, j);
					j--;
				}
				
				array[j+1] = aux;
			}
		}
		
	}
	
	private boolean verifica(T[] array, int leftIndex, int rightIndex) {
		boolean result = true;
		
		if (array == null || array.length <= 0) {
			result = false;
		} else if (leftIndex >= rightIndex || leftIndex < 0) {
			result = false;
		} else if (rightIndex > array.length || leftIndex >= array.length
				|| rightIndex <= 0) {
			result = false;
		}
		
		return result;
	}
	
}
