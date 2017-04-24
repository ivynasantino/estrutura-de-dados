package adt.heap;

public class HeapOthres<T extends Comparable<T>> extends HeapImpl<T> {

	// No caso da minHeap
	public T kSmall(int k) {
		return null;
	}
	
	public T kLargest(int k) {
		return null;
	}
	
	public T[] elementsByLevel(int level) {
		int index = (int)Math.pow(2, level-1) - 1;
		int cont = 0;
		int lenArray = (int)(Math.pow(2, level-1));
		T[] array = (T[])new Comparable[lenArray];
		
		while (cont < lenArray) {
			array[cont] = heap[index];
			cont++;
			index++;
		}
		
		return array;
		
	}
	
}
