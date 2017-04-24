package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validaParamentros(array, leftIndex, rightIndex)) 
			return;
		
		for (int i = leftIndex; i < rightIndex; i++) {
			int min = i;
			for (int j = i + 1; j <= rightIndex; j++) {
				if (array[j].compareTo(array[min]) < 0) {
					min = j;
				}
			}
			Util.swap(array, i, min);
		}
	}
	
	private boolean validaParamentros(T[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length <= 1) 
			return false;
		
		
		if (leftIndex <= rightIndex || leftIndex < 0 || rightIndex <= 0) 
			return false;
		
		if (leftIndex >= array.length) 
			return false;
		
		return true;
	}
}
