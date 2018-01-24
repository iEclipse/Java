
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class ProcessScheduler {

    static LinkedList<Process> p = new LinkedList();
    static int count;

    public static void main(String[] args) {
        Scanner fs;
        String[] temp;

        try {
            fs = new Scanner(new File(args[0]));

            while (fs.hasNext()) {
                temp = fs.nextLine().split(",");
                p.add(new Process(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                count++;
            }
            fcfs();
            sf();
            q(50);
            q(100);
            r(50);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
    }

    public static void fcfs() {
        double att = 0;
        int time = 0;
        System.out.println("Running First-come, first-served scheduler.");

        for (int i = 0; i < p.size(); i++) {
            time += p.get(i).cycles;
            System.out.println("Process " + p.get(i).pid + " finishes on cycle " + time + ".");
            att += time;
        }
        att /= count;
        System.out.printf("Average turnaround time: %.2f.\n\n", att);
    }

    public static void sf() {
        LinkedList<Process> tempList = new LinkedList<>();
        double att = 0;
        int time = 0, temp;

        for (int i = 0; i < p.size(); i++)
            tempList.add(p.get(i));
        System.out.println("Running shortest first scheduler.");
        Process current = null;

        while (!tempList.isEmpty()) {
            temp = Integer.MAX_VALUE;
            for (int i = 0; i < tempList.size(); i++)
                if (tempList.get(i).cycles < temp) {
                    current = tempList.get(i);
                    temp = current.cycles;
                }
            tempList.remove(tempList.indexOf(current));
            time += temp;
            System.out.println("Process " + current.pid + " finishes on cycle " + time + ".");
            att += time;
        }
        att /= count;
        System.out.printf("Average turnaround time: %.2f.\n\n", att);
    }

    public static void q(int quantum) {
        LinkedList<Process> tempList1 = new LinkedList<>();
        LinkedList<Process> tempList2 = new LinkedList<>();
        Process temp = null;
        int[] time;
        int increment = 1;
        int passes = 0;
        int offset = 0;
        double att = 0;

        System.out.println("Running round robin scheduler with quantum " + quantum + ".");

        for (int i = 0; i < p.size(); i++)
            tempList1.add(p.get(i));
        time = new int[tempList1.size()];
        Arrays.fill(time, 0);

        while (tempList1.size() != tempList2.size()) {
            for (int i = 0; i < tempList1.size(); i++) {
                temp = tempList1.get(i);
                if (!tempList2.contains(temp)) {
                    passes++;
                    if (increment * quantum > temp.cycles) {
                        offset += (increment * quantum - temp.cycles);
                        time[temp.pid - 1] = quantum * passes - offset;
                        System.out.println("Process " + temp.pid + " finishes on cycle " + time[temp.pid - 1] + ".");
                        tempList2.add(temp);
                    } else if (increment * quantum == temp.cycles) {
                        time[temp.pid - 1] = quantum * passes - offset;
                        System.out.println("Process " + temp.pid + " finishes on cycle " + time[temp.pid - 1] + ".");
                        tempList2.add(temp);
                    }
                }
            }
            increment++;
        }
        for (int i = 0; i < time.length; i++)
            att += time[i];
        att /= count;
        System.out.printf("Average turnaround time: %.2f.\n\n", att);
    }

    public static void r(int quantum) {
        LinkedList<Process> tempList1 = new LinkedList<>();
        LinkedList<Process> tempList2 = new LinkedList<>();
        Random rn = new Random();
        Process temp = null;
        int[] timeRemaining = new int[p.size()];;
        int[] probabilities;
        int[] time;
        int total = 0;
        int passes = 1;
        int offset = 0;
        double att = 0;
        int selector;

        System.out.println("Running random scheduler with quantum " + quantum + ".");

        for (int i = 0; i < p.size(); i++) {
            tempList1.add(p.get(i));
            total += p.get(i).cycles;
            timeRemaining[i] = p.get(i).cycles;
        }
        time = new int[tempList1.size()];
        Arrays.fill(time, 0);

        while (!tempList1.isEmpty()) {
            total = 0;
            probabilities = new int[tempList1.size()];
            for (int i = 0; i < tempList1.size(); i++) {
                probabilities[i] = total + timeRemaining[tempList1.get(i).pid - 1];
                total += timeRemaining[tempList1.get(i).pid - 1];
            }
            selector = rn.nextInt(total);
            for (int i = 0; i < tempList1.size(); i++)
                if (selector <= probabilities[i]) {
                    temp = tempList1.get(i);
                    break;
                }
            if (quantum > timeRemaining[temp.pid - 1]) {
                offset += (quantum - timeRemaining[temp.pid - 1]);
                time[temp.pid - 1] = quantum * passes - offset;
                System.out.println("Process " + temp.pid + " finishes on cycle " + time[temp.pid - 1] + ".");
                tempList2.add(temp);
            } else if (quantum == timeRemaining[temp.pid - 1]) {
                time[temp.pid - 1] = quantum * passes - offset;
                System.out.println("Process " + temp.pid + " finishes on cycle " + time[temp.pid - 1] + ".");
                tempList2.add(temp);
            } else
                timeRemaining[temp.pid - 1] -= quantum;
            tempList1.removeAll(tempList2);
            tempList2.clear();
            passes++;
        }

        for (int i = 0; i < time.length; i++)
            att += time[i];
        att /= count;

       System.out.printf("Average turnaround time: %.2f.\n\n", att);
    }
}
