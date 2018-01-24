import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ProcessTable {

    static ArrayList<ControlBlock> p = new ArrayList();
    static int pid, pc, sp, r0, r1, r2, r3;
    static String program, username;
    static int runningIndex;
    static int nPid = 2;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        int temp;

        p.add(new ControlBlock(1, "init", "root"));

        pid = p.get(0).pc;
        program = p.get(0).program;
        username = p.get(0).username;
        pc = p.get(0).pc;
        sp = p.get(0).sp;
        r0 = p.get(0).r0;
        r1 = p.get(0).r1;
        r2 = p.get(0).r2;
        r3 = p.get(0).r3;
        runningIndex = 0;

        while (true) {
            input = s.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
                case "fork":
                    if (runningIndex != -1) {
                        p.add(new ControlBlock(nPid++, program, username));
                        p.get(p.size() - 1).status = 1;
                        p.get(p.size() - 1).pc = pc;
                        p.get(p.size() - 1).sp = sp;
                        p.get(p.size() - 1).r0 = r0;
                        p.get(p.size() - 1).r1 = r1;
                        p.get(p.size() - 1).r2 = r2;
                        p.get(p.size() - 1).r3 = r3;
                    }
                    break;
                case "kill":
                    if (command.length == 2) {
                        temp = lookup(command[1]);
                        if (temp != -1 && (p.get(temp).username.equals(username) || username.equals("root"))) {
                            if (temp == runningIndex)
                                cpuLoad(0, null);
                            p.remove(temp);
                        }
                    }
                    break;
                case "execve":
                    if (command.length == 3) {
                        if (command[2].equals("root") && !username.equals("root"))
                            break;
                        if (runningIndex != -1) {
                            p.remove(runningIndex);
                            p.add(new ControlBlock(pid, command[1], command[2]));
                        } else
                            p.add(new ControlBlock(nPid++, command[1], command[2]));
                        cpuLoad(1, p.get(p.size() - 1));
                    }
                    break;
                case "block":
                    if (runningIndex != -1) {
                        p.get(runningIndex).status = 2;
                        runRandom();
                    }
                    break;
                case "yield":
                    temp = runningIndex;
                    if (runningIndex != -1)
                        p.get(runningIndex).status = 2;
                    runRandom();
                    if (temp != -1)
                        p.get(temp).status = 1;
                    break;
                case "exit":
                    if (runningIndex != -1) {
                        p.remove(runningIndex);
                        runRandom();
                    }
                    break;
                case "print":
                    print();
                    break;
                case "unblock":
                    if (command.length == 2) {
                        temp = lookup(command[1]);
                        if (temp != -1)
                            p.get(temp).status = 1;
                    }
            }
        }
    }

    public static void runRandom() {
        ArrayList<ControlBlock> possible = new ArrayList();
        Random r = new Random();
        int index;
        for (int i = 0; i < p.size(); i++)
            if (p.get(i).status == 1)
                possible.add(p.get(i));
        if (p.isEmpty() || possible.isEmpty())
            cpuLoad(0, null);
        else {
            index = r.nextInt(possible.size());
            cpuLoad(1, possible.get(index));
        }
    }

    public static void cpuLoad(int key, ControlBlock c) {
        if (key == 0) {
            pid = -1;
            program = "";
            username = "";
            pc = 0;
            sp = 0;
            r0 = 0;
            r1 = 0;
            r2 = 0;
            r3 = 0;
            runningIndex = -1;
        } else {
            pid = c.pid;
            program = c.program;
            username = c.username;
            pc = c.pc;
            sp = c.sp;
            r0 = c.r0;
            r1 = c.r1;
            r2 = c.r2;
            r3 = c.r3;
            runningIndex = p.indexOf(c);
            p.get(runningIndex).status = 0;
        }
    }

    public static int lookup(String pid) {
        for (int i = 0; i < p.size(); i++)
            if (Integer.toString(p.get(i).pid).equals(pid))
                return i;
        return -1;
    }

    public static void print() {
        System.out.println("CPU:");
        System.out.printf("PC = 0x%08x%6s = 0x%08x\nR0 = 0x%08x%6s = 0x%08x\nR2 = 0x%08x%6s = 0x%08x\n\n", pc, "SP", sp, r0, "R1", r1, r2, "R3", r3);
        System.out.printf("%3s%15s%10s%8s%12s%12s%12s%12s%12s%12s\n", "PID", "Program", "User", "Status", "PC", "SP", "R0", "R1", "R2", "R3");
        for (int i = 0; i < p.size(); i++)
            System.out.printf("%3d%15s%10s%8x  0x%08x  0x%08x  0x%08x  0x%08x  0x%08x  0x%08x\n", p.get(i).pid, p.get(i).program, p.get(i).username, p.get(i).status, p.get(i).pc, p.get(i).sp, p.get(i).r0, p.get(i).r1, p.get(i).r2, p.get(i).r3);
    }
}
