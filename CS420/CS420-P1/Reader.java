import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("Input.txt");
		RandomBoard b = new RandomBoard();
		Search aStar = new Search();

		if (f.exists()) {
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {
				String s = sc.nextLine();
				String[] input = new String[9];
				System.out.println(s);
				if (s.charAt(0) >= '0' && s.charAt(0) <= '8') {
					for (int i = 0; i < 9; i++)
						input[i] = (s.substring(i, i + 1));
					b.generate(input);
					System.out.println("\nSOLVING USING H1");
					aStar.search(b.board, 0);
					System.out.println("\nSOLVING USING H2");
					aStar.search(b.board, 1);
				}
			}
		} else {
			System.out.println("Input.txt Doesn't Exist.");
		}
	}
}
