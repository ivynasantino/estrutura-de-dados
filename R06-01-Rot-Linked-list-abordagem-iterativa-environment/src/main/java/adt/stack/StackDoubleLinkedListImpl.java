package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element == null) return;
		
		if (isFull()) {
			throw new StackOverflowException();
		}
		
		top.insert(element);
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		
		T removed = top();
		top.removeLast();
		return removed;
		
	}

	@Override
	public T top() {
		T[] array = top.toArray();
		int index = top.size() - 1;
		return array[index];
	}

	@Override
	public boolean isEmpty() {
		return (top.isEmpty());
	}
	
	@Override
	public boolean isFull() {
		return (size == top.size());
	}

}
