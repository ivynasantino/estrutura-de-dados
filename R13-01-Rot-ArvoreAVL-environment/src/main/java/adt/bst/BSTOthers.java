package adt.bst;

import java.util.ArrayList;

public class BSTOthers<T extends Comparable<T>> extends BSTImpl<T> {
	
	public T[] descendingOrder() {
		ArrayList<T> array = new ArrayList<T>();
		descendingOrder(array, getRoot());
		
		int size = array.size();
		T[] result = (T[]) new Comparable[size];
		
		for (int i = 0; i < size; i++) {
			result[i] = array.get(i);
		}
		
		return result;
	}
	
	private void descendingOrder(ArrayList<T> array, BSTNode<T> node) {
		if (node.isEmpty()) return;
		
		descendingOrder(array, (BSTNode<T>)node.getRight());
		array.add(node.getData());
		descendingOrder(array, (BSTNode<T>)node.getLeft());
	}
	
	public int leafNumber() {
		return leafNumber(root);
	}
	
	private int leafNumber(BSTNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		}
		
		int nodeRight = leafNumber((BSTNode<T>)node.getRight());
		int nodeLeft = leafNumber((BSTNode<T>)node.getLeft());
		
		return nodeLeft + nodeRight + 1;
	}
	
	
	public boolean isDecendent(T father, T child) {
		BSTNode<T> nodeDad = search(father);
		BSTNode<T> nodeChild = search(child);
		
		if (!nodeDad.isEmpty() && !nodeChild.isEmpty()) {
			return searchChild(nodeDad, nodeChild);
		}
		return false;
	}
	
	private boolean searchChild(BSTNode<T> father, BSTNode<T> child) {
		if (!father.isEmpty() && !child.isEmpty() && (!father.getLeft().isEmpty()
			|| !father.getRight().isEmpty())) {
			if (!father.getLeft().isEmpty()
					&& father.getLeft().getData().equals(child.getData())) {
				return true;
			} else if (!father.getRight().isEmpty()
					&& father.getRight().getData().equals(child.getData())) {
				return true;
			} else if (father.getData().compareTo(child.getData()) < 0) {
				return searchChild((BSTNode<T>)father.getRight(), child);
			} else if (father.getData().compareTo(child.getData()) > 0) {
				return searchChild((BSTNode<T>)father.getLeft(), child);
			}
		} return false;
	}
	
	public int distance(T father, T son) {
		BSTNode<T> nodeDad = search(father);
		BSTNode<T> nodeChild = search(son);
		
		if (!isDecendent(nodeDad.getData(), nodeChild.getData())) {
			return -1;
		}
		
		if (!nodeDad.isEmpty() && !nodeChild.isEmpty()) {
			return distance(nodeDad, nodeChild, 1);
		}
		
		return -1;
	}
	
	private int distance(BSTNode<T> father, BSTNode<T> child, int n) {
		if (!father.isEmpty() || !child.isEmpty() || !father.getLeft().isEmpty()
				|| !father.getRight().isEmpty()) {
			if (father.getLeft().getData().equals(child.getData())) {
				return n;
			} else if (father.getRight().getData().equals(child.getData())) {
				return n;
			} else if (father.getData().compareTo(child.getData()) < 0) {
				return distance((BSTNode<T>)father.getRight(), child, ++n);
			} else if (father.getData().compareTo(child.getData()) > 0) {
				return distance((BSTNode<T>)father.getLeft(), child, ++n);
			} else {
				return -1;
			}
		} return -1;
	}
	
	public void mirror() {
		mirror(root);
	}
	
	private void mirror(BSTNode<T> node) {
		
		if (node.isEmpty()) return;
		
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		
		mirror(left);
		mirror(right);
		
		BSTNode<T> aux = node;
		aux.setLeft(right);
		aux.setRight(left);

	}
	
	public T kSmall(BST<T> tree, int k) {
		if (tree.size() < k || k < 0 || tree == null) return null;
		
		if (k == tree.size()) {
			return tree.maximum().getData();
		} else if (k == 1) {
			return tree.minimum().getData();
		}
		
		T data = tree.minimum().getData();
		int i = 1;
		
		while (data != null && k > i) {
			data = tree.sucessor(data).getData();
			i++;
		}
		
		if (i == k) {
			return data;
		}
		return null;
	}
	
}
