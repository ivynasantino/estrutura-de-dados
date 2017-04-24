package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element == null)
			return;

		if (isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<>());

		} else {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this));
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
				this.previous = null;

			} else if (this.next.next.isEmpty()) {
				this.data = this.next.data;
				this.next = this.next.next;

			} else {
				RecursiveDoubleLinkedListImpl<T> nodeNextNext = (RecursiveDoubleLinkedListImpl) this.next.next;
				this.data = this.next.data;
				this.next = this.next.next;
				nodeNextNext.previous = this;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				if (this.previous.isEmpty()) {
					this.data = null;
					this.next = null;
					this.previous = null;

				} else {
					this.previous.next = new RecursiveDoubleLinkedListImpl<T>();
					this.previous = new RecursiveDoubleLinkedListImpl<T>();
				}

			} else {
				RecursiveDoubleLinkedListImpl<T> nodeNext = 
						(RecursiveDoubleLinkedListImpl) this.next;

				nodeNext.removeLast();
			}
		}
	}

	public void remove(T element) {
		if (element == null)
			return;

		if (!isEmpty()) {
			if (this.data.equals(element)) {
				if ((this.previous.isEmpty() && this.next.isEmpty()) 
						|| this.next.isEmpty()) {

					this.data = null;
					this.next = null;
					this.previous = null;

				} else {

					RecursiveDoubleLinkedListImpl<T> nodeNext = 
							(RecursiveDoubleLinkedListImpl) this.next;

					this.data = this.next.data;
					this.next = this.next.next;
					nodeNext.previous = this.previous;
				}
			} else {
				this.next.remove(element);
			}
		}
	}
	
	public void insert(T element) {
		if (element == null) return;
		
		if (this.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> nodeEmptyNext = new RecursiveDoubleLinkedListImpl<T>();
			RecursiveDoubleLinkedListImpl<T> nodeEmptyPrevious = new RecursiveDoubleLinkedListImpl<T>();

			this.data = element;
			this.next = nodeEmptyNext;
			this.previous = nodeEmptyPrevious;

		} else {
			insert(element, this);
		}
	}
	
	private void insert(T element, RecursiveDoubleLinkedListImpl<T> node) {
		RecursiveDoubleLinkedListImpl<T> nodeNext = (RecursiveDoubleLinkedListImpl) node.next;

		if (node.next.isEmpty()) {
			node.next.data = element;
			node.next.next = new RecursiveDoubleLinkedListImpl<>();
			nodeNext.previous = node;
			
		} else {
			insert(element, nodeNext);
		}
	}
	
	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
