package cs331.sorting.andrewtrang;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class InsertionSort implements Sort {

    LinkedList<Integer> sorted = new LinkedList<>();

    @Override
    public void init(List list) {
        sorted.addAll(list);
    }

    @Override
    public List getSortedList() {
        LinkedList<Integer> temp = new LinkedList<>();
        ListIterator<Integer> iterator1 = sorted.listIterator();
        int current;
        if (sorted.size() <= 1)
            return sorted;
        current = iterator1.next();
        temp.add(current);
        while (iterator1.hasNext()) {
            ListIterator<Integer> iterator2 = temp.listIterator(0);
            current = iterator1.next();
            while (iterator2.hasNext()) {
                int temp2 = iterator2.next();
                if (temp2 > current) {
                    iterator2.previous();
                    break;
                }
            }
            iterator2.add(current);
        }
        return temp;
    }
}
