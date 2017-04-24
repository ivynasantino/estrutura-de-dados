package adt.skipList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SkipTest {

	SkipOthres<String> skip;
	SkipListNode<String>[] array;

	@Before
	public void setUp() {
		skip = new SkipOthres<String>(4);
	}
	
	@Test
	public void searchTest() {
		skip.insert(10, "A", 2);
		skip.insert(20, "B", 1);
		skip.insert(0, "C", 1);
		skip.insert(15, "D", 3);
		skip.insert(5, "E", 2);

		assertEquals("A", skip.searchRec(10).getValue());
		assertEquals("B", skip.searchRec(20).getValue());
		assertEquals("C", skip.searchRec(0).getValue());
		assertEquals("D", skip.searchRec(15).getValue());
		assertEquals("E", skip.searchRec(5).getValue());

		assertEquals(null, skip.searchRec(-10));
		assertEquals(null, skip.searchRec(30));
		assertEquals(null, skip.searchRec(9));
		
		skip.insert(25, "F", 4);
		assertEquals("F", skip.searchRec(25).getValue());
		assertEquals(6, skip.size());
		
		skip.remove(25);
		assertEquals(5, skip.size());	
		
	}
	
	
	@Test
	public void goodSkipTest() {
		
//		skip.insert(1, "A", 1);
//		skip.insert(2, "B", 2);
//		skip.insert(3, "C", 1);
//		skip.insert(4, "D", 3);
//		skip.insert(5, "E", 1);
//		skip.insert(6, "F", 2);
//		skip.insert(7, "G", 1);
//		
//		assertEquals(7, skip.size());
//		
//		assertTrue(skip.goodSkip(skip));
		
		skip.insert(1, "A", 1);
		skip.insert(2, "b", 2);
		skip.insert(3, "c", 1);

		assertEquals("A", skip.searchRec(1).getValue());
		assertEquals(3, skip.size());
		assertTrue(skip.goodSkip(skip));
	}
	
	@Test
	public void insertTest() {
		skip.insertRec(10, "A", 2);
		skip.insertRec(20, "B", 1);
		skip.insertRec(0, "C", 1);
		skip.insertRec(15, "D", 3);
		skip.insertRec(5, "E", 3);

		assertEquals(5, skip.size());
		
		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <0,1>, <5,3>, <10,2>, <15,3>, <20,1>, <NIL,4>]", Arrays.toString(array));
		assertEquals(0, array[0].getForward(0).getKey());
		assertEquals(5, array[0].getForward(1).getKey());
		assertEquals(5, array[0].getForward(2).getKey());
		assertEquals(5, array[1].getForward(0).getKey());
		assertEquals(10, array[2].getForward(0).getKey());
		assertEquals(10, array[2].getForward(1).getKey());
		assertEquals(15, array[2].getForward(2).getKey());
		assertEquals(15, array[3].getForward(0).getKey());
		assertEquals(15, array[3].getForward(1).getKey());
		assertEquals(20, array[4].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(2).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());

		List<String> aux = new ArrayList<>();
		for (int i = 1; i < array.length - 1; ++i)
			aux.add(array[i].getValue());

		assertEquals("[C, E, A, D, B]", aux.toString());

		skip.insert(0, "A", 1);
		skip.insert(5, "B", 3);
		skip.insert(10, "C", 2);
		skip.insert(15, "D", 3);
		skip.insert(20, "E", 1);

		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <0,1>, <5,3>, <10,2>, <15,3>, <20,1>, <NIL,4>]", Arrays.toString(array));
		assertEquals(0, array[0].getForward(0).getKey());
		assertEquals(5, array[0].getForward(1).getKey());
		assertEquals(5, array[0].getForward(2).getKey());
		assertEquals(5, array[1].getForward(0).getKey());
		assertEquals(10, array[2].getForward(0).getKey());
		assertEquals(10, array[2].getForward(1).getKey());
		assertEquals(15, array[3].getForward(0).getKey());
		assertEquals(20, array[4].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());

		aux.clear();
		for (int i = 1; i < array.length - 1; ++i)
			aux.add(array[i].getValue());

		assertEquals("[A, B, C, D, E]", aux.toString());
	}
	
	@Test
	public void testRemove() {
		skip.insertRec(10, "A", 1);
		skip.insertRec(20, "B", 2);
		skip.insertRec(0, "C", 2);
		skip.insertRec(15, "D", 3);
		skip.insertRec(5, "E", 1);

		skip.insertRec(-10, "F", 1);
		skip.insertRec(30, "G", 3);
		skip.insertRec(9, "H", 2);
		skip.insertRec(17, "I", 2);
		skip.insertRec(-2, "J", 1);

		assertEquals(10, skip.size());

		skip.remove(10);
		skip.remove(20);
		skip.remove(0);
		skip.remove(15);
		skip.remove(5);

		assertEquals(5, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <-10,1>, <-2,1>, <9,2>, <17,2>, <30,3>, <NIL,4>]", Arrays.toString(array));
		assertEquals(-10, array[0].getForward(0).getKey());
		assertEquals(9, array[0].getForward(1).getKey());
		assertEquals(30, array[0].getForward(2).getKey());
		assertEquals(-2, array[1].getForward(0).getKey());
		assertEquals(9, array[2].getForward(0).getKey());
		assertEquals(17, array[3].getForward(0).getKey());
		assertEquals(17, array[3].getForward(1).getKey());
		assertEquals(30, array[4].getForward(0).getKey());
		assertEquals(30, array[4].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(0).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(1).getKey());
		assertEquals(Integer.MAX_VALUE, array[5].getForward(2).getKey());

		skip.remove(-10);
		skip.remove(30);
		skip.remove(9);
		skip.remove(17);
		skip.remove(-2);

		assertEquals(0, skip.size());

		array = skip.toArray();
		assertEquals("[<ROOT,4,4>, <NIL,4>]", Arrays.toString(array));
		assertEquals(Integer.MAX_VALUE, array[0].getForward(0).getKey());
	}

	@Test
	public void elementsTest() {
		skip.insertRec(10, "A", 2);
		skip.insertRec(20, "B", 1);
		skip.insertRec(0, "C", 1);
		skip.insertRec(15, "D", 3);
		skip.insertRec(5, "E", 3);

		assertEquals(5, skip.size());
		
		Comparable[] a = skip.elementsByLevel(2);
		assertEquals(a.length, 1);
		Comparable[] b = skip.elementsByLevel(1);
		assertEquals(2, b.length);
		
	}
	
}
