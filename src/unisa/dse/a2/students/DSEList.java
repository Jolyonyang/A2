package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;
	private int size;
	public DSEList() {
		head = null;
		tail = null;
		size = 0;
	}
	public DSEList(Node head_) {
		this.head = head_;
		if (head_ == null) {
			this.tail = null;
			this.size = 0;
		} else {
			// Find the tail and count the size
			Node current = head_;
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
	public DSEList(DSEList other) { // Copy constructor. 
		if (other == null || other.head == null) return;

        this.head = new Node(null, null, other.head.getString());
        Node currentOther = other.head.next;
        Node currentNew = this.head;

        while (currentOther != null) {
            Node newNode = new Node(null, currentNew, currentOther.getString());
            currentNew.next = newNode;
            currentNew = newNode;
            currentOther = currentOther.next;
        }

        this.tail = currentNew;
        this.size = other.size();
	}

	//remove the String at the parameter's index
	public String remove(int index) {
		if (index < 0 || index >= size) return null;

        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;

        if (current.prev != null) current.prev.next = current.next;
        else head = current.next;

        if (current.next != null) current.next.prev = current.prev;
        else tail = current.prev;

        size--;
        return current.getString();
	}

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		Node current = head;
        int index = 0;
        while (current != null) {
            if (current.getString().equals(obj)) return index;
            current = current.next;
            index++;
        }
        return -1;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		 if (index < 0 || index >= size) return null;
	        Node current = head;
	        for (int i = 0; i < index; i++) current = current.next;
	        return current.getString();
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
		StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.getString());
            if (current.next != null) sb.append(" ");
            current = current.next;
        }
        return sb.toString();
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		if (obj == null) return false;
        Node newNode = new Node(null, tail, obj);
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

	//add String at parameter's index
	public boolean add(int index, String obj) {
		if (index < 0 || index > size || obj == null) return false;
        Node newNode = new Node(null, null, obj);

        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            return add(obj);
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) current = current.next;
            Node prev = current.prev;

            newNode.next = current;
            newNode.prev = prev;
            prev.next = newNode;
            current.prev = newNode;
        }

        size++;
        return true;
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		return indexOf(obj) != -1;
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		int index = indexOf(obj);
        if (index == -1) return false;
        remove(index);
        return true;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
        Node current = head;
        while (current != null) {
            result = 31 * result + (current.getString() == null ? 0 : current.getString().hashCode());
            current = current.next;
        }
        return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
        if (!(other instanceof DSEList)) return false;

        DSEList o = (DSEList) other;
        if (this.size != o.size()) return false;

        Node a = this.head;
        Node b = o.head;
        while (a != null && b != null) {
            if (!a.getString().equals(b.getString())) return false;
            a = a.next;
            b = b.next;
        } 
        
		return true;
	}
	
}
