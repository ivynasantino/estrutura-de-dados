package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return (data == null && next == null);
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} 
		return 1 + this.next.size();
	}

	@Override
	public T search(T element) {
		if (element == null) return null;
		
		if (isEmpty()) {
			return null;
		} else {
			if (this.data.equals(element)) {
				return element;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<>();
			
		} else {
			next.insert(element);
		}
		
	}

	@Override
	public void remove(T element) {
		if (element == null) return;
		
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				this.setData(this.next.data);
				this.setNext(next.next);
				
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		int size = size();
		T[] array = (T[])new Comparable[size];
		toArray(array, 0, this);
		
		return array;
	}

	private void toArray(T[] array, int index, 
			RecursiveSingleLinkedListImpl<T> node) {
		if (!node.isEmpty()) {
			array[index] = node.data;
			toArray(array, ++index, node.next);
		}
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
