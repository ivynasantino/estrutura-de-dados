package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	private BSTNode<T> getLeft(BSTNode<T> node) {
		return (BSTNode<T>)node.getLeft();
	}
	
	private BSTNode<T> getRight(BSTNode<T> node) {
		return (BSTNode<T>)node.getRight();
	}
	
	private BSTNode<T> getParent(BSTNode<T> node) {
		return (BSTNode<T>)node.getParent();
	}
	
	private static final int ZERO = 0;
	private static final int BALANCE_RIGHT = 1;
	private static final int BALANCE_LEFT = -1;



	// AUXILIARY
	/**
	 * Calculates the balance of the node. Returns a positive integer if
	 * unbalanced to right or a negative integer if unbalanced to the left, zero
	 * otherwise.
	 */
	protected int calculateBalance(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return 0;
		}
		
		int heightLeft = height(getLeft(node));
		int heightRight = height(getRight(node));
			
		return heightRight - heightLeft;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (balance < BALANCE_LEFT) {
			if (calculateBalance(getLeft(node)) > ZERO) {
				leftRotation(getLeft(node));
			}
			rightRotation(node);
		} else if (balance > BALANCE_RIGHT) {
			if (calculateBalance(getRight(node)) < ZERO) {
				rightRotation(getRight(node));
			}
			leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			rebalance(node);
			rebalanceUp(getParent(node));
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}
	
	public void remove(T element) {
		if (element == null) return;
		
		BSTNode<T> node = search(element);
		
		if (node == null) return;
		
		int degree = degree(node);
		
		if (degree == ZERO) { //Leaf
			removeLeaf(node);
		} else if (degree == ONE) {
			removeOneDegree(node);
		} else if (degree == TWO){
			removeTwoDegrees(node);
		} else {
			return;
		}
	}
	
	private void removeLeaf(BSTNode<T> node) {
		node.setData(null);
		rebalanceUp(node);
	}
	
	private void removeOneDegree(BSTNode<T> node) {
		if (node.getParent() == null) { //Root
			if (!node.getLeft().isEmpty()) {
				node.getLeft().setParent(null);
				root = (BSTNode<T>) node.getLeft();
				rebalanceUp(root);
				return;
				
			} else {
				node.getRight().setParent(null);
				root = (BSTNode<T>) node.getRight();
				rebalanceUp(root);
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
		
		rebalanceUp(nodeAux);
	}
	
	private void removeTwoDegrees(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		
		if (sucessor == null) return;
		
		int degree = degree(sucessor);
		node.setData(sucessor.getData());
		
		if (degree == ZERO) {
			removeLeaf(sucessor);
		} else if (degree == ONE) {
			removeOneDegree(sucessor);
		} else {
			removeTwoDegrees(sucessor);
		}
	}
	
	public void insert(T element) {
		if (element == null) return;
		insert(root, element);
	}
	
	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			
			rebalanceUp(node);
			return;
			
		} else if (element.compareTo(node.getData()) > ZERO) {
			insert((BSTNode<T>)node.getRight(), element);
			
		} else if (element.compareTo(node.getData()) < ZERO) {
			insert((BSTNode<T>)node.getLeft(), element);
		}
	}
}
