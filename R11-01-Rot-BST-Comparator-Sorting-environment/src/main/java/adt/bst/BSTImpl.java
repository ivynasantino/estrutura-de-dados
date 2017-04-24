package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int INVALID = -1;
	
	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return INVALID;
			
		} else {
			return 1 + Math.max(height((BSTNode<T>)node.getLeft()), 
					height((BSTNode<T>)node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (element == null || node == null || node.isEmpty()) {
			return new BSTNode<T>();
			
		} else {
			if (element.equals(node.getData())) {
				return node;
				
			} else if (element.compareTo(node.getData()) < 0){
				return search(element, (BSTNode<T>)node.getLeft());
			} else {
				return search(element, (BSTNode<T>)node.getRight());
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		insert(element, root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			
		} else {
			if (element.compareTo(node.getData()) > 0) {
				insert(element, (BSTNode<T>)node.getRight());
				
			} else if (element.compareTo(node.getData()) < 0) {
				insert(element, (BSTNode<T>)node.getLeft());
			}
		}
	}
	
	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (isEmpty()) {
			return null;
			
		} else {
			if (node.getRight().isEmpty()) {
				return node;
				
			} else {
				return maximum((BSTNode<T>)node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (isEmpty()) {
			return null;
			
		} else {
			if (node.getLeft().isEmpty()) {
				return node;
				
			} else {
				return minimum((BSTNode<T>) node.getLeft());
			}
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		
		if (node.isEmpty()) {
			return null;
		}
		
		else if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		}
		
		else {
		
			BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
			
			while (nodeAux != null && node.equals(nodeAux.getRight())) {
				node = nodeAux;
				nodeAux = (BSTNode<T>) node.getParent();
			}
		
			return nodeAux;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		
		if (node.isEmpty()) {
			return null;
		}
		
		else if (!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		}
		
		else {
			BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
			
			while (nodeAux != null && node.equals(nodeAux.getLeft())) {
				node = nodeAux;
				nodeAux = (BSTNode<T>) node.getParent();
			}
			
			return nodeAux;
		}
	}

	@Override
	public void remove(T element) {
		if (element == null) return;
		
		BSTNode<T> node = search(element);
		
		if (node == null) return;
		
		int degree = degree(node);
		
		if (degree == ZERO) { //Leaf
			node.setData(null);
		} else if (degree == ONE) {
			removeOneDegree(node);
		} else if (degree == TWO){
			removeTwoDegrees(node);
		} else {
			return;
		}
		
	}
	
	/**
	 * Calculate node degree
	 * @param node
	 * @return degree
	 */
	private int degree(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return INVALID;
		}
		
		if (node.isLeaf()) { //Degree zero
			return ZERO;
			
		} else if (oneSon(node)) {
			return ONE;
			
		} else {
			return TWO;
		}
	}
	
	/**
	 * Remove nodes with one degree
	 * @param node
	 */
	private void removeOneDegree(BSTNode<T> node) {
		if (node.getParent() == null) { //Root
			if (!node.getLeft().isEmpty()) {
				node.getLeft().setParent(null);
				root = (BSTNode<T>) node.getLeft();
				return;
				
			} else {
				node.getRight().setParent(null);
				root = (BSTNode<T>) node.getRight();
				return;
			}
		} 
		
		BSTNode<T> nodeAux = null;
			
		if (!node.getRight().isEmpty()) {
			nodeAux = (BSTNode<T>) node.getRight();
		} else {
			nodeAux = (BSTNode<T>) node.getLeft();
		}
			
		nodeAux.setParent(node.getParent());
			
		if (node.equals(node.getParent().getLeft())) {
			node.getParent().setLeft(nodeAux);
		
		} else if (node.equals(node.getParent().getRight())){
			node.getParent().setRight(nodeAux);
		}
		
	}
	
	private void removeTwoDegrees(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		
		if (sucessor == null) return;
		
		int degree = degree(sucessor);
		node.setData(sucessor.getData());
		
		if (degree == ZERO) {
			sucessor.setData(null);
		} else if (degree == ONE) {
			removeOneDegree(sucessor);
		} else {
			removeTwoDegrees(sucessor);
		}
	}
	
	/**
	 * Node with one son
	 * @param node
	 * @return boolean
	 */
	private boolean oneSon(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			return true;
		} else if (node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
			return true;
			
		} return false;
	}
	
	@Override
	public T[] preOrder() {
		ArrayList<T> array = new ArrayList<T>();
		preOrder(root, array);
		
		int size = array.size();
		T[] result = (T[])new Comparable[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = array.get(i);
		}
		
		return result;
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> array) {
		if (node.isEmpty()) {
			return;
		}
		
		array.add(node.getData());
		preOrder((BSTNode<T>)node.getLeft(), array);
		preOrder((BSTNode<T>)node.getRight(), array);
	}

	@Override
	public T[] order() {
		ArrayList<T> array = new ArrayList<T>();
		
		order(root, array);
		
		int size = array.size();
		T[] result = (T[])new Comparable[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = array.get(i);
		}
		
		return result;
	}

	private void order(BSTNode<T> node, ArrayList<T> array) {
		if (node.isEmpty()) {
			return;
		}
		
		order((BSTNode<T>)node.getLeft(), array);
		array.add(node.getData());
		order((BSTNode<T>)node.getRight(), array);
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> array = new ArrayList<T>();
		
		postOrder(root, array);
		
		int size = array.size();
		T[] result = (T[])new Comparable[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = array.get(i);
		}
		
		return result;
	}
	
	private void postOrder(BSTNode<T> node, ArrayList<T> array) {
		if (node.isEmpty()) return;
		
		postOrder((BSTNode<T>)node.getLeft(), array);
		postOrder((BSTNode<T>)node.getRight(), array);
		array.add(node.getData());
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
