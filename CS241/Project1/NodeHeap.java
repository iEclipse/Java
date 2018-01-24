/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #2
 *
 * Description: Implement a heap from the heap interface & create a table-sasignment application.
 *
 * @Author: Andrew Trang
 */
package cpp.cs.cs241.prog_assgmnt_1;

/**
 * 
 * @author This class represents the heap data structure.
 *
 * @param <V>
 *            The desired data type
 */
public class NodeHeap<V extends Comparable<V>> implements Heap<V> {
	/**
	 * This field represents the root of the heap.
	 */
	Node root;
	/**
	 * This field represents the type of tree. Options: "min" or "max"
	 */
	String type;
	/**
	 * This field represents the size of the heap.
	 */
	int size;
	/**
	 * This field represents last checked node.
	 */
	int last;

	/**
	 * Default constructor for the {@link NodeHeap} class.
	 */
	public NodeHeap() {
		type = "min";
		root = null;
		size = 0;
	}

	/**
	 * User-defined constructor that allows the use of both min/max heap types.
	 * 
	 * @param type
	 *            Type of heap (min/max)
	 */
	public NodeHeap(String type) {
		this.type = type;
		root = null;
		size = 0;
	}

	/**
	 * Adds an element to the heap.
	 */
	public void add(V value) {
		last = ++size;
		if (size == 1)
			root = new Node(null, null, value);
		else if (size % 2 == 0) {
			getParent(size).left = new Node(null, null, value);
			siftUp(getParent(size).left);
		} else {
			getParent(size).right = new Node(null, null, value);
			siftUp(getParent(size).right);
		}
	}

	/**
	 * Removes the element from the root of the heap.
	 */
	public V remove() {
		Node removed = root;
		if (size == 1) {
			root = null;
			--size;
			return removed.data;
		} else if (size > 1) {
			Node temp = getParent(size);
			if (size % 2 == 0) {
				removeHelper(temp.left);
				getParent(size).left = null;
			} else {
				removeHelper(temp.right);
				getParent(size).right = null;
			}
			--size;
			last = 1;
			siftDown(root);
			return removed.data;
		}
		return null;
	}

	/**
	 * Assists the remove method in removing a node.
	 * 
	 * @param temp
	 *            The node to be removed.
	 */
	private void removeHelper(Node temp) {
		temp.left = root.left;
		temp.right = root.right;
		root = temp;
		temp = null;
	}

	/**
	 * Converts an array to a heap.
	 */
	public void fromArray(V[] array) {
		for (int i = 0; i < array.length; ++i)
			add(array[i]);
	}

	/**
	 * Returns the heap in an array representation. The array passed MUST equal
	 * the heap's size.
	 */
	public V[] toArray(V[] array) {
		if (size > 0) {
			array[0] = root.data;
			for (int i = 1; i <= size / 2; ++i) {
				Node temp = getParent(2 * i);
				if (temp.left != null)
					array[2 * i - 1] = temp.left.data;
				if (temp.right != null) {
					array[2 * i] = temp.right.data;
				}
			}
		}
		return array;
	}

	/**
	 * Performs the siftUp operation on a specific node
	 * 
	 * @param temp
	 *            The current node to be sifted.
	 * 
	 */
	private void siftUp(Node temp) {
		Node parent = getParent(last);
		if (compare(parent, temp)) {
			if (last % 2 == 0)
				swap(parent, temp, 0);
			else
				swap(parent, temp, 1);
			last /= 2;
			if (root == parent) {
				root = temp;
			} else if (last % 2 > 0)
				getParent(last).right = temp;
			else
				getParent(last).left = temp;
			siftUp(temp);
		}
	}

	/**
	 * Performs the siftDown operation on a specific node
	 * 
	 * @param temp
	 *            The current node to be sifted.
	 * 
	 */
	private void siftDown(Node temp) {
		if (temp.right != null) {
			Node x = compare(temp.left, temp.right) ? temp.right : temp.left;
			if (compare(temp, x)) {
				if (x == temp.left) {
					swap(temp, x, 0);
					if (temp == root)
						root = x;
					else {
						last = 2 * last + 1;
						getParent(last).left = x;
					}
					siftDown(x.left);
				} else if (x == temp.right) {
					swap(temp, x, 1);
					if (temp == root)
						root = x;
					else {
						last = 2 * last + 2;
						getParent(last).right = x;
					}
					siftDown(x.right);
				}
			}
		} else if (temp.left != null) {
			Node x = temp.left;
			if (compare(temp, x)) {
				swap(temp, x, 0);
				if (temp == root)
					root = x;
				else {
					last = 2 * last + 1;
					getParent(last).left = x;
				}
			}
		}
	}

	/**
	 * Switches the order of two nodes.
	 * 
	 * @param temp
	 *            The first node to be swapped
	 * @param x
	 *            The second node to be swapped
	 * @param dir
	 *            The direction of the swap Options: left(0)/right(1)
	 */
	private void swap(Node temp, Node x, int dir) {
		if (dir == 0) {
			Node y = temp.right;
			Node z = x.left;
			x.left = temp;
			temp.right = x.right;
			x.right = y;
			temp.left = z;

		} else {
			Node y = temp.left;
			Node z = x.right;
			x.right = temp;
			temp.left = x.left;
			x.left = y;
			temp.right = z;
		}
	}

	/**
	 * Returns a the sorted heap in an array format.
	 */
	public V[] getSortedContents(V[] array) {
		for (int i = 0; i < array.length; ++i)
			array[i] = remove();
		for (int i = 0; i < array.length; ++i)
			add(array[i]);
		return array;
	}

	/**
	 * Returns the parent of the specified node.
	 * 
	 * @param size
	 *            The number of the desired node
	 * @return The parent node
	 */
	private Node getParent(int size) {
		Node temp = root;
		String route = Integer.toBinaryString(size);
		for (int i = 1; i < route.length() - 1; ++i)
			if (route.charAt(i) == '0')
				temp = temp.left;
			else
				temp = temp.right;
		return temp;
	}

	/**
	 * Compares two nodes in the heap.
	 * 
	 * @param temp1
	 *            The first node to be compared
	 * @param temp2
	 *            The second node to be compared
	 * @return The truth statement of whether the first is smaller/greater than
	 *         the second.
	 */
	private boolean compare(Node temp1, Node temp2) {
		if (temp1 == null || temp2 == null)
			return false;
		else if (type == "min")
			return temp1.data.compareTo(temp2.data) == 1;
		else if (type == "max")
			return temp2.data.compareTo(temp1.data) == 1;
		else
			return false;
	}

	/**
	 * Nested node class for the heap.
	 */
	class Node {
		/**
		 * The child nodes of the current node.
		 */
		Node left, right;
		/**
		 * The data to be stored in each node.
		 */
		V data;

		/**
		 * Constructor of the node class.
		 * 
		 * @param left
		 *            The left child of the current node
		 * @param right
		 *            The right child of the current node
		 * @param data
		 *            The data stored in the node
		 */
		public Node(Node left, Node right, V data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}
}
