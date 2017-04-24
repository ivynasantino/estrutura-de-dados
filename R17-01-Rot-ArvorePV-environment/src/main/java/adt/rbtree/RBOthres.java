package adt.rbtree;

import adt.rbtree.RBNode.Colour;

public class RBOthres <T extends Comparable<T>> extends RBTreeImpl<T>{

	public int qntBlack() {
		return qntBlack((RBNode<T>)root);
	}
	
	private int qntBlack(RBNode<T> node) {
		int cont = 0;
		
		if (node == null) {
			cont = -1;
		}
		
		else if (node.getColour() == Colour.BLACK) {
			cont = 1;
		}
		
		return cont + qntBlack((RBNode<T>)node.getRight()) + 
				qntBlack((RBNode<T>)node.getLeft());
	}
}
