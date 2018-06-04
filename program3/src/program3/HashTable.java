/*
 * Sarah Hall
 * CSCI 232 
 * program 3
 */

package program3;
import java.security.InvalidKeyException;
import java.util.ArrayList;

class HashTable<K,V>{

	private Node<K,V>[] hashtable; //our array
	private Node<K,V> AVAILIBLE = new Node<K,V>(null, null); //marker node
	private int numberOfEntries;
	private int locationsUsed; 
	private int size;
	
	public HashTable() {this(30);}//
	
	public HashTable(int size) {
		this.size = size;
		hashtable = (Node<K,V>[]) new Node[size];
		numberOfEntries = 0;
		locationsUsed = 0;
		}
	
	public int getIndex(K key) {
		int index = key.hashCode() % hashtable.length;
		if(index < 0 )
				index = index + hashtable.length;
		return index;
	}
	
	
	
	public int hashValue(int index, K key) {
		boolean found = false;
		int removedStateIndex = -1;//index of first location in removed state
		int i = 0;
		while(!found &&(hashtable[index]!=null) && i < hashtable.length) {
			if(hashtable[index].isIn()) {
				if(key.equals(hashtable[index].getKey()))
					found = true;
				else {
					index = (index + i*i) % hashtable.length;
					i++;
				}
			}
			else {
				if(removedStateIndex == -1)
					removedStateIndex = index;
				index = (index + i*i) % hashtable.length;
				i++;	
			}
		}
		if(found || removedStateIndex == -1)
			return index;
		else
			return removedStateIndex;
	}
	
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	
	  public void display()
	    {
	        for (int index = 0; index < hashtable.length; index++)
	        {
	            if (hashtable[index] == null)
	            System.out.println("null ");
	            else if (hashtable[index].isRemoved())
	            System.out.println("notIn ");
	            else
	            System.out.println(hashtable[index].getKey() + " " + hashtable[index].getValue());
	        } 
	        System.out.println();
	    }
	  
	  public V remove(K key)
	    {
	        V removedValue = null;
	        
	        int index = getIndex(key);
	        index = locate(index, key);
	        
	        if (index != -1)
	        {
	            // key found; flag entry as removed and return its value
	            removedValue = hashtable[index].getValue();
	            hashtable[index].setToRemoved();
	            numberOfEntries--;
	        } // end if
	        // else not found; result is null
	        
	        return removedValue;
	    } // end remove
	
	  public V getValue(K key)
	    {
	        V result = null;
	        
	        int index = getIndex(key);
	        index = locate(index, key);
	        
	        if (index != -1)
	        result = hashtable[index].getValue(); // key found; get value
	        
	        // else not found; result is null
	        
	        return result;
	    } // end getValue
	  
	  
	public V add(K key, V value)
    {
        V oldValue;
        if (isHashTableTooFull())
        rehash();
        int index = getIndex(key);
        index = hashValue(index, key);
        assert (index >= 0) && (index < hashtable.length);
        if ( (hashtable[index] == null) || hashtable[index].isRemoved())
        { // key not found, so insert new entry
            hashtable[index] = new Node<K, V>(key, value);
            numberOfEntries++;
            locationsUsed++;
            oldValue = null;
        }
        else
        { // key found; get old value for return and then replace it
            oldValue = hashtable[index].getValue();
            hashtable[index].setValue(value);
        } // end if
        
        return oldValue;
    } // end add
	
	 private void rehash()
	    {
	        Node<K, V>[] oldTable = hashtable;
	        int oldSize = hashtable.length;
	        int newSize = oldSize * 2;
	        hashtable = new Node[newSize]; // increase size of array
	        numberOfEntries = 0; // reset number of dictionary entries, since
	        // it will be incremented by add during rehash
	        locationsUsed = 0;
	        
	        // rehash dictionary entries from old array to the new and bigger
	        // array; skip both null locations and removed entries
	        for (int index = 0; index < oldSize; index++)
	        {
	            if ( (oldTable[index] != null) && oldTable[index].isIn() )
	            add(oldTable[index].getKey(), oldTable[index].getValue());
	        } // end for
	    } // end rehash
	    
	    /** @return true if lambda > MAX_LOAD_FACTOR for hash table;
	    *       otherwise returns false. */
	    private boolean isHashTableTooFull()
	    {
	        return locationsUsed > 0.8 * hashtable.length;
	    } // end isHashTableTooFull
	    
	    public int locate(int index, K key)
	    {
	        boolean found = false;
	        int i=0;
	        while ( !found && (hashtable[index] != null) && i<hashtable.length)
	        {
	            if ( hashtable[index].isIn() && key.equals(hashtable[index].getKey()) )
	            found = true; // key found
	            else // follow probe sequence
	            {
	                index = (index + i*i) % hashtable.length;         // linear probing
	                i++;
	            }
	        } // end while
	        // Assertion: either key or null is found at hashTable[index]
	        
	        int result = -1;
	        if (found)
	        result = index;
	        
	        return result;
	    } // end locate
		
	
}

