package adt.rbtree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RBTest {

	private RBTree<Integer> tree;
	
	@Before
	public void setUp() {
		tree = new RBOthres<>();
		tree.insert(10);
		tree.insert(3);
		tree.insert(1);
//		tree.insert(35);
//		tree.insert(33);
//		tree.insert(2);
//		tree.insert(0);
//		tree.insert(9);
	}

	@Test
	public void testNodeBlack() {
		assertEquals(3, tree.size());
	}
}
