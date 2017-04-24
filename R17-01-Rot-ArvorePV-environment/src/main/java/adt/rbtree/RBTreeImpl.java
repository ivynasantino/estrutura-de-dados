package adt.rbtree;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {
	
	private static final Colour BLACK = RBNode.Colour.BLACK;
	private static final Colour RED = RBNode.Colour.RED;
	
	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight(getRoot());
	}
	
	private int blackHeight(RBNode<T> node) {
		int height = 1;
		
		if (node == null || node.isEmpty()) {
			return height;
		}
		
		if (!isBlack(node)) {
			height = 0;
		}
		
		return height + Math.max(blackHeight((RBNode<T>)node.getLeft()), 
				blackHeight((RBNode<T>)node.getRight()));
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() 
				&& verifyNILNodeColour() 
				&& verifyRootColour() 
				&& verifyChildrenOfRedNodes()
				&& verifyBlackHeight();
		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return getRoot().getColour() == BLACK;
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes(getRoot());
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean valid = true;
		if (node != null && !node.isEmpty() && isRed(node)) {
			valid = isBlack(getLeft(node))&& isBlack(getRight(node)); 
		}
		if (!node.isEmpty() && (!isRed(node) || valid)) {
			valid = verifyChildrenOfRedNodes(getLeft(node))
					&& verifyChildrenOfRedNodes(getRight(node));
		}
		return valid;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return verifiBlackHeight(getRoot());
	}
	
	private boolean verifiBlackHeight(RBNode<T> node) {
		boolean heightValid = node.isEmpty() 
				|| (blackHeight(getLeft(node)) == blackHeight(getRight(node)));
		boolean result = heightValid;
		if (!node.isEmpty() && heightValid) {
			result = verifiBlackHeight(getLeft(node)) && verifiBlackHeight(getRight(node));
		}
		return result;
	}

	@Override
	public void insert(T value) {
		if (value != null)
			insert(getRoot(), value);
	}

	private void insert(RBNode<T> node, T value) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setColour(RED);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);
			fixUpCase1(node);
						
		} else {
			if (node.getData().compareTo(value) > 0) {
				insert(getLeft(node), value);
			} else if (node.getData().compareTo(value) < 0) {
				insert(getRight(node), value);
			}
		}
		
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> array = new ArrayList<RBNode<T>>();
		rbPreOrder(array, (RBNode<T>)root);
		
		RBNode<T>[] result = new RBNode[array.size()];
		
		for (int i = 0; i < array.size(); i++) {
			result[i] = array.get(i);
		}
		
		return result;
	}

	private void rbPreOrder(ArrayList<RBNode<T>> array, RBNode<T> node) {
		if (node.isEmpty()) return;
		
		array.add(node);
		rbPreOrder(array, getLeft(node));
		rbPreOrder(array, getRight(node));
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(getRoot())) {
			node.setColour(BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (!isBlack(getParent(node))) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = getParent(node);
		RBNode<T> grandParent = getParent(parent);
		RBNode<T> uncle;

		if (isLeftChild(parent))
			uncle = getRight(grandParent);
		else
			uncle = getLeft(grandParent);

		if (!isBlack(uncle)) {
			parent.setColour(BLACK);
			uncle.setColour(BLACK);
			grandParent.setColour(RED);
			fixUpCase1(grandParent);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = getParent(node);

		if (!((isLeftChild(node) && isLeftChild(parent)) 
				|| (!isLeftChild(node) && !isLeftChild(parent)))) {
			
			if (isLeftChild(node))
				Util.rightRotation(parent);
			else
				Util.leftRotation(parent);
			
			fixUpCase5(parent);

		} else {
			fixUpCase5(next);
		}
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = getParent(node);
		RBNode<T> parentParent = getParent(parent);
		parent.setColour(BLACK);
		parentParent.setColour(RED);

		if (isLeftChild(node)) {
			Util.rightRotation(parentParent);
		} else {
			Util.leftRotation(parentParent);
		}
	}

	@Override
	public RBNode<T> getRoot() {
		return (RBNode<T>) root;
	}

	protected RBNode<T> getParent(BSTNode<T> node) {
		return (RBNode<T>) node.getParent();
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.equals(getParent(node).getLeft());
	}

	protected RBNode<T> getRight(BTNode<T> node) {
		return (RBNode<T>) node.getRight();
	}

	protected RBNode<T> getLeft(BTNode<T> node) {
		return (RBNode<T>) node.getLeft();
	}
	
	private boolean isBlack(RBNode<T> node) {
		return node.getColour().equals(BLACK);
	}
	
	private boolean isRed(RBNode<T> node) {
		return node.getColour().equals(RED);
	}
}
