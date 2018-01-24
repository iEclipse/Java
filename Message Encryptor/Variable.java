import java.io.Serializable;

public class Variable implements Serializable {

	private static final long serialVersionUID = -2871041525378491429L;
	
	char[] a;
	char[] b;
	char[] c;

	protected void assign(char[] input1, char[] input2, char[] input3) {
		a = input1;
		b = input2;
		c = input3;
	}

	protected char[] getA() {
		return a;
	}
	protected char[] getB() {
		return b;
	}
	protected char[] getC() {
		return c;
	}
}