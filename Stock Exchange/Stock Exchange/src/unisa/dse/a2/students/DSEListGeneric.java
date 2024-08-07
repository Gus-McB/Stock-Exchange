package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head;
	private NodeGeneric<T> tail;

	public DSEListGeneric() {
		this.head = null;
		this.tail = null;
	}
	public DSEListGeneric(NodeGeneric<T> head_) {
		if (this.head == null) {
			this.head = head_;
			this.tail = head_;
		}
		else {
			return;
		}
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { // Copy constructor. 
		if (other.head == null) { // Check if head is null.
			this.head = null;
			this.tail = null;
			return;
		}
		NodeGeneric<T> newHead = new NodeGeneric<T>(other.head.next, null, other.head.get()); // New NodeGeneric Head.
		this.head = newHead;
		NodeGeneric<T> node = other.head.next; // Original List next position.
		NodeGeneric<T> current = this.head; // Current position.
		while (node != null) { // Loops assigning each node.
			NodeGeneric<T> newNode = new NodeGeneric<T>(node, current, node.get());
			node = node.next;
			current = current.next;
			
		}
		this.tail = current;
	}

	//remove and return the item at the parameter's index
	public T remove(int index) {
		int i = 0;
		NodeGeneric<T> node = this.head;
		if (index > this.size()) { // Checks valid index.
			return null;
		}
		else if (index < 0) { // Checks negative index.
			return null;
		}
		else if (index == 0) { // Removes the head.
			node = this.head;
			this.head = this.head.next;
			this.head.prev = null;
			return node.get();
		}
		while (i < index) { // Loops each node until index is reached.
			node = node.next;
			i++;
		}
		if (node.next == null) { // Checks if tail.
			node = this.tail;
			this.tail = node.prev;
			this.tail.next = null;
			return node.get();
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		return node.get();
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) { 
		for (int i = 0; i < this.size(); i++) { // Loops GenericList.
			if (this.get(i).equals(obj) == true) { // Checks if Node value is equal.
				return i;
			}
		}
		return -1;
	}
	
	//returns item at parameter's index
	public T get(int index) {
		if (index > this.size()) { // Checks index is within the List.
			return null;
		}
		else if (index < 0) { // Checks index is not a negative.
			return null;
		}
		NodeGeneric<T> node = this.head;
		int i = 0;
		while (i < index) { // Loops over the List.
			node = node.next;
			i++;
		}
		return node.get();
	}

	//checks if there is a list
	public boolean isEmpty() { 
		if (this.tail == null && this.head == null) { // Checks the Head and Tail are null.
			return true;
		}
		else {
			return false;
		}
	}

	//return the size of the list
	public int size() {
		NodeGeneric<T> node = this.head;
		int i = 0;
		while (node != null) { // Loops over the List counting each Node.
			i++;
			node = node.next;
		}
		return i;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String value = "";
		NodeGeneric<T> node = this.head;
		while (node != null) { // Loops over the list.
			value += node.get();
			if (node.next != null) { // Adds a space at the end of the String.
				value += " ";
			}
			node = node.next;
		}
		return value;
	}

	//add the parameter item at of the end of the list
	public boolean add(T obj) {
		NodeGeneric<T> newNode = new NodeGeneric<T>(null, this.tail, obj);
		if (this.tail == null) { // Checks if the Tail is empty.
			this.tail = newNode;
			this.head = newNode;
			return true;
		}
		else { // Adds the node to the end of the list.
			this.tail.next = newNode;
			newNode.prev = this.tail;
			this.tail = newNode;
			return true;
		}
	}

	//add item at parameter's index
	public boolean add(int index, T obj) {
		if (index == 0) { // Adds Node to the front of the List.
			NodeGeneric<T> newNode = new NodeGeneric<T>(this.head, null, obj);
			this.head.prev = newNode;
			this.head = newNode;
			
			return true;
		}
		else if (index == this.size()) { // Adds the Node to the end of the List.
			this.add(obj);
			return true;
		}
		else { // Adds the node at Index.
			int i = 0;
			NodeGeneric<T> node = this.head;
			while (i < index) { // Loop until Index is found.
				i++;
				node = node.next;
			}
			NodeGeneric<T> newNode = new NodeGeneric<T>(node, node.prev, obj);
			node.prev.next = newNode;
			node.prev = newNode;
			return true;
		}
	}

	//searches list for parameter's String return true if found
	public boolean contains(Object obj) {
		for (int i = 0; i < this.size(); i++) { // Loops over each index.
			if (this.get(i).equals(obj) == true) { // Compares each index to the parameter.
				return true;
			}
		}
		return false;
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
		for (int i = 0; i < this.size(); i++) { // Loops over the List.
			if (this.get(i).equals(obj) == true) { // Checks if the Node is found.
				this.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		if (this.toString() == other.toString()) { // Compares the toString values of each Node.
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public int indexOf(Object node) {
		for (int i = 0; i < this.size(); i++) { // Loops over the List.
			if (this.get(i).toString().equals(node.toString()) == true) { // Compares the toString value for each Node.
				return i;
			}
		}
		return -1;
	}
	
}
