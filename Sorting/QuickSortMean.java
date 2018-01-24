package cs331.sorting.andrewtrang;

import java.util.LinkedList;

public class QuickSortMean extends QuickSort {

    @Override
    public void sort(LinkedList<Integer> list) {
        sort(list, list.size() / 2);
    }

    public void sort(LinkedList<Integer> list, int mean) {
        LinkedList<Integer> lower = new LinkedList<>();
        LinkedList<Integer> same = new LinkedList<>();
        LinkedList<Integer> higher = new LinkedList<>();
        int pivot = Integer.MAX_VALUE;
        if (list.size() <= 1)
            return;

        for (int i = 0; i < list.size(); i++)
            if (pivot == mean)
                break;
            else if (Math.abs(list.listIterator(i).next() - mean) < Math.abs(pivot - mean))
                pivot = list.listIterator(i).next();
        while (!list.isEmpty())
            if (list.getFirst() < pivot)
                lower.add(list.removeFirst());
            else if (list.getFirst() == pivot)
                same.add(list.removeFirst());
            else
                higher.add(list.removeFirst());
        sort(lower, pivot - pivot / 2);
        sort(higher, pivot + pivot / 2);
        while (!lower.isEmpty())
            list.add(lower.removeFirst());
        while (!same.isEmpty())
            list.add(same.removeFirst());
        while (!higher.isEmpty())
            list.add(higher.removeFirst());
    }
}
