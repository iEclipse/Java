//List of Error Messages
public abstract class ErrorMessage {
	String endOfStack() {
		return "Reached the end of the stack.";
	}
	String noInput() {
		return "You did not specify an input.";
	}
	String notValid() {
		return "Not a valid expression.";
	}
	String invalidSymbol() {
		return "Invalid symbol used.";
	}
	String noMatchP() {
		return "Parentheses mismatch.";
	}
	String noMatchP1() {
		return " Missing starting parentheses.";
	}
	String noMatchP2() {
		return " Missing ending parentheses.";
	}
	String unevenVars() {
		return "Uneven number of operands.";
	}
}
