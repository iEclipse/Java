//Doubly Linked List w/ Dummy Head

public class DH {
	DNode head;
	DNode trailer;
	int size;
	// Default Constructor
	public DH() {
		head = new DNode(null, null, null);
		trailer = new DNode(null, head, null);
		head.next = trailer;
		size = 0;
	}
	// Adds an object to the end of the list
	public void add(Object data) {
		DNode cur = new DNode(data, trailer.prev, trailer);
		trailer.prev.next = cur;
		trailer.prev = cur;
		size++;
	}
	// Removes first occurrence of the object
	public void remove(Object data) {
		DNode cur = head.next;
		while (cur != trailer)
			if (cur.data == data) {
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				cur.prev = null;
				cur.next = null;
				size--;
				break;
			}
			else
				cur = cur.next;
	}
	// Prints the set
	public String toString() {
		String msg = "{";
		DNode cur = head.next;
		while (cur != trailer) {
			msg += cur.data;
			cur = cur.next;
			if (cur != trailer)
				msg += ", ";
		}
		msg += "}";
		return msg;
	}
	// Nested Node class
	public class DNode {
		Object data;
		DNode prev;
		DNode next;
		public DNode(Object data, DNode prev, DNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
}
