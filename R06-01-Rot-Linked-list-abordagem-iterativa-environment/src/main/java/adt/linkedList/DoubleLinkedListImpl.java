package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super();
		this.head = new DoubleLinkedListNode<>();
		this.last = (DoubleLinkedListNode<T>)this.head;
	}
	
	@Override
	public void insert(T element) {
		if (element == null) return;
		  
		DoubleLinkedListNode<T> elem = new 
				DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), last);

		if (isEmpty()) {
			super.head = last = elem;
		} else {
			last.next = elem;
			last = elem;
		}
		
	}
	
	@Override
	public void remove(T element) {
		if (element == null) return;

		if (isEmpty()) return;

		if (head.getData().equals(element)) {
			head = head.getNext();

		} else if (size() > 1) {
			SingleLinkedListNode<T> aux = head.getNext();

			while (!aux.isNIL() && !aux.getData().equals(element)) {
				aux = aux.getNext();
			}

			if (aux.getData().equals(element)) {

				DoubleLinkedListNode<T> toRemove = (DoubleLinkedListNode<T>) aux,
						previous = toRemove.getPrevious(), next = (DoubleLinkedListNode<T>) toRemove.getNext();

				if (!next.isNIL()) {
					next.setPrevious(previous);
				}

				previous.setNext(next);
			}
		}
	
	}
	
	@Override
	public void insertFirst(T element) {
		if (element == null) return;
		
		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>();
		
		SingleLinkedListNode<T> next = new DoubleLinkedListNode<>(element, null, aux);
		next.next = getHead();
		last.previous = (DoubleLinkedListNode<T>)next;
		
		if(getHead().isNIL()) {
			last = (DoubleLinkedListNode<T>) next;
		}
		
		this.head = next;
	}

	@Override
	public void removeFirst() {
		if(isEmpty()) return;
		
		if(!getHead().isNIL()) {
			head = getHead().getNext();
				
			if(getHead().isNIL()) {
				last = (DoubleLinkedListNode<T>) getHead();
			}
			
			SingleLinkedListNode<T> aux = new DoubleLinkedListNode<>();
			last.previous = (DoubleLinkedListNode<T>) aux;
		}
	}

	@Override
	public void removeLast() {
		if (isEmpty()) return;
		  
        DoubleLinkedListNode<T> lastPrev = getLast().getPrevious();
        lastPrev.setNext(new DoubleLinkedListNode<T>());

        if (size() == 1) {
            head = lastPrev;
       }

       last = lastPrev;
	
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
