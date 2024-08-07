package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;

	public DSEList() {
		this.head = null;
		this.tail = null;
	}
	public DSEList(Node head_) {
		if (this.head == null) {
			this.head = head_;
			this.tail = head_;
		}
		else {
			return;
		}
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		if (other.head == null) {
			this.head = null;
			this.tail = null;
			return;
		}
		Node newHead = new Node(other.head.next, null, other.head.getString()); // Creates a copy of the head in a new node and assigns it to head.
		this.head = newHead;
		Node node = other.head.next; // Next position in the original list.
		Node current = this.head; // current position in the DSEList
		while (node != null) { // Loops the original list until null is returned.
			Node newNode = new Node(node, current, node.getString());
			node = node.next;
			current = current.next;
			
		}
		this.tail = current;
	}

	//remove the String at the parameter's index
	public String remove(int index) {
		int i = 0;
		Node node = this.head;
		if (index > this.size()) { // Ensure index is within the list.
			return null;
		}
		else if (index < 0) { // List cannot have negative indexes.
			return null;
		}
		else if (index == 0) { // returns the head of the List.
			node = this.head;
			this.head = this.head.next;
			this.head.prev = null;
			return node.getString();
		}
		while (i < index) { // Loops over the List until the index is reached.
			node = node.next;
			i++;
		}
		if (node.next == null) { // Returns the tail.
			node = this.tail;
			this.tail = node.prev;
			this.tail.next = null;
			return node.getString();
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		return node.getString();
		}
		

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		for (int i = 0; i < this.size(); i++) { // Loops over the list until the object is found.
			if (this.get(i).equals(obj) == true) {
				return i;
			}
		}
		return -1; // If no object is found, returns '-1'.
	}
	
	//returns String at parameter's index
	public String get(int index) {
		if (index > this.size()) { // Checks if the index is within the List.
			return null;
		}
		else if (index < 0) { // Checks for negative index.
			return null;
		}
		Node node = this.head;
		int i = 0;
		while (i < index) { // Loops over list.
			node = node.next;
			i++;
		}
		return node.getString(); // Returns the node value at index.
	}

	//checks if there is a list
	public boolean isEmpty() {
		if (this.tail == null && this.head == null) { // Checks if the head and tail are null.
			return true;
		}
		else {
			return false;
		}
	}

	//return the size of the list
	public int size() { 
		if (this.head == null) { // Check if the head is null.
			return 0;
		}
		int size = 1;
		Node node = this.head;
		while (node.next != null) { // Loops and counts each element.
			node = node.next;
			size++;
		}
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String value = "";
		Node node = this.head;
		while (node != null) { // Loop and concatenates all the string values. 
			value += node.getString();
			if (node.next != null) {
				value += " ";
			}
			node = node.next;
		}
		return value;
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		Node newNode = new Node(null, this.tail, obj);
		if (this.head == null) { // Checks if the List is empty.
			this.tail = newNode;
			this.head = newNode;
			return true;
		}
		else { // Inserts the node at the end of the list.
			this.tail.next = newNode;
			newNode.prev = this.tail;
			this.tail = newNode;
			return true;
		}
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
		if (index == 0) { // Inserts at the head.
			Node newNode = new Node(this.head, null, obj);
			this.head.prev = newNode;
			this.head = newNode;
			
			return true;
		}
		else if (index == this.size()) { // Inserts at the tail.
			this.add(obj);
			return true;
		}
		else { // Loop and insert at index.
			int i = 0;
			Node node = this.head;
			while (i < index) {
				i++;
				node = node.next;
			}
			Node newNode = new Node(node, node.prev, obj);
			node.prev.next = newNode;
			node.prev = newNode;
			return true;
		}
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		for (int i = 0; i < this.size(); i++) { // Loops over each node.
			if (this.get(i).equals(obj) == true) { // Checks value is correct.
				return true;
			}
		}
		return false;
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		for (int i = 0; i < this.size(); i++) { // Loops over all nodes.
			if (this.get(i).equals(obj) == true) { // Checks node value is correct.
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
		if (this.toString() == other.toString()) { // Checks the toString value against another nodes.
			return true;
		}
		else{
			return false;
		}
	}
	
}
