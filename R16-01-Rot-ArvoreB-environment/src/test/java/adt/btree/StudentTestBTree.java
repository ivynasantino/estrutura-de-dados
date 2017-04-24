package adt.btree;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StudentTestBTree {

	protected BTree<Integer> tree1;

	@Before
	public void setUp() throws Exception {
		tree1 = new BTreeImpl<Integer>(4);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tree1.isEmpty());
	}

	@Test
	public void testHeight() {
		assertEquals(-1, tree1.height());
		tree1.insert(2);
		assertEquals(0, tree1.height());
	}

	@Test
	public void testDepthLeftOrder() {
		tree1.insert(2);
		tree1.insert(4);
		tree1.insert(6);
		tree1.insert(8);
		try {
			assertEquals("[[6], [2, 4], [8]]",
					Arrays.toString(tree1.depthLeftOrder()));
		} catch (AssertionError e) {
			assertEquals("[[4], [2], [6, 8]]",
					Arrays.toString(tree1.depthLeftOrder()));
		}
	}

	@Test
	public void testSize() {
		assertEquals(0, tree1.size());
		tree1.insert(18);
		assertEquals(1, tree1.size());
	}
	
	
	@Test
	public void testDepthLeftOrder2() {

		tree1.insert(13);
		tree1.insert(9);
		tree1.insert(5);
	}

	@Test
	public void testInsert2() {

		tree1.insert(13);
		tree1.insert(9);
		tree1.insert(5);

		assertEquals(tree1.getRoot().getElementAt(0), new Integer(5));
		assertEquals(tree1.getRoot().getElementAt(1), new Integer(9));
		assertEquals(tree1.getRoot().getElementAt(2), new Integer(13));

		assertEquals(tree1.size(), 3);
		assertEquals(tree1.height(), 0);

		tree1.insert(12);

		assertEquals(tree1.size(), 4);
		assertEquals(tree1.height(), 1);

		tree1.insert(122);
		tree1.insert(3);

		assertEquals(tree1.size(), 6);
		assertEquals(tree1.height(), 1);
		
		tree1.insert(76);

		tree1.insert(10);
		assertEquals(1, tree1.height());

		tree1.insert(7);
		assertEquals(1, tree1.height());
		tree1.insert(8);
		assertEquals(1, tree1.height());
		
		tree1.insert(6);

		tree1.insert(1000);
		assertEquals(2, tree1.height());

		for (int i = 0; i < 4332; i++) {
			tree1.insert(null);
		}

	}

	@Test
	public void testAdd() {
		BTreeImpl<Integer> arvere = new BTreeImpl<>(4);
		assertEquals(arvere.size(), 0);

		assertNull(arvere.search(1).node);
		arvere.insert(0);
		arvere.insert(1);
		assertNotNull(arvere.search(1).node);
		assertNull(arvere.search(-1).node);
		arvere.insert(-1);
		assertNotNull(arvere.search(-1).node);

		assertEquals(arvere.size(), 3);
		assertEquals(arvere.height(), 0);

		arvere.insert(2);
		assertEquals(arvere.height(), 1);
		arvere.insert(-2);
		assertEquals(arvere.size(), 5);
		assertEquals(arvere.height(), 1);
		assertNotNull(arvere.search(-2).node);

		arvere.insert(3);
		arvere.insert(-3);
		assertEquals(arvere.height(), 1);
		assertEquals(arvere.size(), 7);

		arvere.insert(4);
		assertEquals(arvere.height(), 1);
		arvere.insert(5);
		arvere.insert(6);
		assertEquals(arvere.height(), 1);
		
		arvere.insert(7);
		assertEquals(arvere.size(), 11);
		assertEquals(arvere.height(), 2);
		assertEquals(arvere.search(444).node, new BNodePosition<>().node);
		assertEquals(arvere.search(7).node, new Integer(7));
		assertNotNull(arvere.search(7).node);
	}

}
