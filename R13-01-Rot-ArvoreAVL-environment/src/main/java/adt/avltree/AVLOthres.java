package adt.avltree;

import adt.bst.BSTNode;

public class AVLOthres<T extends Comparable<T>> extends AVLTreeImpl<T> {

	public void insertNotRotacion(T[] array) {
		insertNotRotacion(array, 0, array.length - 1);
	}
	
	private void insertNotRotacion(T[] array, int ini, int last) {
		int mid = (ini + last)/2;
		if (array == null || array[mid] == null || 
				ini > last || last < 0) return;
		insert(array[mid]);
		
		insertNotRotacion(array, ini, mid - 1);
		insertNotRotacion(array, mid + 1, last);
	}
	
	public boolean isAvl() {
		if (root == null || root.isEmpty()) {
			return false;
		}
		
		return isBST() && isAvl(root);
	}
	
	// No máximo 2 filhos, no.left < parent < no.right
	private boolean isBST() {
		if (root == null || root.isEmpty()) {
			return false;
		}
		
		return isBST(root);
	}
	
	private boolean isBST(BSTNode<T> node) {
		if (node == null) return false;
		
		else if (node.isEmpty()) return true;
		
		else if (!node.getLeft().isEmpty()) {
			
		}
		
		return false;
	}
	
	private boolean isAvl(BSTNode<T> node) {
		return false;
	}
	
}
