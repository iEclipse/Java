import java.util.Scanner;

public class Project4 {

	// Postfix & Prefix Expressions
	static String convertedExpression1;
	static String convertedExpression2;

	public static void main(String[] args) {
		Conversion cv = new Conversion();
		Scanner sc = new Scanner(System.in);
		String expression;
		System.out.print("Stack Application & Expressions\n============================================\n[Empty Input Terminates the Program.]\n");

		// Program Cycle
		// ===============
		// 1) Polls User for Input
		// 2) Removes Whitespace
		// 3) Polls User for Input
		// 3) Converts Expressions
		// 4) Exits on Empty Input
		do {
			System.out.print("\nEnter an expression: ");
			expression = sc.nextLine();
			expression = expression.replace(" ", "");

			if (expression.length() > 0 && cv.getPostfix(expression) != null && cv.getPrefix(expression) != null)
				System.out.println("Postfix: " + convertedExpression1 + "\nPrefix: " + convertedExpression2);
		} while (expression.length() > 0);
		System.out.println("Program terminated.");
	}
}
