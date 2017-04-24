package sorting.divideAndConquer.quicksort3;
   
import sorting.AbstractSorting;
import util.Util;
   
   /**
    * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
    * funciona de forma ligeiramente diferente. Relembre que quando o pivô
   * escolhido divide o array aproximadamente na metade, o QuickSort tem um
   * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
   * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
   * pivô. Essa técnica consiste no seguinte: 1. Comparar o elemento mais a
   * esquerda, o central e o mais a direita do intervalo. 2. Ordenar os elemento,
   * tal que: A[left] < A[center] < A[right]. 3. Adotar o A[center] como pivô. 4.
   * Colocar o pivô na penúltima posição A[right-1]. 5. Aplicar o particionamento
   * considerando o vetor menor, de A[left+1] até A[right-1]. 6. Aplicar o
   * algoritmo na metade a esquerda e na metade a direita do pivô.
   */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
  		AbstractSorting<T> {
  
	public void sort(T[] array, int leftIndex, int rightIndex) {
  		
  		if (array == null || array.lenght == 0) return;
		else if (leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.lenght) return;
		else if (leftIndex >= array.lenght || rightIndex <= 0) return;
  		
  		if (leftIndex < rightIndex) {
  			int mid = leftIndex + (rightIndex - leftIndex) / 2;
  			medianOfThree(array, leftIndex, mid, rightIndex);
  			Util.swap(array, mid, rightIndex - 1);
  			int pivot = particion(array, leftIndex + 1, rightIndex - 1);
  			
  			sort(array, leftIndex, pivot - 1);
  			sort(array, pivot + 1, rightIndex);
  			
  		}
  	}
  	
  	private void medianOfThree(T[] array, int leftIndex, int mid, 
  			int rightIndex) {
  		
  		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
  			Util.swap(array, leftIndex, rightIndex);
  		}
  		
  		if (array[mid].compareTo(array[rightIndex]) > 0) {
  			Util.swap(array, mid, rightIndex);
  		}
  		
  		if (array[leftIndex].compareTo(array[mid]) > 0) {
  			Util.swap(array, leftIndex, mid);
  		}
  	}
  	
  	private int particion(T[] array, int leftIndex, int rightIndex) {
  
  		T pivot = array[leftIndex - 1];
 		int inicial = leftIndex - 1;
  		  		
  		for (int j = leftIndex; j < rightIndex; j++) {
  			if (array[j].compareTo(pivot) < 0) {
  				inicial++;
  		  		Util.swap(array, inicial, j);
  		  		}
  			}
  		  	
  		Util.swap(array, leftIndex - 1, inicial);
  		return inicial; 	
  		
  	}
 
}
