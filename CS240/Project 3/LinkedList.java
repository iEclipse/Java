public class LinkedList {
	protected Node head;
	protected int size;
	protected String name;
	protected double avg;
	public LinkedList() {
		head = new Node(0, null);
		size = 0;
	}
	// Calculates the average
	public void getAvg() {
		Node current = head;
		while (current.next != null) {
			avg += current.next.data;
			current = current.next;
		}
		avg /= size;
	}
	// Adds an element to the end of the list
	public boolean addElement(double object) {
		Node current = head;
		Node temp = new Node(object, null);
		while (current.next != null) {
			current = current.next;
		}
		current.next = temp;
		size++;
		return true;
	}
	// Nested Node class that takes an object for its stored element
	public class Node {
		double data;
		Node next;

		// Constructor for new nodes
		Node(double data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}