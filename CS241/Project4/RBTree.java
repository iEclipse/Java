package cpp.cs.cs241.prog_assgmnt_4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #5
 *
 * Red Black Tree
 *
 * @author Andrew Trang
 */

public class RBTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/**
	 * This field represents the root of the tree.
	 */
	private Node root;

	/**
	 * This enumeration represents the color of each node.
	 */
	private enum Color {
		BLACK, RED
	}

	/**
	 * This class represents each object in the tree.
	 */
	public class Node {
		/**
		 * These fields represent the attributes of each Node.
		 * @param lChild = Left Child Node
		 * @param rChild = Right Child Node
		 * @param parent = Parent Node
		 * @param key = Key
		 * @param value = Value
		 * @param color = Color
		 */
		Node lChild, rChild, parent;
		K key;
		V value;
		Color color;

		/**
		 * This is a constructor for the Node class.
		 * It initializes the Node to be used by the rest of the code.
		 * @param key = Key
		 * @param value = Value
		 * @param color = Color
		 * @param lChild = Left Child Node
		 * @param rChild = Right Child Node
		 * @param parent = Parent Node
		 */
		private Node(K key, V value, Color color, Node lChild, Node rChild, Node parent) {
			this.key = key;
			this.value = value;
			this.color = color;
			this.lChild = lChild;
			this.rChild = rChild;
			this.parent = parent;
		}
	}

	@Override
	/**
	 * This method adds a Node to the RBTree.
	 * @param key = key
	 * @param value = value
	 */
	public void add(K key, V value) {
		if (key == null)
			throw new NullPointerException("Key cannot be null.");
		if (root == null) {
			root = new Node(key, value, Color.BLACK, null, null, null);
			root.lChild = leaf(root);
			root.rChild = leaf(root);
		} else {
			Node temp = getParent(key, root);
				//Left Child
			if (key.compareTo(temp.key) < 0) {
				temp.lChild = new Node(key, value, Color.RED, null, null, temp);
				temp.lChild.lChild = leaf(temp.lChild);
				temp.lChild.rChild = leaf(temp.lChild);
				addCase1(temp.lChild);
			} else {
				//Right Child
				temp.rChild = new Node(key, value, Color.RED, null, null, temp);
				temp.rChild.lChild = leaf(temp.rChild);
				temp.rChild.rChild = leaf(temp.rChild);
				addCase1(temp.rChild);
			}
		}
	}

	/**
	 * Case 1 of the AddFix.
	 * @param n = Node to be fixed
	 */
	private void addCase1(Node n) {
		if (n.parent == null)
			n.color = Color.BLACK;
		else
			addCase2(n);
	}

	/**
	 * Case 2 of the AddFix.
	 * @param n = Node to be fixed
	 */
	private void addCase2(Node n) {
		if (n.parent.color == Color.BLACK)
			return;
		else
			addCase3(n);
	}

	/**
	 * Case 3 of the AddFix.
	 * @param n = Node to be fixed
	 */
	private void addCase3(Node n) {
		Node g = getGrandParent(n);
		Node u = getUncle(n);
		if (u != null && u.color == Color.RED) {
			n.parent.color = Color.BLACK;
			u.color = Color.BLACK;
			g.color = Color.RED;
			addCase1(g);
		} else
			addCase4(n);
	}

	/**
	 * Case 4 of the AddFix.
	 * @param n = Node to be fixed
	 */
	private void addCase4(Node n) {
		Node g = getGrandParent(n);
		if (n == n.parent.rChild && n.parent == g.lChild) {
			rotateLeft(n.parent);
			n = n.lChild;
		} else if (n == n.parent.lChild && n.parent == g.rChild) {
			rotateRight(n.parent);
			n = n.rChild;
		}
		addCase5(n);
	}

	/**
	 * Case 5 of the AddFix.
	 * @param n = Node to be fixed
	 */
	private void addCase5(Node n) {
		Node g = getGrandParent(n);
		n.parent.color = Color.BLACK;
		g.color = Color.RED;
		if (n == n.parent.lChild)
			rotateRight(g);
		else
			rotateLeft(g);
	}

	@Override
	/**
	 * This method removes a Node from the RBTree.
	 * @param k = Key of the Node to be fixed
	 */
	public V remove(K key) {
		Node n = lookupHelper(key, root);
		if (n != null) {
			V value = n.value;
			delete(n);
			return value;
		}
		return null;
	}

	/**
	 * This method performs a BST Removal.
	 * @param n Node to be removed
	 */
	private void delete(Node n) {
		// N is null
		if (root == null)
			return;
		// N has no children
		else if (getChildren(n) == 0) {
			if (root == n)
				root = null;
			else if (n.parent.lChild == n) {
				n.parent.lChild = leaf(n.parent);
				n = n.parent.lChild;
			} else if (n.parent.rChild == n) {
				n.parent.rChild = leaf(n.parent);
				n = n.parent.rChild;
			}
		}
		// N has one child
		else if (getChildren(n) == 1) {
			if (root == n) {
				root = isLeaf(root.rChild) ? root.lChild : root.rChild;
				root.parent = null;
			} else if (isLeaf(n.lChild)) {
				n.rChild.parent = n.parent;
				n.parent.rChild = n.rChild;
			} else {
				n.lChild.parent = n.parent;
				n.parent.rChild = n.rChild;
			}
		}
		// N has two children
		else if (getChildren(n) == 2) {
			Node s = getSuccessor(n); //Can change to either predecessor/successor
			n.key = s.key;
			n.value = s.value;
			delete(s);
		}
		if (getSibling(n) != null)
			removeCase1(n);
		if (root != null)
			root.color = Color.BLACK;
	}

	/**
	 * Case 1 of the RemoveFix.
	 * @param n = Node to be fixed
	 */
	private void removeCase1(Node n) {
		Node s = getSibling(n);
		if (s.color == Color.RED)
			rotateLeft(n.parent);
		removeCase2(n);
	}

	/**
	 * Case 2 of the RemoveFix.
	 * @param n = Node to be fixed
	 */
	private void removeCase2(Node n) {
		Node s = getSibling(n);
		if (n == root)
			return;
		if (s.color == Color.BLACK) {
			if (s.lChild.color == Color.BLACK && s.rChild.color == Color.BLACK)
				s.color = Color.RED;
			removeCase2(n.parent);
		}
		removeCase3(n);
	}

	/**
	 * Case 3 of the RemoveFix.
	 * @param n = Node to be fixed
	 */
	private void removeCase3(Node n) {
		Node s = getSibling(n);
		if (s.color == Color.BLACK) {
			if (s.lChild.color == Color.RED && s.rChild.color == Color.BLACK) {
				s.color = Color.RED;
				s.lChild.color = Color.BLACK;
				rotateRight(s);
			} else if (s.rChild.color == Color.RED && s.lChild.color == Color.BLACK) {
				s.color = Color.RED;
				s.rChild.color = Color.BLACK;
				rotateLeft(s);
			}
		}
		removeCase4(n);
	}

	/**
	 * Case 4 of the RemoveFix.
	 * @param n = Node to be fixed
	 */
	private void removeCase4(Node n) {
		Node s = getSibling(n);
		if (s.color == Color.BLACK) {
			if (s.rChild.color == Color.RED) {
				s.color = n.parent.color;
				n.parent.color = Color.BLACK;
				rotateLeft(n.parent);
				s.rChild.color = Color.BLACK;
			} else if (s.lChild.color == Color.RED) {
				s.color = n.parent.color;
				n.parent.color = Color.BLACK;
				rotateRight(n.parent);
				s.lChild.color = Color.BLACK;
			}
		}
	}

	@Override
	/**
	 * This method returns the value of a key.
	 * @param key = Lookup key
	 */
	public V lookup(K key) {
		Node temp = lookupHelper(key, root);
		if (temp != null)
			return temp.value;
		return null;
	}

	/**
	 * This is a helper method for the Lookup method.
	 * @param key = Lookup Key
	 * @param temp = Recursive Node Lookup
	 * @return Searched Node
	 */
	public Node lookupHelper(K key, Node temp) {
		while (temp != null && temp.key != null) {
			if (key.compareTo(temp.key) == 0)
				return temp;
			else if (key.compareTo(temp.key) < 0) {
				temp = temp.lChild;
			} else if (key.compareTo(temp.key) > 0) {
				temp = temp.rChild;
			}
		}
		return null;
	}

	@Override
	/**
	 * @return This method returns the tree's structure in a BFS order.
	 */
	public String toPrettyString() {
		Queue<Node> q = new LinkedList<Node>();
		String result = "";
		//This header will not be returned in the String.
		System.out.println(
				"==============================\n Format: (Parent)[Key, Color]\n==============================");
		if (root == null)
			return "The tree is empty.";
		q.add(root);
		q.add(null);
		while (!q.isEmpty()) {
			Node temp = q.remove();
			if (temp == null) {
				result += "\n";
				if (!q.isEmpty())
					q.add(null);
			} else {
				if (temp.parent == null)
					result += "(*)";
				else
					result += "(" + temp.parent.key + ")";
				result += "[" + temp.key + ", " + temp.color + "] ";
				if (temp.rChild != null)
					q.add(temp.lChild);
				if (temp.rChild != null)
					q.add(temp.rChild);
			}
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * This method rotates the subtree left.
	 * @param = n Initial Node
	 */
	private void rotateLeft(Node n) {
		Node c = n.rChild.lChild;
		n.rChild.parent = n.parent;
		if (n == root)
			root = n.rChild;
		else if (n == n.parent.lChild)
			n.parent.lChild = n.rChild;
		else
			n.parent.rChild = n.rChild;
		n.parent = n.rChild;
		n.rChild.lChild = n;
		n.rChild = c;
		c.parent = n;
	}

	/**
	 * This method rotates the subtree right.
	 * @param n = Initial Node
	 */
	private void rotateRight(Node n) {
		Node c = n.lChild.rChild;
		n.lChild.parent = n.parent;
		if (n == root)
			root = n.lChild;
		else if (n == n.parent.rChild)
			n.parent.rChild = n.lChild;
		else
			n.parent.lChild = n.lChild;
		n.parent = n.lChild;
		n.lChild.rChild = n;
		n.lChild = c;
		c.parent = n;
	}

	/**
	 * This method generates a new leaf.
	 * @param parent = Assigns the parent to the parent field
	 */
	private Node leaf(Node parent) {
		return new Node(null, null, Color.BLACK, null, null, parent);
	}

/**
 * This method checks to see if a Node is a leaf.
 * A leaf can be defined as having two null-valued children.
 * @param n = The Node to be checked.
 * @return = Truth statement of the method.
 */
	private boolean isLeaf(Node n) {
		if (n != null)
			return n.lChild == null && n.rChild == null;
		return false;
	}

	/**
	 * This method locates the sibling Node.
	 * @param n = Recursive Node Search
	 * @return = Located sibling node
	 */
	private Node getSibling(Node n) {
		if (n != null && n.parent != null)
			if (n == n.parent.lChild)
				return n.parent.rChild;
			else if (n == n.parent.rChild)
				return n.parent.lChild;
		return null;
	}

	/**
	 * This method locates the parent Node using a lookup key.
	 * @param key = Lookup key
	 * @param n = Recursive Node Search
	 * @return = Located parent node
	 */
	private Node getParent(K key, Node n) {
		while (n.key != null)
			if ((key.compareTo(n.key) < 0 && n.lChild.key == null)
					|| (key.compareTo(n.key) >= 0 && n.rChild.key == null))
				return n;
			else if (key.compareTo(n.key) < 0)
				n = n.lChild;
			else if ((key.compareTo(n.key) >= 0))
				n = n.rChild;
		return null;
	}

	/**
	 * This method locates the grandparent Node.
	 * @param n Grand child lookup Node
	 * @return Located grandparent Node
	 */
	private Node getGrandParent(Node n) {
		if (n != null && n.parent != null)
			return n.parent.parent;
		return null;
	}

	/**
	 * This method locates the uncle Node.
	 * @param n Uncle Lookup Node
	 * @return Located uncle Node
	 */
	private Node getUncle(Node n) {
		Node grandParent = getGrandParent(n);
		if (n != null && grandParent != null && n.key != null)
			if (n.key.compareTo(grandParent.key) < 0)
				return grandParent.rChild;
			else if (n.key.compareTo(grandParent.key) >= 0)
				return grandParent.lChild;
		return null;
	}

	/**
	 * This method checks for the number of children of a Node.
	 * @param n = The node to be checked.
	 * @return = The number of children detected.
	 */
	private int getChildren(Node n) {
		int num = 0;
		if (n == null)
			return -1;
		if (!isLeaf(n.lChild))
			++num;
		if (!isLeaf(n.rChild))
			++num;
		return num;
	}

	/**
	 * Note: Currently using getSuccessor
	 * 
	 * This method returns the predecessor of a Node.
	 * A predecessor Node can be defined as the rightmost
	 * child of the current Node's left child.
	 * @param n Lookup Node
	 * @return Predecessor Node
	 */
	private Node getPredecessor(Node n) {
		if (n == null)
			return null;
		Node temp = n.lChild;
		while (temp.rChild != null && temp.rChild.key != null)
			temp = temp.rChild;
		return temp;
	}

	/**
	 * This method returns the successor of a Node.
	 * A predecessor Node can be defined as the leftmost
	 * child of the current Node's right child.
	 * @param n Lookup Node
	 * @return Successor Node
	 */
	private Node getSuccessor(Node n) {
		if (n == null)
			return null;
		Node temp = n.rChild;
		while (temp.lChild != null && temp.lChild.key != null)
			temp = temp.lChild;
		return temp;
	}
}