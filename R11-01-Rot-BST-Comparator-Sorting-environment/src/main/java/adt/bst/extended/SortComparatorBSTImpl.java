package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl() {
		comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return ((Comparable<T>) o1).compareTo(o2);
			}
			
		};
	}
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		if (array == null || array.length <= 0) {
			return null;
		}
		
		for (int i = 0; i < array.length; i++) {
			insert(array[i]);
		}
		
		return order();
		
	}

	@Override
	public T[] reverseOrder() {
		ArrayList<T> array = new ArrayList<T>();
		reverseOrder(array, root);
		int size = array.size();
		T[] result = (T[])new Comparable[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = array.get(i);
		}
		return result;
	}

	private void reverseOrder(ArrayList<T> array, BSTNode<T> node) {
		if (node == null || node.isEmpty()) return;
		
		reverseOrder(array, (BSTNode<T>)node.getRight());
		array.add(node.getData());
		reverseOrder(array, (BSTNode<T>)node.getLeft());
	}
	
	public void insert(T value) {
		if (value == null) return;
		insert(value, root);
	}
	
	private void insert(T value, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			
		} else {
			if (comparator.compare(value, node.getData()) > 0) {
				insert(value, (BSTNode<T>)node.getRight());
				
			} else if (comparator.compare(value, node.getData()) < 0) {
				insert(value, (BSTNode<T>)node.getLeft());
			}
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return search(element, root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node == null || element == null || node.isEmpty()) {
			return new BSTNode<T>();
		}
		
		if (comparator.compare(element, node.getData()) == 0) {
			return node;
			
		} else if (comparator.compare(element, node.getData()) > 0) {
			return search(element, (BSTNode<T>)node.getRight());
			
		} else {
			return search(element, (BSTNode<T>)node.getLeft());
		}
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
