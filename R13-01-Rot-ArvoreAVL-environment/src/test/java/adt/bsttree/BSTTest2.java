package adt.bsttree;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.bst.BSTImpl;

public class BSTTest2 {
	
	@Test
	public void customTest() {
		BSTImpl<Integer> bst = new BSTImpl<>();

		// Testando todos os pre-requisitos
		assertTrue(bst.isEmpty());
		assertEquals(bst.size(), 0);
		assertEquals(bst.height(), -1);
		assertNull(bst.maximum());
		assertNull(bst.minimum());
		assertNotNull(bst.search(1));
		assertNull(bst.search(1).getData());
		assertTrue(bst.search(1).isEmpty());
		Integer[] vazio = {};
		assertArrayEquals(bst.order(), vazio);
		assertArrayEquals(bst.preOrder(), vazio);
		assertArrayEquals(bst.postOrder(), vazio);
		assertNotNull(bst.search(null));
		assertNull(bst.predecessor(null));
		assertNull(bst.sucessor(null));

		synchronized (bst) {
			int[] ns = new int[] { 100, 50, 200, 250, 150, 140, 180, 75, 25, 10, 70, 60, 73, 65, 55, 71 };
			int size = 0;
			for (int e : ns) {
				assertEquals(bst.size(), size++);
				bst.insert(e);
			}
		}

		// Os pre-requisitos nao devem mais ser validos
		assertFalse(bst.isEmpty());
		assertNotEquals(bst.size(), 0);
		assertNotEquals(bst.height(), -1);
		assertNotNull(bst.maximum());
		assertNotNull(bst.minimum());
		// Aqui eh excecao, claro
		assertNotNull(bst.search(10));
		assertNotNull(bst.search(10).getData());
		assertFalse(bst.search(10).isEmpty());
		// birl
		try {
			assertArrayEquals(bst.order(), vazio);
			fail();
		} catch (AssertionError e) {
		}
		try {
			assertArrayEquals(bst.preOrder(), vazio);
			fail();
		} catch (AssertionError e) {
		}
		try {
			assertArrayEquals(bst.postOrder(), vazio);
			fail();
		} catch (AssertionError e) {
		}

		// Testando as infos da arvere
		assertFalse(bst.isEmpty());
		assertEquals(bst.height(), 5);
		assertEquals(bst.size(), 16);
		assertTrue(bst.maximum().getData().equals(new Integer(250)));
		assertTrue(bst.minimum().getData().equals(new Integer(10)));

		assertNull(bst.sucessor(bst.maximum().getData()));
		assertNull(bst.predecessor(bst.minimum().getData()));

		assertNotNull(bst.sucessor(bst.getRoot().getData()));
		assertTrue(bst.sucessor(bst.getRoot().getData()).getData().equals(new Integer(140)));
		assertNotNull(bst.predecessor(bst.getRoot().getData()));
		assertTrue(bst.predecessor(bst.getRoot().getData()).getData().equals(new Integer(75)));

		assertNotNull(bst.search(null));
		assertTrue(bst.search(null).isEmpty());
		assertNull(bst.predecessor(null));
		assertNull(bst.sucessor(null));

		bst.insert(null);
		assertEquals(bst.height(), 5);
		assertEquals(bst.size(), 16);

		bst.remove(null);
		assertEquals(bst.height(), 5);
		assertEquals(bst.size(), 16);

		assertTrue(bst.predecessor(55).getData().equals(new Integer(50)));
		assertTrue(bst.predecessor(71).getData().equals(new Integer(70)));
		assertTrue(bst.sucessor(10).getData().equals(new Integer(25)));
		assertTrue(bst.predecessor(140).getData().equals(new Integer(100)));
		assertNull(bst.sucessor(250));
		assertNull(bst.predecessor(10));
		assertTrue(bst.sucessor(150).getData().equals(new Integer(180)));
		assertTrue(bst.predecessor(50).getData().equals(new Integer(25)));
		
		assertTrue(bst.predecessor(50).getData().equals(new Integer(25)));
		bst.remove(25);
		assertEquals(bst.height(), 5);
		assertEquals(bst.size(), 15);
		assertTrue(bst.predecessor(50).getData().equals(new Integer(10)));

		bst.remove(50);
		assertEquals(bst.height(), 5);
		assertEquals(bst.size(), 14);
		assertTrue(bst.sucessor(10).getData().equals(new Integer(55)));
		
		assertEquals(bst.height(), 5);
		bst.insert(135);
		assertEquals(bst.height(), 5);
		bst.insert(130);
		assertEquals(bst.height(), 5);
		bst.insert(125);
		assertEquals(bst.height(), 6);
		bst.insert(120);
		assertEquals(bst.height(), 7);
		
		assertTrue(bst.predecessor(120).getData().equals(new Integer(100)));
		assertTrue(bst.sucessor(120).getData().equals(new Integer(125)));
		
		assertEquals(bst.size(), 18);
		assertFalse(bst.search(71).isEmpty());
		bst.remove(71);
		assertTrue(bst.search(71).isEmpty());
		bst.remove(73);
		assertNull(bst.predecessor(73));
		assertNull(bst.sucessor(71));
		assertEquals(bst.size(), 16);
		assertEquals(bst.height(), 7);
		
		bst.remove(55);
		bst.remove(65);
		assertEquals(bst.size(), 14);
		assertEquals(bst.height(), 7);
		
		bst.remove(60);
		bst.remove(75);
		assertEquals(bst.size(), 12);
		assertEquals(bst.height(), 7);
		
		assertTrue(bst.sucessor(70).getData().equals(100));
		assertTrue(bst.predecessor(70).getData().equals(10));
		
		bst.remove(200);
		assertEquals(bst.size(), 11);
		assertEquals(bst.height(), 7);
		
		bst.remove(250);
		assertEquals(bst.size(), 10);
		assertEquals(bst.height(), 6);
		
		bst.remove(150);
		assertEquals(bst.size(), 9);
		assertEquals(bst.height(), 6);
		
		bst.remove(180);
		assertEquals(bst.size(), 8);
		assertEquals(bst.height(), 5);
		
		assertTrue(bst.sucessor(100).getData().equals(120));
		assertTrue(bst.predecessor(125).getData().equals(120));
		assertTrue(bst.predecessor(120).getData().equals(100));
		
		bst.remove(100);
		assertEquals(bst.size(), 7);
		assertEquals(bst.height(), 4);
		
		assertTrue(bst.getRoot().getData().equals(120));
		assertTrue(bst.search(100).isEmpty());
		assertNull(bst.predecessor(100));
		assertNull(bst.sucessor(100));
		assertTrue(bst.predecessor(120).getData().equals(70));
		assertTrue(bst.sucessor(120).getData().equals(125));
		
		bst.remove(120);
		assertEquals(bst.size(), 6);
		assertEquals(bst.height(), 3);
		
		assertTrue(bst.getRoot().getData().equals(125));
		assertTrue(bst.search(120).isEmpty());
		assertNull(bst.predecessor(120));
		assertTrue(bst.minimum().getData().equals(10));
		assertTrue(bst.maximum().getData().equals(140));
		
		bst.remove(10);
		bst.remove(135);
		bst.remove(130);
		
		assertTrue(bst.maximum().getData().equals(140));
		assertTrue(bst.minimum().getData().equals(70));
		assertEquals(bst.size(), 3);
		assertEquals(bst.height(), 1);
		assertNull(bst.sucessor(140));
		assertNull(bst.predecessor(70));
		assertTrue(bst.sucessor(bst.getRoot().getData()).getData().equals(140));
		assertTrue(bst.predecessor(bst.getRoot().getData()).getData().equals(70));
		
		bst.remove(140);
		bst.remove(70);
		
		assertEquals(bst.height(), 0);
		assertEquals(bst.size(), 1);
		assertTrue(bst.maximum().equals(bst.minimum()));
		
		bst.remove(bst.getRoot().getData());
		// Voltamos ao começo
		assertTrue(bst.isEmpty());
		assertEquals(bst.size(), 0);
		assertEquals(bst.height(), -1);
		assertNull(bst.maximum());
		assertNull(bst.minimum());
		assertNotNull(bst.search(1));
		assertNull(bst.search(1).getData());
		assertTrue(bst.search(1).isEmpty());
		assertArrayEquals(bst.order(), vazio);
		assertArrayEquals(bst.preOrder(), vazio);
		assertArrayEquals(bst.postOrder(), vazio);
		assertNotNull(bst.search(null));
		assertNull(bst.predecessor(null));
		assertNull(bst.sucessor(null));
	}

}
