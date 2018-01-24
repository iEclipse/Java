/**
 * @author Andrew Trang
 * 
 */

package prog_assgmnt_2;

import java.util.Random;

public class Main {

	/**
	 * This method starts the testing process.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}

	/**
	 * This method prints out the time it takes to sort each case.
	 * 
	 * @param array
	 *            The array to be sorted
	 * @param num
	 *            The number of indexes for radix sort
	 */
	public static void timer(int[] array, int num) {
		long initT;
		SortingAlgorithms sa = new SortingAlgorithms();
		ArrayHeap<Integer> ah = new ArrayHeap<Integer>();
		initT = System.nanoTime();
		sa.bubbleSort(array);
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		initT = System.nanoTime();
		sa.selectionSort(array);
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		initT = System.nanoTime();
		sa.insertionSort(array);
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		Integer[] temp = new Integer[array.length];
		for (int i = 0; i < array.length; i++)
			temp[i] = Integer.valueOf(array[i]);
		initT = System.nanoTime();
		ah.fromArray(temp);
		ah.getSortedContents();
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		initT = System.nanoTime();
		sa.mergeSort(array);
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		initT = System.nanoTime();
		sa.quickSort(array);
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		initT = System.nanoTime();
		sa.countingSort(array);
		System.out.printf("%13dms", (System.nanoTime() - initT) / 1000000);
		initT = System.nanoTime();
		sa.radixSort(array, num);
		System.out.printf("%13dms\n", (System.nanoTime() - initT) / 1000000);
	}

	/**
	 * This method creates the arrays to be tested.
	 */
	public static void test() {
		Random rn = new Random();
		int[] array1 = new int[1000];
		int[] array2 = new int[10000];
		int[] array3 = new int[100000];
		int[] array4 = new int[1000000];
		System.out.println("Size(1000)\n");
		System.out.printf("%15s%15s%15s%15s%15s%15s%15s%15s\n", "Bubble", "Selection", "Insertion", "Heap", "Merge",
				"Quick", "Counting", "Radix");
		for (int i = 0; i < array1.length; ++i)
			array1[i] = rn.nextInt(10);
		timer(array1, 1);
		for (int i = 0; i < array1.length; ++i)
			array1[i] = rn.nextInt(100);
		timer(array1, 2);
		for (int i = 0; i < array1.length; ++i)
			array1[i] = rn.nextInt(1000);
		timer(array1, 3);
		for (int i = 0; i < array1.length; ++i)
			array1[i] = rn.nextInt(10000);
		timer(array1, 4);
		for (int i = 0; i < array1.length; ++i)
			array1[i] = rn.nextInt(100000);
		timer(array1, 5);
		for (int i = 0; i < array1.length; ++i)
			array1[i] = rn.nextInt(1000000);
		timer(array1, 6);
		System.out.println("\nSize(10000)\n");
		System.out.printf("%15s%15s%15s%15s%15s%15s%15s%15s\n", "Bubble", "Selection", "Insertion", "Heap", "Merge",
				"Quick", "Counting", "Radix");
		for (int i = 0; i < array2.length; ++i)
			array2[i] = rn.nextInt(10);
		timer(array2, 1);
		for (int i = 0; i < array2.length; ++i)
			array2[i] = rn.nextInt(100);
		timer(array2, 2);
		for (int i = 0; i < array2.length; ++i)
			array2[i] = rn.nextInt(1000);
		timer(array2, 3);
		for (int i = 0; i < array2.length; ++i)
			array2[i] = rn.nextInt(10000);
		timer(array2, 4);
		for (int i = 0; i < array2.length; ++i)
			array2[i] = rn.nextInt(100000);
		timer(array2, 5);
		for (int i = 0; i < array2.length; ++i)
			array2[i] = rn.nextInt(1000000);
		timer(array2, 6);
		System.out.println("\nSize(100000)\n");
		System.out.printf("%15s%15s%15s%15s%15s%15s%15s%15s\n", "Bubble", "Selection", "Insertion", "Heap", "Merge",
				"Quick", "Counting", "Radix");
		for (int i = 0; i < array3.length; ++i)
			array3[i] = rn.nextInt(10);
		timer(array3, 1);
		for (int i = 0; i < array3.length; ++i)
			array3[i] = rn.nextInt(100);
		timer(array3, 2);
		for (int i = 0; i < array3.length; ++i)
			array3[i] = rn.nextInt(1000);
		timer(array3, 3);
		for (int i = 0; i < array3.length; ++i)
			array3[i] = rn.nextInt(10000);
		timer(array3, 4);
		for (int i = 0; i < array3.length; ++i)
			array3[i] = rn.nextInt(100000);
		timer(array3, 5);
		for (int i = 0; i < array3.length; ++i)
			array3[i] = rn.nextInt(1000000);
		timer(array3, 6);
		System.out.println("\nSize(1000000)\n");
		System.out.printf("%15s%15s%15s%15s%15s%15s%15s%15s\n", "Bubble", "Selection", "Insertion", "Heap", "Merge",
				"Quick", "Counting", "Radix");
		for (int i = 0; i < array4.length; ++i)
			array4[i] = rn.nextInt(10);
		timer(array4, 1);
		for (int i = 0; i < array4.length; ++i)
			array4[i] = rn.nextInt(100);
		timer(array4, 2);
		for (int i = 0; i < array4.length; ++i)
			array4[i] = rn.nextInt(1000);
		timer(array4, 3);
		for (int i = 0; i < array4.length; ++i)
			array4[i] = rn.nextInt(10000);
		timer(array4, 4);
		for (int i = 0; i < array4.length; ++i)
			array4[i] = rn.nextInt(100000);
		timer(array4, 5);
		for (int i = 0; i < array4.length; ++i)
			array4[i] = rn.nextInt(1000000);
		timer(array4, 6);
	}
}