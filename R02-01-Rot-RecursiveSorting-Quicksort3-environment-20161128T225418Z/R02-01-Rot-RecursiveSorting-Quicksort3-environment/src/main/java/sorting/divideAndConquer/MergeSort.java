package sorting.divideAndConquer;


import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (verifica(array, leftIndex, rightIndex)) {
			if (leftIndex < rightIndex) {
				int middle = leftIndex + (rightIndex - leftIndex) / 2;
				sort(array, leftIndex, middle);
				sort(array, middle + 1, rightIndex);
				merge(array, leftIndex, middle, rightIndex);
			}
		}
		
	}
	
	private void merge(T[] array, int leftIndex, int middle, int rightIndex){
		int size = array.length;
		T[] aux = (T[]) new Comparable[size];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			aux[i] = array[i];
		}
		
		int i = leftIndex;
		int k = leftIndex;
		int mid = middle + 1;
		
		while (i <= middle && mid <= rightIndex) {
			if (aux[i].compareTo(aux[mid]) < 0) {
				array[k] = aux[i];
				i++;
			}
			
			else {
				array[k] = aux[mid];
				mid++;
			}
			
			k++;
		}
		
		while (i <= middle) {
			array[k] = aux[i];
			k++;
			i++;
		}
		
		while (mid <= middle) {
			array[k] = aux[mid];
			k++;
			mid++;
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
