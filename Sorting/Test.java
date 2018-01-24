package cs331.sorting.andrewtrang;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random rn = new Random();
        InsertionSort is = new InsertionSort();
        InsertionSort is2 = new InsertionSort();
        MergeInsertionSort mis = new MergeInsertionSort();
        MergeSort ms = new MergeSort();
        QuickSort qs = new QuickSort();
        QuickSortMean qsm = new QuickSortMean();
        QuickSortRandom qsr = new QuickSortRandom();
        int testSize = (int)Math.pow(2, 14);
        System.out.println("List Size: " + testSize + "\n");

        List a = new LinkedList();
        for (int i = 0; i < testSize; i++) {
            a.add(rn.nextInt(testSize));
        }

        long startTime;
        long endTime;
        long elapsedTime;

        ///////////////////////
        is.init(a);
        is.getSortedList();
        
        is.init(a);
        startTime = System.nanoTime();
        is.getSortedList();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("Insertion: " + (elapsedTime));
        
        ///////////////////////
        mis.init(a);
        startTime = System.nanoTime();
        mis.getSortedList();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("MergeInsertion: " + (elapsedTime));

        //////////////////////////////
        ms.init(a);
        startTime = System.nanoTime();
        ms.getSortedList();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("Merge: " + (elapsedTime));

        /////////////////////////
        qs.init(a);
        startTime = System.nanoTime();
        qs.getSortedList();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("Quick: " + (elapsedTime));

        /////////////////////////
        qsm.init(a);
        startTime = System.nanoTime();
        qsm.getSortedList();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("QuickMean: " + (elapsedTime));

        /////////////////////////
        qsr.init(a);
        startTime = System.nanoTime();
        qsr.getSortedList();
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("QuickRandom: " + (elapsedTime));
    }
}
