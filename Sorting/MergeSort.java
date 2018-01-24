package cs331.sorting.andrewtrang;

import java.util.LinkedList;
import java.util.List;

public class MergeSort implements Sort {

    LinkedList<Integer> sorted = new LinkedList<>();

    @Override
    public void init(List list) {
        sorted.addAll(list);
    }

    @Override
    public List getSortedList() {
        sorted = divide(sorted);
        return sorted;
    }

    public LinkedList<Integer> divide(LinkedList<Integer> list) {
        LinkedList<Integer> newList = new LinkedList();
        if (list.size() == 1)
            return list;

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

    public LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right) {
        LinkedList<Integer> newList = new LinkedList();
        while (!left.isEmpty() && !right.isEmpty())
            if (left.getFirst() < right.getFirst())
                newList.add(left.removeFirst());
            else
                newList.add(right.removeFirst());
        while (!left.isEmpty())
            newList.add(left.removeFirst());
        while (!right.isEmpty())
            newList.add(right.removeFirst());
        return newList;
    }
}
