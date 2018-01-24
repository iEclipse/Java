package cs331.sorting.andrewtrang;

import java.util.LinkedList;
import java.util.List;

public class QuickSort implements Sort {

    LinkedList<Integer> sorted = new LinkedList<>();
    int index = 0;

    @Override
    public void init(List list) {
        sorted.addAll(list);
    }

    @Override
    public List getSortedList() {
        sort(sorted);
        return sorted;
    }

    public void sort(LinkedList<Integer> list) {
        LinkedList<Integer> lower = new LinkedList<>();
        LinkedList<Integer> same = new LinkedList<>();
        LinkedList<Integer> higher = new LinkedList<>();
        if (list.size() <= 1)
            return;
        int pivot = list.getFirst();
        while (!list.isEmpty())
            if (list.getFirst() < pivot)
                lower.add(list.removeFirst());
            else if (list.getFirst() == pivot)
                same.add(list.removeFirst());
            else
                higher.add(list.removeFirst());
        sort(lower);
        sort(higher);
        while (!lower.isEmpty())
            list.add(lower.removeFirst());
        while (!same.isEmpty())
            list.add(same.removeFirst());
        while (!higher.isEmpty())
            list.add(higher.removeFirst());
    }
}
