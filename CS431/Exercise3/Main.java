
public class Main {

    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = new BlockingQueue<>(100);
        Runnable r = () -> { // replace lambda if you don’t have access to Java 8
            for (int i = 0; i < 200; i++)
                try {
                    int n = queue.dequeue();
                    System.out.println(n + " removed");
                    Thread.sleep(500);
                } catch (Exception e) {
                }
        };
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("Adding " + i);
            queue.enqueue(i);
        }
    }
}
