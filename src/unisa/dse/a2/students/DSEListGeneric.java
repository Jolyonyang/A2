package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric implements ListGeneric {
	
	public NodeGeneric head;
	private NodeGeneric tail;
	private int size = 0;
	
	public DSEListGeneric() {
		head = null;
		tail = null;
		size = 0;
	}
	public DSEListGeneric(NodeGeneric head_) {
		this.head = head_;
		if (head_ == null) {
			this.tail = null;
			this.size = 0;
		} else {
			
			NodeGeneric<T> current = head_;
			int count = 1;
			while (current.next != null) {
				current = current.next;
				count++;
			}
			this.tail = current;
			this.size = count;
		}
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEList other) { 
		if (other == null || other.head == null) return;

        this.head = new NodeGeneric<>(null, null, other.head.get());
        NodeGeneric<T> currentOther = other.head.next;
        NodeGeneric<T> currentNew = this.head;

        while (currentOther != null) {
            NodeGeneric<T> newNode = new NodeGeneric<>(null, currentNew, currentOther.get());
            currentNew.next = newNode;
            currentNew = newNode;
            currentOther = currentOther.next;
        }

        this.tail = currentNew;
		this.size = other.size();
	}

	//remove and return the item at the parameter's index
	public void remove(int index) {

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
	}
	
	//returns item at parameter's index
	public void get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
	}

	//return the size of the list
	public int size() {
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
	}

	//add the parameter item at of the end of the list
	public boolean add(Object obj) {
	}

	//add item at parameter's index
	public boolean add(int index, Object obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(Object obj) {
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
