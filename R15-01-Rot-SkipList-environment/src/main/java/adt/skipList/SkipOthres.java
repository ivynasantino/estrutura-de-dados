package adt.skipList;

import java.util.ArrayList;

public class SkipOthres<T> extends SkipListImpl<T> {

	public SkipOthres(int maxHeight) {
		super(maxHeight);
	}
	
	public SkipListNode<T> searchRec(int key) {
		return searchRec(key, root);
	}
	
	 private SkipListNode<T> searchRec(int key, SkipListNode<T> node) {
		 if (node == null) return node;
	     int index = node.height() - 1;
	     
	     while (index >= 0 && (node.forward[index] == null 
	    		 || node.forward[index].key >key)) {
	    	 index--;
	     }
	     
	     if (index < 0) return null;
	        
	     else if (node.forward[index].key == key) return node.forward[index];
	        
	     else return this.searchRec(key, node.forward[index]);
	    }
	
	public T[] elementsByLevel(int level) {
		ArrayList<T> array = new ArrayList();
		elementsByLevel(array, root, level, maxHeight-1);
		T[] result = (T[])new Comparable[array.size()];
		
		for (int i = 0; i < array.size(); i++) {
			result[i] = array.get(i);
		}
		
		return result;
	}
	
	private void elementsByLevel(ArrayList<T> array, SkipListNode<T> node,
			int level, int height) {
		if (node == null) return;
		
		else if (height == level-1 || node.forward[height] != null) {
			array.add(node.getForward(height).value);
			elementsByLevel(array, node.getForward(height), level, height);
		} else {
			height--;
			elementsByLevel(array, node.getForward(height), level, height);
		}
	}
	
	
	public boolean goodSkip(SkipList<T> skip) {
		return goodSkip(root, 0, skip.size(), (maxHeight - 1));
	}
	
	private boolean goodSkip(SkipListNode<T> node, int ini, int last, int height) {
		if (node == null || node.forward[height].height() > height || height < 0) {
			return false;
		}
		if (ini == last && node != null) {
			return true;
		} else {
			int mid = (ini + last)/2;
			height--;
			return goodSkip(node.forward[height], 0, mid-1, height)
					&& goodSkip(node.forward[height], mid+1, last, height);
		}
	}
	
	public void insertRec(int key, T newValue, int height) {
        if (height > this.maxHeight) return;
       
        SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
        insert(this.root, newNode);
    }


    private void insert(SkipListNode<T> node, SkipListNode<T> newNode) {
        for (int index = node.height() - 1; index >= 0; index--) {
            if (node.forward[index] == null) {
                if (index < newNode.height()) node.forward[index] = NIL;
                else continue;
            }
            if (node.forward[index].getKey() == newNode.getKey()) {
                newNode.forward[index] = node.forward[index].forward[index];
                node.forward[index] = newNode;
            } else if (node.forward[index].getKey() < newNode.getKey()) {
                insert(node.forward[index], newNode);
                return;
            } else if (index < newNode.height()) {
                newNode.forward[index] = node.forward[index];
                node.forward[index] = newNode;
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
                return;
            }
        }
    }
	
	
}
