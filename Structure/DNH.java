//Doubly Linked List w/o Dummy Head

public class DNH {
	DNode head;
	DNode trailer;
	int size;
	// Default Constructor
	public DNH() {
		head = null;
		trailer = null;
		size = 0;
	}
	// Adds an object the end of the list
	public void add(Object data) {
		if (head == null)
			head = new DNode(data, null, null);
		else if (trailer == null) {
			trailer = new DNode(data, head, null);
			head.next = trailer;
		}
		else {
			trailer.next = new DNode(data, head, null);
			trailer = trailer.next;
		}
		size++;
	}
	// Removes first occurrence of the object
	public void remove(Object data) {
		DNode cur = head;
		while (cur != null)
			if (cur.data == data && cur != head && cur != trailer) {
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				cur.next = null;
				cur.prev = null;
				size--;
				break;
			}
			else if (cur.data == data && cur == head && cur != trailer) {
				head = cur.next;
				head.prev = null;
				cur.next = null;
				size--;
				break;
			}
			else if (cur.data == data && cur == trailer && cur != head) {
				trailer.prev.next = null;
				trailer.prev = null;
				trailer = trailer.prev.next;
				size--;
				break;
			}
			else if (cur.data == data) {
				head.next = null;
				trailer.prev = null;
				head = null;
				trailer = null;
				size--;
				break;
			}
			else
				cur = cur.next;
	}
	// Prints the set
	public String toString() {
		String msg = "{";
		DNode cur = head;
		while (cur != null) {
			msg += cur.data;
			cur = cur.next;
			if (cur != null)
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
