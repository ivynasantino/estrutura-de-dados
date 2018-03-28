package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (verifica(array, leftIndex, rightIndex)) {
			boolean trocas = false;
			
			for (int i = leftIndex; i < rightIndex; i++) 
				for (int j = leftIndex; j < rightIndex; j++) 
					if (array[j].compareTo(array[j+1]) > 0) 
						Util.swap(array, j, j+1);
						trocas = true;
						
			if (!trocas) {
				return;
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
