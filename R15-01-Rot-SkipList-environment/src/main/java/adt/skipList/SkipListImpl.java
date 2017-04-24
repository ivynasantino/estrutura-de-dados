package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;
		
		for (int i = (height - 1); i >= 0; i--) {
			while (node.getForward(i) != null && 
					node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
			
			update[i] = node;
		}
		
		node = node.getForward(0);
		
		if (node.getKey() == key) {
			if (node.height() == height) {
				node.setValue(newValue);
			} else {
				remove(key);
				insert(key, newValue, height);
			}
		} else {
			int newLevel = height;
			
			if (newLevel > this.height()) {
				for (int i = height(); i < newLevel; i++) {
					update[i] = root;
				}
				
				int newHeight = node.height();
				newHeight = newLevel;
			}
			
			node = new SkipListNode<T>(key, newLevel, newValue);
			
			for (int i = 0; i < newLevel; i++) {
				if (update[i].getForward(i) == null) {
					node.getForward()[i] = NIL;
				} else {
					node.getForward()[i] = update[i].getForward(i);
				}
				
				update[i].getForward()[i] = node;
			}
		}
		

	}

	@Override
	public void remove(int key) {
		 this.remove(key, this.root);
	}
	
	private void remove(int key, SkipListNode<T> node) {
		for (int index = node.height() - 1; index >= 0; index--) {
			if (node.forward[index] == null) continue;

			if (node.forward[index].key == key) {
				node.forward[index] = node.forward[index].forward[index];
	       } else if (node.forward[index].key < key) {
	    	   this.remove(key, node.forward[index]);
	           break;
	       }
		}
	}
	

	@Override
	public int height() {
		for (int i = maxHeight - 1; i >= 0; i--) {
			if (!root.getForward(i).equals(NIL)) {
				return i + 1;
			}
		}
		
		return 0;
	}
	
	

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> node = root;
		
		for (int i = (height() - 1); i >= 0; i--) {
			while (node.forward[i].getKey() < key) {
				node = node.forward[i];
			}
		}
		
		node = node.forward[0];
		
		if (node.getKey() != key) {
			node = null;
		}
		
		return node;
	}

	@Override
	public int size() {
		int size = 0;
		
		SkipListNode<T> node = root.getForward(0);
		
		while (!node.equals(NIL)) {
			size++;
			node = node.getForward(0);
		}
		
		return size;
		
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int size = size() + 2;
		SkipListNode<T>[] result = new SkipListNode[size];
		int index = 0;
		SkipListNode<T> node = root;
		
		while (node != null) {
			result[index++] = node;
			node = node.getForward(0);
		}
		return result;
	}
	
	
	public SkipListNode<T> getRoot() {
		return root;
	}

}
