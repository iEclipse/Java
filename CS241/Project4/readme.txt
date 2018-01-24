Member Info
=======================================================================
Author: Andrew Trang
Class: CS 241: Data Structures and Algorithms II
Professor: Edwin Rodríguez


Description
=======================================================================
This is an individual assignment. This will be a very simple programming 
project in which you will simply implement a Red-Black Tree, and include
pretty-printing.

You will implement the following tree interface interface:

public interface Tree<K extends Comparable<K>, V> {
  public void add(K key, V value);
  public V remove(K key);
  public V lookup(K key);
  public String toPrettyString();
}

The method toPrettyString() will return a string with the values in the tree, in
a pyramid fashion, each value appearing along with its color, so as to make
it easy to visualize the structure of the tree.


Approach
=======================================================================
The assignment requires a lot of logical consideration when implementing
the methods. Add, lookup, remove, and printing all rely on a stable searching
algorithm so I began there. Once I established a stable lookup method, I
wrote the pretty string method to alleviate the process of debugging.

Moving on, I programming the add method and its helper functions including
the rotate, and sibling/parent/uncle/grandparent searches. From there, I
wrote the remove and its balancing methods as well.

Conclusions
=======================================================================
This assignment was by far the most difficult one I've had to work on. The
issue with trees I've noticed is the need to use recursion to simplify the code.
That necessity becomes troublesome when debugging because the stack trace
is nearly impossible to to comprehend.

In the end, the logic itself wasn't to bad, but implementation proved to be a pain.
