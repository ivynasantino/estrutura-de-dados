package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	private static final int ZERO = 0;
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if (size <= ZERO) return;
		
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}
		
		return array[ZERO];
	}

	@Override
	public boolean isEmpty() {
		return (tail == -1);
	}

	@Override
	public boolean isFull() {
		return (tail == array.length - 1);
	}

	private void shiftLeft() {
		for (int i = 1; i < array.length; i++) {
			array[i-1] = array[i];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		
		if (element == null) {
			return;
		}
		array[++tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T element = array[ZERO];
		tail--;
		shiftLeft();
		
		return element;
	}

}
