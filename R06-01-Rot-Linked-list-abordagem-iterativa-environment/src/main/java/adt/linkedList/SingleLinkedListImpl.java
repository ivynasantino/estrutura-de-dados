package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (head.isNIL());
	}

	@Override
	public int size() {
		int size = 0;
		
		SingleLinkedListNode<T> auxHead = head;
		while (!auxHead.isNIL()) {
			auxHead = auxHead.getNext();
			size++;
		}
	
		return size;
	}

	@Override
	public T search(T element) {
		if (element == null) return null;
		
		SingleLinkedListNode<T> nodeHead = head;
		
		while (!nodeHead.isNIL()) {
			if (nodeHead.getData().equals(element)) {
				return nodeHead.getData();
			}
			
			nodeHead = nodeHead.getNext();
		}
		
		return null;
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		
		SingleLinkedListNode<T> nodeHead = head;
		
		while (!nodeHead.isNIL()) {
			nodeHead = nodeHead.getNext();
		}
		
		nodeHead.setData(element);
		nodeHead.setNext(new SingleLinkedListNode<T>());
	}

	@Override
	public void remove(T element) {
		if (isEmpty()) return;
		if (element == null) return;
		
		SingleLinkedListNode<T> nodeHead = head;
		
		if (head.getData().equals(element)) {
			head = head.next;
			
		} else {
			SingleLinkedListNode<T> previous = new SingleLinkedListNode<>();
			
			while (!nodeHead.isNIL()) {
				if (nodeHead.getData().equals(element)) {
					previous.setNext(nodeHead.getNext());
					nodeHead.setNext(new SingleLinkedListNode<T>());
					break;
				}
				
				previous = nodeHead;
				nodeHead = nodeHead.getNext();
			}
		}
	}

	@Override
	public T[] toArray() {
		int size = size();
		T[] array = (T[])new Object[size];
		SingleLinkedListNode<T> nodeHead = head;
		int index = 0;
		
		while (!nodeHead.isNIL()) {
			array[index] = nodeHead.getData();
			nodeHead = nodeHead.getNext();
			index++;
 		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
