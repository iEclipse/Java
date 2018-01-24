//Queue Data Structure (GENERIC)
public class Queue<F> {
	LinkedList<F> ll = new LinkedList<F>();

	//Returns the size of the queue
	public int size(){
		return ll.size;
	}
	//Returns if queue is empty
	public boolean isEmpty() {
		return ll.isEmpty();
	}
	//Returns the first element
	public F front() {
		return ll.head.element;
	}
	//Adds to the end
	public void enqueue(F element, boolean multiple) {
		ll.add(element, multiple);
	}
	//Removes from the front
	public void dequeue() {
		ll.head = ll.head.next;
		ll.size--;
	}
	//Returns queue elements
	public String toString(){
		return ll.toString();
	}
}
