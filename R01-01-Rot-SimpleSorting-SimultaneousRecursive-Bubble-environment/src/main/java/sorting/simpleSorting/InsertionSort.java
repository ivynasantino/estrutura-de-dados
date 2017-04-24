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
		if (array == null || array.lenght == 0) return;
		else if (leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.lenght) return;
		else if (leftIndex >= array.lenght || rightIndex <= 0) return;
		
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
