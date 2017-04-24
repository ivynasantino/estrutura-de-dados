package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Essa comparação não é feita diretamente com os elementos armazenados,
 * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
 * pode funcionar como uma max-heap ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	
	private static final int ZERO = 0;
	
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	public HeapImpl() {
		comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
			
		};
	}
	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		//T[] resp = Util.makeArray(index + 1);
		T[] resp = (T[])new Comparable[index+1];
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int max = position;
		
		if (left <= index && heap[left] != null && 
				comparator.compare(heap[left], heap[position]) > 0) {
			max = left;
		}
		
		if (right <= index && heap[right] != null &&
				comparator.compare(heap[right], heap[max]) > 0) {
			max = right;
		}
		
		if (max != position) {
			Util.swap(heap, max, position);
			heapify(max);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		// TODO Implemente a insercao na heap aqui.
		if (element == null) return;
		
		heap[++index] = element;
		int i = index;
		
		while (i != 0) {
		//while (i > 0 && comparator.compare(heap[parent(i)], heap[i]) < 0) {
			i = parent(i);
			heapify(i);
			
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if (array == null || array.length <= 0) return;
		
		heap = Arrays.copyOf(array, array.length);
		index = array.length - 1;
		
		for (int i = parent(index); i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) return null;
		
		T removedValue = rootElement();
		Util.swap(heap, ZERO, index--);
		heapify(ZERO);
		
		return removedValue;
	}

	@Override
	public T rootElement() {
		if (isEmpty()) return null;
		
		return heap[ZERO];
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array == null || array.length <= 0) return array;
		
		int indexAux = index;
		T[] heapAux = getHeap();
		Comparator<T> comparatorAux = getComparator();
		
		comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
			
		};
		
		buildHeap(array);
		T[] result = (T[])new Comparable[array.length];
		
		for (int i = array.length - 1; i >= 0; i--) {
			result[i] = extractRootElement();
		}
		
		heap = heapAux;
		index = indexAux;
		comparator = comparatorAux;
		
		return result;
	}
	
	//Extra!
	public T[] elementsByLevel(int level) {
		int index = (int)Math.pow(2, level-1) - 1;
		int order = (int)Math.pow(2, level);
		
		int exe = heap.length - index;
	
		if (level == 1) {
			T[] array = (T[])new Comparable[1];
			array[0] = heap[0];
			return array;
		}
		
		ArrayList<T> array = new ArrayList();
		
		for (int i = index; i <= exe; i++) {
			if (heap[i] != null) {
				array.add(heap[i]);
			}
		}
//		while (heap[index] != null && (index <= order)) {
//			array.add(heap[index]);
//			index++;
//		}
		
		T[] result = (T[])new Comparable[array.size()];
		
		for (int i = 0; i < array.size(); i++) {
			result[i] = array.get(i);
		}
		
		return result;
		
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
