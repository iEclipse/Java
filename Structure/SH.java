//Singly Linked List w/ Dummy Head

public class SH {
	SNode head;
	int size;
	// Default Constructor
	public SH() {
		head = new SNode(null, null);
		size = 0;
	}
	// Adds an object to the end of the list
	public void add(Object data) {
		SNode cur = head;
		while (cur.next != null)
			cur = cur.next;
		cur.next = new SNode(data, null);
		size++;
	}
	// Removes first occurrence of the object
	public void remove(Object data) {
		SNode cur = head.next;
		SNode cur2 = head;
		while (cur.next != null)
			if (cur.data == data) {
				cur2.next = cur.next;
				cur.next = null;
				size--;
				break;
			}
			else {
				cur2 = cur2.next;
				cur = cur.next;
			}
	}
	// Prints the set
	public String toString() {
		String msg = "{";
		SNode cur = head.next;
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
	public class SNode {
		Object data;
		SNode next;
		public SNode(Object data, SNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
