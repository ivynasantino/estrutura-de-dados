package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		} else {
			this.top.insertFirst(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		} else {
			RecursiveDoubleLinkedListImpl<T> lista = (RecursiveDoubleLinkedListImpl) this.top;
			T element = lista.getData();
			lista.removeFirst();
			return element;
		}
	}

	@Override
	public T top() {
		if (this.isEmpty()) {
			return null;
		} else {

			RecursiveDoubleLinkedListImpl<T> lista = (RecursiveDoubleLinkedListImpl) this.top;
			return lista.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return (top.isEmpty());
	}

	@Override
	public boolean isFull() {
		return (top.size() == size);
	}

}
