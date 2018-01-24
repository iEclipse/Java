// Converts Expressions to Different Notations
public class Conversion extends ErrorMessage {

	// ==========================
	// FUNCTION: Takes in a String Expression and Converts to Postfix Notation
	// PARAMETERS: String Expression
	// ==========================
	public String getPostfix(String expression) {
		Stack<Character> operators = new Stack<Character>();
		String text = "";

			// Loop for Each Character in the String
			for (int i = 0, p; i < expression.length(); i++) {
				char character = expression.charAt(i);
				p = getPrecedence(character);
				// Expression Check
				if (!validCheck(p, expression, i)) {
					stop(notValid());
					return null;
				}
				// If Variable then Add to the String
				else if (p == 1) {
					text += character;
				}
				// If Starting Parentheses then Push to the Operator Stack
				else if (p == 0)
					operators.push(character);
				// If Ending Parentheses then Evaluate until Start Parentheses is Found
				else if (p == 4) {
					if (!operators.isEmpty()) {
						while (getPrecedence(operators.top().element) != 0) {
							text += operators.top().element;
							operators.pop();
							if (operators.isEmpty()) {
								stop(noMatchP() + noMatchP1());
								return null;
							}
						}
						if (operators.isEmpty())
							return null;
						operators.pop();
					}
					else {
						stop(noMatchP() + noMatchP1());
						return null;
					}
				}
				// If Operator Stack is Empty Push the Character to the Stack
				// If Current Index has Higher Precedence then Push to the Stack
				else if (operators.isEmpty() || p > getPrecedence(operators.top().element)) {
					operators.push(character);
				}
				// If Current Index has Lower Precedence then Evaluate until Higher then Push to the Stack
				else {
					while (!operators.isEmpty() && p <= getPrecedence(operators.top().element)) {
						text += operators.top().element;
						operators.pop();
					}
					operators.push(character);
				}
			}
			// If Symbols Remain in the Stack then Add to String
			// If Extra Parentheses then Return Error Message
			while (!operators.isEmpty()) {
				if (getPrecedence(operators.top().element) == 0) {
					stop(noMatchP() + noMatchP2());
					return null;
				}
				text += operators.top().element;
				operators.pop();
			}
		Project4.convertedExpression1 = text;
		return text;
	}
	// ==========================
	// FUNCTION: Converts to Prefix Notation
	// PARAMETERS: String Expression
	// ==========================
	public String getPrefix(String expression) {
		Stack<Character> operators = new Stack<Character>();
		Stack<String> variables = new Stack<String>();
		String text = "";
		String left, right, op;
		
			// Loop for Each Character in the String
			for (int i = 0, p; i < expression.length(); i++) {
				char character = expression.charAt(i);
				p = getPrecedence(character);
				// Expression Check
				if (!validCheck(p, expression, i)) {
					stop(notValid());
					return null;
				}
				// If Variable then Add to the Variable Stack
				else if (p == 1) {
					variables.push(Character.toString(character));
				}
				// If Starting Parentheses then Push to the Operator Stack
				else if (p == 0)
					operators.push(character);
				// If Ending Parentheses then Push to Both Stacks
				// Pop Variable Stack Twice and Assign to Right then Left
				// Pop Operator Stack and Assign to Op
				// Loop Until Starting Parentheses Found
				else if (p == 4) {
					while (getPrecedence(operators.top().element) != 0) {
						if (variables.getSize() < 2) {
							stop(unevenVars());
							return null;
						}
						op = Character.toString(operators.top().element);
						operators.pop();
						right = variables.top().element;
						variables.pop();
						left = variables.top().element;
						variables.pop();
						variables.push(op + left + right);
						if (operators.isEmpty()) {
							stop(noMatchP() + noMatchP1());
							return null;
						}
					}
					if (operators.isEmpty())
						return null;
					operators.pop();
				}
				// If Operator Stack is Empty Push the Character to the Stack
				// If Current Index has Higher Precedence then Push to the Stack
				else if (operators.isEmpty() || p > getPrecedence(operators.top().element)) {
					operators.push(character);
				}
				// If Current Index has Lower Precedence then Evaluate until Higher then Push to the Stack
				else {
					while (!operators.isEmpty() && p <= getPrecedence(operators.top().element)) {
						if (variables.getSize() < 2) {
							stop(unevenVars());
							return null;
						}
						op = Character.toString(operators.top().element);
						operators.pop();
						right = variables.top().element;
						variables.pop();
						left = variables.top().element;
						variables.pop();
						variables.push(op + left + right);
					}
					operators.push(character);
				}
			}
			// If Symbols Remain in the Stacks Evaluate until Empty
			// If Extra Parentheses then Return Error Message
			while (!operators.isEmpty() && !variables.isEmpty()) {
				if (getPrecedence(operators.top().element) == 0) {
					stop(noMatchP() + noMatchP2());
					return null;
				}
				if (variables.getSize() < 2) {
					stop(unevenVars());
					return null;
				}
				op = Character.toString(operators.top().element);
				operators.pop();
				right = variables.top().element;
				variables.pop();
				left = variables.top().element;
				variables.pop();
				variables.push(op + left + right);
			}
			if (!variables.isEmpty())
				text = variables.top().element;
		Project4.convertedExpression2 = text;
		return text;
	}
	// ==========================
	// FUNCTION: Checks if Expression is Valid
	// PARAMETERS: Precedence of First Variable, Expression, Index of the Expression
	// ==========================
	public boolean validCheck(int c1, String expression, int index) {
		if (index + 1 < expression.length()) {
			int c2 = getPrecedence(expression.charAt(index + 1));
			if (c1 == -1 || c2 == -1)
				return false;
			else if (c1 == 1 && c2 == 1)
				return false;
			else if ((c1 == 2 || c1 == 3) && (c2 == 2 || c2 == 3))
				return false;
			else if ((c1 == 4 && c2 == 0) || (c1 == 1 && c2 == 0))
				return false;
			else
				return true;
		} else if (c1 < 1 || c1 > 1 && expression.length() < 2){
			return false;}
		else
			return true;
	}
	// ==========================
	// FUNCTION: Returns Precedence Level of a Character
	// PARAMETERS: Character
	// ==========================
	public int getPrecedence(Character c) {
		if (c.equals(')'))
			return 4;
		else if (c.equals('*') || c.equals('/') || c.equals('%'))
			return 3;
		else if (c.equals('+') || c.equals('-'))
			return 2;
		else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
			return 1;
		else if (c.equals('('))
			return 0;
		else {
			return -1;
		}
	}
	// ==========================
	// User Interface Methods
	// ==========================
	public void stop(String reason) {
		System.out.println("Syntax Error: " + reason);
	}
}
