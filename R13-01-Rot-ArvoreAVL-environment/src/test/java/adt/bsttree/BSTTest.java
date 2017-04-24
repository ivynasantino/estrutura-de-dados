package adt.bsttree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTOthers;

public class BSTTest {

	BSTOthers<Integer> tree;
	
	@Before
	public void setUp() {
		tree = new BSTOthers<Integer>();
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(1);
		tree.insert(7);
		tree.insert(15);
		tree.insert(30);
	}

	@Test
	public void orderTest() {
		assertEquals(tree.size(), 7);
		assertEquals(tree.order(), new Integer[] {1, 5, 7, 10, 15, 20, 30});
	}
	
	@Test
	public void descendingOrderTest() {
		assertEquals(tree.descendingOrder(), new Integer[] {30, 20, 15, 10, 7, 5, 1});
	}
	
	@Test
	public void leafTest() {
		assertEquals(tree.leafNumber(), 7);
		tree.remove(30);
		assertEquals(tree.leafNumber(), 6);
	}
	
	@Test
	public void isDecendentTest() {
		assertTrue(tree.isDecendent(10, 20));
		assertTrue(tree.isDecendent(10, 5));
		assertTrue(tree.isDecendent(10, 1));
		assertTrue(tree.isDecendent(10, 30));
		assertFalse(tree.isDecendent(10, -1));
		assertTrue(tree.isDecendent(20, 15));
		assertTrue(tree.isDecendent(5, 7));
		assertFalse(tree.isDecendent(20, 1));
		assertFalse(tree.isDecendent(8, 1));
		
		tree.insert(8);
		assertEquals(tree.size(), 8);
		assertTrue(tree.isDecendent(10, 8));
		
		tree.insert(25);
		assertTrue(tree.isDecendent(10, 25));
	}
	
	@Test
	public void distanceTest() {
		assertEquals(tree.distance(10, 20), 1);
		assertEquals(tree.distance(10, 30), 2);
		assertEquals(tree.distance(20, 1), -1);
		assertEquals(tree.distance(5, 30), -1);
		assertEquals(tree.distance(10, 15), 2);
		assertEquals(tree.distance(15, -1), -1);
	}
	
	@Test
	public void mirrorTest() {
		assertEquals(tree.size(), 7);
		tree.mirror();		
		assertEquals(tree.getRoot().getLeft().getData(), new Integer(20));
		assertEquals(tree.getRoot().getRight().getData(), new Integer(5));
		assertEquals(tree.getRoot().getRight().getRight().getData(), new Integer(1));
		assertEquals(tree.getRoot().getLeft().getRight().getData(), new Integer(15));
		assertEquals(tree.getRoot().getLeft().getLeft().getData(), new Integer(30));
		assertEquals(tree.getRoot().getRight().getLeft().getData(), new Integer(7));

	}
	
	@Test
	public void kSmallTest() {
		tree.insert(0);
		assertEquals(tree.size(), 8);
		assertEquals(tree.kSmall(tree, 1), new Integer(0));
		assertEquals(tree.kSmall(tree, 4), new Integer(7));
		assertEquals(tree.kSmall(tree, 2), new Integer(1));
		assertEquals(tree.kSmall(tree, 3), new Integer(5));
		
	}
	
}