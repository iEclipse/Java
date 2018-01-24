//Singly Linked List w/o Dummy Head Data Structure (GENERIC)
public class LinkedList<E> {
	
	Node<E> head;
	int size;
	public LinkedList() {
		head = null;
		size = 0;
	}
	//Checks to see if list is empty
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}
	//Adds an element to the end of the list
	//OPTIONAL: Allow duplicate entries
	public boolean add(E element, boolean multiple) {
		if (isEmpty()) {
			head = new Node<E>(element, null);
			size++;
			return true;
		}
		Node<E> cur = head;
		Node<E> cur2 = cur;
		while (cur != null) {
			if (!multiple && cur.element == element)
				return false;
			cur2 = cur;
			cur = cur.next;
		}
		cur2.next = new Node<E>(element, null);
		size++;
		return true;
	}
	//Removes an element from the list
	//OPTIONAL: Remove all occurences
	public boolean remove(E element, boolean multiple) {
		int i = 0;
		boolean found = true;
		boolean headF = false;
		Node<E> cur = head;
		if (isEmpty())
			return !found;
		else if (head.element == element && !multiple) {
			head = head.next;
			size--;
			return found;
		}
		else if (head.element == element) {
			headF = !headF;
			found = !found;
		}
		while (cur.next != null && i < size) {
			if (cur.next.element == element) {
				cur.next = cur.next.next;
				size--;
				if (!multiple)
					return !found;
			}
			i++;
		}
		if (headF) {
			head = head.next;
			size--;
		}
		return found;
	}
	//Prints the elements in the list
	public String toString() {
		Node<E> cur = head;
		String text = "";
		for (int i = 0; i < size; i++) {
			text += cur.element + " ";
			cur = cur.next;
		}
		return text;
	}
	//Nested Node class
	class Node<T> {
		T element;
		Node<T> next;
		Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}
}