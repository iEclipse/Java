import java.util.Random;

public class ControlBlock {

    Random r = new Random();
    int pid, status, pc, sp, r0, r1, r2, r3;
    String program, username;

    ControlBlock(int pid, String program, String username) {
        this.pid = pid;
        this.status = 0;
        this.pc = r.nextInt(Integer.MAX_VALUE);
        this.sp = r.nextInt(Integer.MAX_VALUE);
        this.r0 = r.nextInt(Integer.MAX_VALUE);
        this.r1 = r.nextInt(Integer.MAX_VALUE);
        this.r2 = r.nextInt(Integer.MAX_VALUE);
        this.r3 = r.nextInt(Integer.MAX_VALUE);
        this.program = program;
        this.username = username;
    }
}
