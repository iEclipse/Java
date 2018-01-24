
import java.util.Scanner;

public class FileSystem {

    public static void main(String[] args) {
        Structure s = new Structure();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Command: ");
                String[] input = sc.nextLine().split(" ");
                if (input.length == 3 && input[0].equals("put") && Integer.parseInt(input[2]) > 0) {
                    s.put(input[1], Integer.parseInt(input[2]));
                } else if (input.length == 2 && input[0].equals("del")) {
                    s.del(input[1]);
                } else if (input.length == 1 && input[0].equals("bitmap")) {
                    s.bitmap();
                } else if (input.length == 1 && input[0].equals("inodes")) {
                    s.inodes();
                } else if (input.length == 1 && input[0].equals("exit")) {
                    System.exit(0);
                }else
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("Error: Invalid command.");
            };
        }
    }
}
