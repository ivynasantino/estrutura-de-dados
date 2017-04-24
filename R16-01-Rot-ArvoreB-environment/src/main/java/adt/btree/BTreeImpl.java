package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (node == null || node.isEmpty()) {
			return -1;
		}
		
		if (node.isLeaf()) {
			return 0;
		}
		
		return 1 + height(node.getChildren().get(0));
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		int size = countNodes();
		BNode<T>[] array = new BNode[size];
		
		depthLeftOrder(array, 0, getRoot());
		
		return array;
	}
	
	private int depthLeftOrder(BNode<T>[] array, int index, BNode<T> node) {
		if (!node.isEmpty()) {
			array[index++] = node;
			for (int i = 0; i < node.getChildren().size(); i++) {
				index = depthLeftOrder(array, index, node.getChildren().get(i));
			}
		}
		
		return index;
	}

	private int countNodes() {
		return countNodes(getRoot());
	}
	
	private int countNodes(BNode<T> node) {
		int count = 0;
		if (node != null || !node.isEmpty()) {
			if (node.isLeaf()) {
				count = 1;
				
			} else {
				count = 1;
				LinkedList<BNode<T>> child = node.getChildren();
				
				for (int i = 0; i < child.size(); i++) {
					count += countNodes(child.get(i));
				}
			}
		}
		return count;
	}
	
	@Override
	public int size() {
		return size(root);
	}
	
	private int size(BNode<T> node) {
		int size = 0;
		
		if (!node.isEmpty()) {
			size = node.size();
			LinkedList<BNode<T>> child = node.getChildren();
			
			for (int i = 0; i < child.size(); i++) {
				size += size(child.get(i));
			}
		}
		
		return size;
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element == null) {
			return new BNodePosition<T>();
		}
		
		return search(element, this.root);
	}

	private BNodePosition<T> search(T element, BNode<T> node) {
		int index = 0;
		
		while (index < node.getElements().size() && 
				node.getElementAt(index).compareTo(element) < 0) {
			index++;
		}
		
		if (index < node.getElements().size() 
				&& node.getElementAt(index).equals(element)) {
			return new BNodePosition<>(node, index);
			
		} else if (node.isLeaf()) {
			return new BNodePosition<T>();
			
		} else {
			return search(element, node.getChildren().get(index));
		}
	}
	
	@Override
	public void insert(T element) {
		if (element == null) return;
		
		insert(element, this.root);
	}
	
	private void insert(T element, BNode<T> node) {
		int index = 0;
		
		while (index < node.getElements().size() &&
				node.getElementAt(index).compareTo(element) < 0) {
			index++;
		}
		
		if (index >= node.getElements().size() ||
				!node.getElementAt(index).equals(element)) {
			
			if (node.isLeaf()) {
				node.addElement(element);
				
			} else {
				insert(element, node.getChildren().get(index));
			}
		
		}
		
		if (node.getMaxKeys() < node.size()) {
			split(node);
		}
	}

	private void split(BNode<T> node) {
		int midIndex = node.getElements().size()/2;
		BNode<T> left = new BNode<>(getOrder());
		BNode<T> right = new BNode<>(getOrder());
		
		for (int i = 0; i < node.size(); i++) {
			if (i < midIndex) {
				left.getElements().add(node.getElementAt(i));
				
			} else if (i > midIndex) {
				right.getElements().add(node.getElementAt(i));
			}
		}
		
		T middle = node.getElementAt(midIndex);
		
		
		if (!node.isLeaf()) {
			LinkedList<BNode<T>> child = node.getChildren();
			
			if (child.size() > 0) {
				int childIndex = 0;
				
				for (int i = 0; i < child.size(); i++) {
					if (i <= midIndex) {
						left.addChild(i, child.get(i));
						
					} else {
						right.addChild(childIndex++, child.get(i));
					}
				}
			}
		}
		
		if (node.equals(getRoot())) {
			BNode<T> newRoot = new BNode<>(getOrder());
			newRoot.addElement(middle);
			node.setParent(newRoot);
			setRoot(newRoot);
			
			newRoot.addChild(0, left);
			newRoot.addChild(1, right);
			newRoot.getChildren().get(0).setParent(newRoot);
			newRoot.getChildren().get(1).setParent(newRoot);
			
		} else {
			node.addChild(0, left);
			node.addChild(1, right);
			promote(node);
		}
		
	}

	private void promote(BNode<T> node) {
		int midIndex = node.getElements().size()/2;
		T middle = node.getElementAt(midIndex);
		
		node.getElements().clear();
		node.addElement(middle);
		
		BNode<T> parent = node.getParent();
		
		if (parent != null || !parent.isEmpty()) {
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			
			int index = parent.getChildren().indexOf(node);
			
			parent.addElement(middle);
			parent.addChild(index, node.getChildren().get(0));
			parent.addChild(index++, node.getChildren().get(1));
			
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			
			parent.getChildren().remove(node);
		}
		
	}
	
	public int getOrder() {
		return order;
	}
	
	private void setRoot(BNode<T> root) {
		this.root = root;
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
