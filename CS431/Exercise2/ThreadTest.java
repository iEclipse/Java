
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadTest {

    static BlockingQueue<String> fileList = new LinkedBlockingQueue();
    static BlockingQueue<String> queue = new LinkedBlockingQueue();
    static int[] upper = new int[26];
    static int[] lower = new int[26];
    static int[] num = new int[10];
    static volatile boolean running = true;
    static Thread io = new Thread(new IO());
    static Thread r = new Thread(new Read());
    static Thread p = new Thread(new Process());

    private static final class IO implements Runnable {

        public void run() {
            Scanner s = new Scanner(System.in);

            String[] input;
            while (running)
                try {
                    System.out.print("Command: ");
                    input = s.nextLine().split(" ");
                    if (input.length > 0 && input[0].equals("read"))
                        fileList.put(input[1]);
                    else if (input[0].equals("counts")) {
                        for (int i = 0; i < upper.length; i++)
                            System.out.printf("%3s", (char) ('A' + i));
                        System.out.println();
                        for (int i = 0; i < upper.length; i++)
                            System.out.printf("%3d", upper[i]);
                        System.out.println("\n");
                        for (int i = 0; i < lower.length; i++)
                            System.out.printf("%3s", (char) ('a' + i));
                        System.out.println();
                        for (int i = 0; i < lower.length; i++)
                            System.out.printf("%3d", lower[i]);
                        System.out.println("\n");
                        for (int i = 0; i < num.length; i++)
                            System.out.printf("%3s", (char) ('0' + i));
                        System.out.println();
                        for (int i = 0; i < num.length; i++)
                            System.out.printf("%3d", num[i]);
                        System.out.println("\n");
                    } else if (input[0].equals("exit")) {
                        r.interrupt();
                        p.interrupt();
                        running = false;
                    }
                } catch (InterruptedException ex) {
                }
        }
    }

    private static final class Read implements Runnable {

        Scanner fs;

        public void run() {
            try {
                while (running) {
                    fs = new Scanner(new File(fileList.take()));
                    while (fs.hasNextLine())
                        queue.put(fs.nextLine());
                }
            } catch (FileNotFoundException | InterruptedException ex) {

            }
        }

    }

    private static final class Process implements Runnable {

        String text;

        public void run() {
            while (running)
                try {
                    text = queue.take();
                    for (int i = 0; i < text.length(); i++)
                        if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
                            upper[text.charAt(i) - 'A']++;
                        else if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
                            lower[text.charAt(i) - 'a']++;
                        else if (text.charAt(i) >= '0' && text.charAt(i) <= '9')
                            num[text.charAt(i) - '0']++;
                } catch (InterruptedException ex) {
                }
        }
    }

    public static void main(String[] args) {
        Arrays.fill(upper, 0);
        Arrays.fill(lower, 0);
        Arrays.fill(num, 0);
        io.start();
        r.start();
        p.start();
    }
}
