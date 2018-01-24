package cs331.sorting.andrewtrang;

import java.util.LinkedList;

public class MergeInsertionSort extends MergeSort {

    InsertionSort is = new InsertionSort();

    @Override
    public LinkedList<Integer> divide(LinkedList<Integer> list) {
        if (list.size() < 20) {
            is.sorted = list;
            return (LinkedList<Integer>) is.getSortedList();
        }

        LinkedList<Integer> left = new LinkedList();
        LinkedList<Integer> right = new LinkedList();

        for (int i = 0; i < list.size() / 2; i++)
            left.add(list.removeFirst());

        while (!list.isEmpty())
            right.add(list.removeFirst());
        left = divide(left);
        right = divide(right);
        return merge(left, right);
    }
}
