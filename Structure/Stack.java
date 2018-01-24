//Stack Data Structure (GENERIC)
public class Stack<F> {
	Node<F> top;
	int size;

	public Stack() {
		top = null;
		size = 0;
	}
	// Returns if the stack is empty
	public boolean isEmpty() {
		if (top == null)
			return true;
		return false;
	}
	// returns the top element
	public F top() {
		return top.element;
	}
	// Adds to the top of the stack
	public void push(F element) {
		top = new Node<F>(element, top);
		size++;
	}
	// Removes from the top of the stack
	public boolean pop() {
		if (isEmpty())
			return false;
		else {
			top = top.next;
			size--;
			return true;
		}
	}
	//Prints the elements in the stack
	public String toString() {
		Node<F> cur = top;
		String text = "";
		for (int i = 0; i < size; i++) {
			text += cur.element + " ";
			cur = cur.next;
		}
		return text;
	}
	// Nested Node class
	class Node<T> {
		T element;
		Node<T> next;
		Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
	}
}
