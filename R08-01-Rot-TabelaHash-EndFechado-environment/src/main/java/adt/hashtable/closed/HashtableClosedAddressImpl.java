package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size. For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number. You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {
	   
      int numberPrime = number + 1;
      
      while (!Util.isPrime(numberPrime)) {
    	  numberPrime++;
      }
      
      return numberPrime;
   }
   
   private int indexHash(T element) {
	   HashFunctionClosedAddress<T> getHash = (HashFunctionClosedAddress<T>)getHashFunction();
	   int index = getHash.hash(element);
	   return index;
   }

   @Override
   public void insert(T element) {
	  if (element == null) return;
	  
      int index = indexHash(element);
      
      if (super.table[index] == null) {
    	  super.table[index] = new LinkedList<T>();
      }
      
      if (((LinkedList<T>)super.table[index]).size() > 0 ) {
    	  ((LinkedList<T>)super.table[index]).addFirst(element);
    	  super.elements++;
    	  super.COLLISIONS = super.getCOLLISIONS() + 1;
    	  
      } else {
    	  ((LinkedList<T>)super.table[index]).addFirst(element);
    	  super.elements++;
      }
      
      
   }

   @Override
   public void remove(T element) {
      if (element == null) return;
      
      int index = indexHash(element);
      
      if (super.table[index] != null && search(element) != null) {
    	  ((LinkedList<T>)super.table[index]).remove(element);
    	  super.elements--;
      }
   }

   @Override
   public T search(T element) {
      if (element != null) {
    	  
    	  int index = indexHash(element);
    	  
    	  if (super.table[index] != null && 
    			  ((LinkedList<T>)super.table[index]).contains(element)) {
    		  return element;
    	  }
      }
      return null;
   }

   @Override
   public int indexOf(T element) {
      if (element != null) {
    	  int index = indexHash(element);
    	  
    	  if (super.table[index] != null && search(element) != null) {
    		  return index;
    	  }
      }
      return -1;
   }

}
