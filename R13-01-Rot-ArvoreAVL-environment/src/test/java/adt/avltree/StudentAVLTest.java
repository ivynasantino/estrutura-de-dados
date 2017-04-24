package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	private AVLTree<Integer> testAvl;

	private AVLOthres<Integer> notRotacion;
	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
		testAvl = new AVLTreeImpl<>();
		notRotacion = new AVLOthres<>();
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
	}

	@Test
	public void testInsert() {
		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());

		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());

		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());
	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);

		avl.remove(-1);
		assertEquals(4, avl.size());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());
	}
	
	@Test
	public void testRandom() {
		assertEquals(testAvl.size(), 0);
		assertTrue(testAvl.isEmpty());
		
		testAvl.insert(40);
		assertFalse(testAvl.isEmpty());
		testAvl.insert(20);
		assertEquals(testAvl.getRoot().getData(), new Integer(40));
		testAvl.insert(10); // rotacao
		
		assertEquals(testAvl.getRoot().getData(), new Integer(20));
		assertEquals(testAvl.getRoot().getLeft().getData(), new Integer(10));
		assertEquals(testAvl.getRoot().getRight().getData(), new Integer(40));
		
		testAvl.insert(5); //rotacao
		assertEquals(testAvl.getRoot().getData(), new Integer(20));
		
		testAvl.insert(50); //rotacao
		assertEquals(testAvl.getRoot().getData(), new Integer(20));
		
		testAvl.remove(5);
		assertEquals(testAvl.size(), 4);
		testAvl.remove(10);
		assertEquals(testAvl.size(), 3);
		assertEquals(testAvl.getRoot().getData(), new Integer(40));
		
		testAvl.remove(40);
		testAvl.remove(50);
		testAvl.remove(20);
		
		assertEquals(testAvl.size(), 0);
		
		// [1,2,3,4,5,6,7]
		// 1-2-3 rotacao
		testAvl.insert(1);
		testAvl.insert(2);
		assertEquals(testAvl.getRoot().getData(), new Integer(1));
		assertEquals(testAvl.getRoot().getRight().getData(), new Integer(2));
		assertEquals(testAvl.order(), new Integer[] {1,2});
		
		testAvl.insert(3); //rotacao
		assertEquals(testAvl.getRoot().getData(), new Integer(2));
		assertEquals(testAvl.getRoot().getLeft().getData(), new Integer(1));
		assertEquals(testAvl.getRoot().getRight().getData(), new Integer(3));
		assertEquals(testAvl.order(), new Integer[] {1,2,3});
		
		// 4-5 rotacao
		testAvl.insert(4);
		testAvl.insert(5); //rotacao
		assertEquals(testAvl.getRoot().getData(), new Integer(2));
		//assertEquals(testAvl.order(), new Integer[] {1,2,3,4,5});
		// 6-7 rotacao
		testAvl.insert(6);
		testAvl.insert(7);
		assertEquals(testAvl.getRoot().getData(), new Integer(4));
		
		//assertEquals(testAvl.order(), new Integer[] {1,2,3,4,5,6,7});
		
	}
	
	@Test
	public void NotRotacionTest() {
		Integer[] array = {1,2,3,4,5,6,7};
		
		notRotacion.insertNotRotacion(array);
		assertEquals(notRotacion.getRoot().getData(), new Integer(4));
		assertEquals(notRotacion.getRoot().getRight().getData(), new Integer(6));
		assertEquals(notRotacion.getRoot().getLeft().getData(), new Integer(2));
		assertEquals(notRotacion.getRoot().getRight().getRight().getData(), new Integer(7));
		assertEquals(notRotacion.getRoot().getRight().getLeft().getData(), new Integer(5));
		
		
		//assertEquals(notRotacion.order(), new Integer[] {1,2,3,4,5,6,7});
	}
}
