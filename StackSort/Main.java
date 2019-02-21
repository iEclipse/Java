import java.util.Random;
import java.util.Stack;

/*
 * To sort a Stack Recursively, you will need two functions.
 * 1. The first function instructs the program to recursively pop the top element until the stack is empty.
 * 		Once the stack is completely empty, it then moves onto the process of element insertion.
 * 2. The insertion function begins by checking if the stack is either empty or if the element passed is greater than the element at the top of the stack.
 * 		If the element on the top is greater, then the element passed is pushed onto the stack.
 * 		If the element on the top is lesser or equal, then the function will pop the next element in the stack and recursively call itself using the base passed element.
 * 			- Once the function determines that it does not need to recursively call itself anymore, it will push the saved element while returning to the top of the call stack.
 */

public class Main {

	public static void main(String[] args) {
		Random rn = new Random();
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < 40; i++)
			stack.push(rn.nextInt(100));

		System.out.println("Before:\n" + stack);
		stack = sort(stack);
		System.out.println("\nAter:\n" + stack);
	}

	static Stack sort(Stack<Integer> s) {

		if (!s.isEmpty()) {
			int val = s.pop();

			sort(s);
			sortHelper(s, val);

		}
		return s;
	}

	static Stack sortHelper(Stack<Integer> s, int val) {
		if (s.isEmpty() || val > s.peek()) {
			s.push(val);
			return s;
		} else {
			int temp = s.pop();
			sortHelper(s, val);
			s.push(temp);
			return s;

		}
	}
}
