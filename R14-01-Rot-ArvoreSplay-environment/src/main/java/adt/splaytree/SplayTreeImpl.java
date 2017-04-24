package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements SplayTree<T> {
	
	private void splay(BSTNode<T> node) {
		if (node == null) return;
		
		if (getParent(node) == null) {
			root = node;
			return;
			
		} 
		
		if (getParent(node).equals(root)) {
			if (node.equals(node.getParent().getLeft())) {
				zigRight(node);
			} else {
				zigLeft(node);
			}
			root = node;
			
		} else if (node.equals(node.getParent().getLeft())&& 
				node.getParent().equals(node.getParent().getParent().getLeft())) {
			zigZigRight(node);
			splay(node);
			
		} else if(node.equals(node.getParent().getRight())&& 
				node.getParent().equals(node.getParent().getParent().getRight())){
			zigZigLeft(node);
			splay(node);
			
		} else if(node.equals(node.getParent().getLeft()) && 
				node.getParent().equals(node.getParent().getParent().getRight())){
			zigZagRightLeft(node);
  			splay(node);
  			
 		} else if(node.equals(node.getParent().getRight()) && 
 				node.getParent().equals(node.getParent().getParent().getLeft())){
 			zigZagLeftRight(node);
 			splay(node);
 			
  		}
	}
	
	private void zigLeft(BSTNode<T> node) {
		leftRotacion(getParent(node));
	}
	
	private void zigRight(BSTNode<T> node) {
		rightRotacion(getParent(node));
	}
	
	private void zigZigLeft(BSTNode<T> node) {
		leftRotacion(getParent(getParent(node)));
		leftRotacion(getParent(node));
	}
	
	private void zigZigRight(BSTNode<T> node) {
		rightRotacion(getParent(getParent(node)));
		rightRotacion(getParent(node));
	}
	
	private void zigZagLeftRight(BSTNode<T> node) {
		leftRotacion(getParent(node));
		rightRotacion(getParent(node));
	}
	
	private void zigZagRightLeft(BSTNode<T> node) {
		rightRotacion(getParent(node));
		leftRotacion(getParent(node));
	}
	
	@Override
	public void insert(T element) {
		if (element == null) return;
		
		super.insert(element);
		BSTNode<T> node = super.search(element);
		splay(node);		
	}
	
	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		
		if (!node.isEmpty()) {
			splay(node);
		} else {
			splay(getParent(node));
		}
		
		return node;
	}
	
	@Override
	public void remove(T element) {
		if (element == null) return;
		
		BSTNode<T> node = super.search(element, root);
		BSTNode<T> parent = getParent(node);
		
		if (node.isEmpty()) {
			splay(getParent(node));
		} else {
			super.remove(element);
			splay(parent);
		}
	}
	
	private void leftRotacion(BSTNode<T> node) {
		Util.leftRotation(node);
	}
	
	private void rightRotacion(BSTNode<T> node) {
		Util.rightRotation(node);
	}
	
	private BSTNode<T> getParent(BSTNode<T> node) {
		return (BSTNode<T>)node.getParent();
	}

}
