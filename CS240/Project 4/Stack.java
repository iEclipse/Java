// Stack Class Using Generic Types
public class Stack<T> extends ErrorMessage {
	private Node<T> top;
	private int size;
	// Default Stack Constructor
	public Stack() {
		top = null;
		size = 0;
	}
	// Returns the Size of the Stack
	public int getSize() {
		return size;
	}
	// Returns if the Stack is Empty
	public boolean isEmpty() {
		return (size == 0);
	}
	// Returns the Top Node
	public Node<T> top() {
		try {
			return top;
		} catch (NullPointerException e) {
			return null;
		}
	}
	// Adds a New Node to the Top of the Stack
	public void push(T element) {
		if (isEmpty()) {
			top = new Node<T>(element, null);
		}
		else {
			top = new Node<T>(element, top);
		}
		size++;
	}
	// Removes the Top Element from the Stack
	public void pop() {
		if (!isEmpty()) {
			top = top.next;
			size--;
		}
	}
	// Nested Node Class
	public class Node<E> {
		public E element;
		public Node<E> next;
		// Node Default Constructor
		public Node() {
			this(null, null);
		}
		// Additional Node Constructor
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}
}
