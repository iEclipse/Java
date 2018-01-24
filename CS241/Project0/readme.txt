Assignment description
============================
This is a simple exercise provided for you to refresh your programming skills. Simply implement the given List interface in Java as an Array List.

When done, create a zip file following submission guidelines and submit through blackboard.

This assignment is worth 10 points of extra credit.

Team/individual information
==============================
Author: Andrew Trang
CS241 Assignment 1

Description of approach for your solution
============================================
Prior to the exercise, I had not learned about arraylists at all from my CS240 course. However, this allowed me to take a look at how the data structure was implemented.
I created a default constructor that initialized and assigned an empty arraylist. I also created a constructor that allowed the user to specify the initial size of the arraylist.

For all of the methods implemented from the List interface, I assumed key meant the object being stored and the value being the index of the arraylist.
A resize method, while not specified in the interface was added to the code to allow the user to add more elements to the arryalist rather than capping the number of objects stored allowed.
The add method stores the key/data at the value/index of the array. If the number of elements equals the capacity of the arraylist, the arraylist expands, stores the data, and returns true.
If an invalid index is given, it will return false.

The multiple remove methods confused me. I initially believed that the variable key meant the index of the array, and the value was the data being stored, but that would mean the remove(K key) and remove(int i) were completely redundant.
I decided to allow the user to lookup an element based on the data and remove it that way. The remove() method just removes the last element in the arraylist. The lack of commends on the interface made it hard to decipher the requirements
so I did the best I could to give the ArrayList class some versatility. The rest of the methods were straightforward, except for the return values which I failed to see the point of.

Conclusions and lessons learned.
=====================================
I spent much of my time analyzing the ArrayList datastructure from outside resources so I learned how to implement them in code. Mixed with generic types, I got a bit more practice on the subject.
I would recommend specifying what the abstract methods in the interface were meant to do. A better description would be appreciated.