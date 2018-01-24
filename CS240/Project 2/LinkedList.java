/* LinkedList Class
 * Contains all methods relating to LinkedLists
 * - Creates a dummy head and initializes a size
 * - Nested Node class can be found below
 */

public class LinkedList {
	protected Node head;
	protected int size;
	public LinkedList() {
		head = new Node(null, null);
		size = 0;
	}

	// Checks to see if an element is present
	public boolean contain(Object object) {
		Node current = head;
		while (current.next != null) {
			if (current.next.data == object)
				return true;
			current = current.next;
		}
		return false;
	}
	// Removes the specified element if present
	public boolean remove(Object object) {
		Node current = head;
		Node temp = current;
		while (current != null) {
			if (current.data == object) {
				temp.next = current.next;
				current.next = null;
				size--;
				return true;
			}
			temp = current;
			current = current.next;
		}
		return false;
	}
	// Adds an element to the end of the list
	public boolean addElement(Object object) {
		Node current = head;
		Node temp = new Node(object, null);
		while (current.next != null) {
			if (current.next.data == object) {
				return false;
			}
			current = current.next;
		}
		current.next = temp;
		size++;
		return true;
	}
	// Returns the size of the LinkedList
	public int size() {
		return size;
	}
	// Checks to see if the list is a subset of the specified list
	public boolean subsetOf(LinkedList b) {
		if (size > b.size)
			return false;
		int num = 0;
		Node nodeB = b.head;
		Node nodeA = head;
		while (nodeA.next != null && nodeB.next != null)
			if (nodeA.next.data != nodeB.next.data)
				nodeA = nodeA.next;
			else {
				num++;
				nodeB = nodeB.next;
			}
		if (num == size)
			return true;
		return false;
	}
	// Checks to see if the two lists compared are equal
	public boolean isEqual(LinkedList b) {
		if (size != b.size)
			return false;
		int num = 0;
		Node nodeB = b.head;
		Node nodeA = head;
		while (nodeA.next != null && nodeB.next != null)
			if (nodeA.next.data != nodeB.next.data)
				nodeA = nodeA.next;
			else {
				num++;
				nodeB = nodeB.next;
			}
		if (num == size)
			return true;
		return false;
	}
	// Creates a LinkedList containing all elements from both lists
	public LinkedList union(LinkedList b) {
		LinkedList temp = this;
		Node nodeB = b.head;
		Node nodeA = head;
		while (nodeA.next != null && nodeB.next != null)
			if (nodeA.next.data == nodeB.next.data)
				nodeA = nodeA.next;
			else {
				temp.addElement(nodeB.next.data);
				nodeB = nodeB.next;
			}
		return temp;
	}
	// Returns only the matching objects in every list
	public LinkedList intersection(LinkedList b) {
		LinkedList temp = new LinkedList();
		Node nodeB = b.head;
		Node nodeA = head;
		while (nodeA.next != null && nodeB.next != null)
			if (nodeA.next.data == nodeB.next.data) {
				temp.addElement(nodeA.next.data);
				nodeA = nodeA.next;
			}
			else
				nodeB = nodeB.next;
		return temp;
	}
	// Returns the objects from first list that are not included in second list
	public LinkedList complement(LinkedList b) {
		LinkedList temp = new LinkedList();
		Node nodeB = b.head;
		Node nodeA = head;
		while (nodeA.next != null && nodeB.next != null)
			if (nodeA.next.data == nodeB.next.data) {
				nodeA = nodeA.next;
			}
			else {
				nodeB = nodeB.next;
				temp.addElement(nodeA.next.data);
			}
		return temp;
	}
	// Returns the elements of each object in the LinkedList
	public String toString() {
		String msg = "{";
		Node temp = head;
		while (temp.next != null) {
			msg += temp.next.data;
			temp = temp.next;
			if (temp.next != null)
				msg += ", ";
		}
		msg += "}";
		return msg;
	}
	// Nested Node class that takes an object for its stored element
	public class Node {
		Object data;
		Node next;

		// Constructor for new nodes
		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}