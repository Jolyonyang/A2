package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric {
	
	public NodeGeneric head;
	private NodeGeneric tail;
	private int size = 0;
	
	public DSEListGeneric() {
		head = null;
		tail = null;
		size = 0;
	}
	public DSEListGeneric(NodeGeneric<T> head_) {
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
		if (index < 0 || index >= size) return null;

        NodeGeneric<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;

        if (current.prev != null) current.prev.next = current.next;
        else head = current.next;

        if (current.next != null) current.next.prev = current.prev;
        else tail = current.prev;

        size--;
		return current.get();
	}

	//returns the index of the String parameter 
	public int indexOf(T obj) {
		NodeGeneric<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.get().equals(obj)) return index;
            current = current.next;
            index++;
        }
        return -1;
	}
	
	//returns item at parameter's index
	public void get(int index) {
		if (index < 0 || index >= size) return null;
        NodeGeneric<T> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.get();
	}

	//checks if there is a list
	public boolean isEmpty() {
		return size == 0;
	}

	//return the size of the list
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		public String toString() {
			StringBuilder sb = new StringBuilder();
	        NodeGeneric<T> current = head;
	        while (current != null) {
	            sb.append(current.get().toString());
	            if (current.next != null) sb.append(" ");
	            current = current.next;
	        }
	        return sb.toString();
	}

	//add the parameter item at of the end of the list
	public boolean add(T obj) {
		if (obj == null) return false;
        NodeGeneric<T> newNode = new NodeGeneric<>(null, tail, obj);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        return true;
	}

	//add item at parameter's index
	public boolean add(int index, Object obj) {
	public boolean add(int index, T obj) {
		if (obj == null || index < 0 || index > size) return false;
        NodeGeneric<T> newNode = new NodeGeneric<>(null, null, obj);

        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            return add(obj);
        } else {
            NodeGeneric<T> current = head;
            for (int i = 0; i < index; i++) current = current.next;
            NodeGeneric<T> prev = current.prev;

            newNode.next = current;
            newNode.prev = prev;
            prev.next = newNode;
            current.prev = newNode;
        }

        size++;
        return true;
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
