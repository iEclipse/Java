public class Recursion {

	// Linear search using recursion
	public static boolean linearSearch(String value, int start, String[] array) {
		if (array[start].equals(value))
			return true;
		else if (start >= array.length - 1)
			return false;
		else
			return linearSearch(value, start + 1, array);
	}
	// Binary search using recursion
	public static boolean binarySearch(int value, int start, int end, int[] array) {
		int mid = (start + end) / 2;
		if (value == array[mid])
			return true;
		else if (start > end)
			return false;
		else if (value < array[mid])
			return binarySearch(value, start, mid, array);
		else
			return binarySearch(value, mid + 1, end, array);
	}
	// Fibonacci numbers using recursion
	public static int fibonacci(int n) {
		if (n == 0 || n == 1)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
	// Print array
	public static <E> void printArray(E[] array, int start) {
		System.out.print(array[start] + " ");
		if (start < array.length - 1)
			printArray(array, start + 1);
	}
	// Reverse print array
	public static <E> void reverseArray(E[] array, int start) {
		if (start < array.length - 1)
			reverseArray(array, start + 1);
		System.out.print(array[start] + " ");
	}
	// Test Method
	public static void main(String[] args) {
		String[] array = {"apple", "banana", "blueberry", "strawberry", "melon", "canteloupe", "kiwi", "pineapple", "coconut", "durian", "dragon fruit", "pear", "lychee", "grape", "cherry"};
		int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		// System.out.println(linearSearch("apple", 0, array));
		// System.out.println(binarySearch(15, 0, array2.length-1, array2));
		// System.out.println(fibonacci(45));
		printArray(array, 0);
	}
}
