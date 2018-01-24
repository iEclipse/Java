package cs331.sorting.andrewtrang;

import java.util.LinkedList;
import java.util.Random;

public class QuickSortRandom extends QuickSort {

    @Override
    public void sort(LinkedList<Integer> list) {
        LinkedList<Integer> lower = new LinkedList<>();
        LinkedList<Integer> same = new LinkedList<>();
        LinkedList<Integer> higher = new LinkedList<>();
        Random rn = new Random();
        if (list.size() <= 1)
            return;
        int pivot = list.listIterator(rn.nextInt(list.size())).next();
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
