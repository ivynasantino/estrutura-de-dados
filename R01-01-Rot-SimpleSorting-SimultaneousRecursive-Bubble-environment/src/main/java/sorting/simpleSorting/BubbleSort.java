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
		
		if (array == null || array.lenght == 0) return;
		else if (leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.lenght) return;
		else if (leftIndex >= array.lenght || rightIndex <= 0) return;
		
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
