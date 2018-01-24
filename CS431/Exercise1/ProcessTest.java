import java.io.IOException;

public class ProcessTest {

    public static void main(String[] args) throws IOException {
        ProcessBuilder p = new ProcessBuilder();
        p.command("java");
        Process java = p.start();
        System.out.println(java.getOutputStream());
    }
}
