//Singly Linked List w/o Dummy Head

public class SNH {
	SNode head;
	int size;
	// Default Constructor
	public SNH() {
		head = null;
		size = 0;
	}
	// Adds an object to the end of the list
	public void add(Object data) {
		if (head == null)
			head = new SNode(data, null);
		else {
			SNode cur = head;
			while (cur.next != null)
				cur = cur.next;
			cur.next = new SNode(data, null);
		}
		size++;
	}
	// Removes the first occurrence of the object
	public void remove(Object data) {
		SNode cur = head;
		SNode cur2 = cur;
		while (cur != null) {
			if (cur.data == data && cur == head) {
				head = cur.next;
				cur.next = null;
				size--;
				break;
			}
			else if (cur.data == data && cur.next != null) {
				cur2.next = cur.next;
				cur.next = null;
				size--;
				break;
			}
			cur2 = cur;
			cur = cur.next;
		}
	}
	// Prints the set
	public String toString() {
		String msg = "{";
		SNode cur = head;
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
