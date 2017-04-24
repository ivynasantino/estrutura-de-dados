package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isFull()) {
				throw new HashtableOverflowException();
				
			} else {
				int probe = 0;
				int index = indexHash(element, probe);
				
				while (probe < capacity()) {
					if (table[index] == null || (table[index] instanceof DELETED)){
						table[index] = element;
						elements++;
						return;
						
					} else {
						probe++;
						index = indexHash(element, probe);
						COLLISIONS++;
					}
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (indexOf(element) != -1) {
				table[indexOf(element)] = new DELETED();
				elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		if (indexOf(element) != -1) {
			return element;
		} 
		return null;
	}

	@Override
	public int indexOf(T element) {
		if (element != null) {
			int probe = 0;
			int index = indexHash(element, probe);
			
			while (probe < capacity() && table[index] != null && 
					!(table[index] instanceof DELETED)) {
				
				if (table[index].equals(element)) {
					return index;
				}
				
				probe++;
				index = indexHash(element, probe);
			}
		}
		return -1;
	}
	
	private int indexHash(T element, int probe) {
		HashFunctionLinearProbing<T> linear = (HashFunctionLinearProbing<T>)hashFunction;
		int index = linear.hash(element, probe);
		return index;
	}

}
