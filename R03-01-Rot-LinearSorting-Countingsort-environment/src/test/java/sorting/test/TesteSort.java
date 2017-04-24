package sorting.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.linearSorting.CountingSort;
import sorting.linearSorting.ExtendedCountingSort;

public class TesteSort {

	private final Integer[] negativos = {-1, -4, -1, -2, -10};
	private final Integer[] empty = {};
	private final Integer[] positivos = {0, 4, 1, 2, 8};
	private final Integer[] crescente = {1, 2, 4, 4, 5};
	private final Integer[] decrescente = {5, 4, 4, 1, 2};
	private final Integer[] misturados = {-1, 4, 5, 23, 2, 4, 6};
	private final Integer[] par = {0, 3, -7, 4};
	private final Integer[] limite = {0, 4, 6, 4, 2, 5};
	
	private final Integer[] expectNegativos = {-10, -4, -2, -1, -1};
	private final Integer[] expectPositivos = {0, 1, 2, 4, 8};
	private final Integer[] expectCD = {1, 2, 4, 4, 5};
	private final Integer[] expectMistura = {-1, 2, 4, 4, 5, 6, 23};
	private final Integer[] expectPar = {-7, 0, 3, 4};
	private final Integer[] expectEmpty = {};
	private final Integer[] expectLimite = {0, 4, 2, 4, 6, 5};
	private final Integer[] expectLimitePar = {0, -7, 3, 4};
	
	AbstractSorting<Integer> sorting; 
	@Before
	public void setUp() {
		sorting = null;
	}
	
	@Test
	public void testCounting() {
		sorting = new CountingSort();
		
		sorting.sort(positivos);
		sorting.sort(crescente);
		sorting.sort(decrescente);
		sorting.sort(empty);
		
		sorting.sort(limite, 2, 4);
		assertArrayEquals(expectPositivos, positivos);
		assertArrayEquals(expectCD, crescente);
		assertArrayEquals(expectCD, decrescente);
		assertArrayEquals(expectEmpty, empty);
		
		assertArrayEquals(expectLimite, limite);
		
	}
	
	@Test
	public void testNegativeCounting() {
		sorting = new ExtendedCountingSort();
		
		sorting.sort(positivos);
		sorting.sort(crescente);
		sorting.sort(decrescente);
		sorting.sort(empty);
		sorting.sort(negativos);
		sorting.sort(misturados);
		
		sorting.sort(par, 1, 3);
		sorting.sort(limite, 2, 4);
		assertArrayEquals(expectPositivos, positivos);
		assertArrayEquals(expectCD, crescente);
		assertArrayEquals(expectCD, decrescente);
		assertArrayEquals(expectEmpty, empty);
		assertArrayEquals(expectNegativos, negativos);
		assertArrayEquals(expectMistura, misturados);
		
		assertArrayEquals(expectLimite, limite);
		assertArrayEquals(expectLimitePar, par);
	}
	

}
