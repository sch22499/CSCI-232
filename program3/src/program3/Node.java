/*
 * Sarah Hall
 * CSCI 232 
 * program 3
 */
package program3;

class Node<K, V>{ //node class
	K key;
	V value;
	Node<K, V> next;
	boolean inTable; //a boolean to tell if a key is in the table
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public V getValue() {return value;} //setters/getters etc
	public K getKey() {return key;}
	public V setValue(V val) {
		V prevVal = value;
		value = val;
		return prevVal;
	}
	public boolean equals(Object o) {
		Node<K,V> ent;
		try {ent = (Node<K,V>) o;}
		catch(ClassCastException ex) {return false;}
		return(ent.getKey() == key) && (ent.getValue() == value);
	}
	public boolean isIn() { //a method for the intable boolean
		return inTable;
	}
    public void setToRemoved()
    {
        key = null;
        value = null;
        inTable = false;
    } // end setToRemoved

	public boolean isRemoved() {
		return !inTable;
	}
	
}