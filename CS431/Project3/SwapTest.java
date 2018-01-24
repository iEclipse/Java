
import java.util.ArrayList;
import java.util.Scanner;

public class SwapTest {

    static ArrayList<Job> jobs = new ArrayList<>();
    static Segment head = new Segment(0, 0, 100, null);
    static Segment temp, temp2, temp3;
    static int segIndex, closest, counter;
    static String[] input;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        temp = head;
        while (true) {
            input = s.nextLine().split(" ");
            switch (input[0]) {
                case "add":
                    add();
                    break;
                case "jobs":
                    jobs();
                    break;
                case "list":
                    list();
                    break;
                case "ff":
                    ff();
                    break;
                case "nf":
                    nf();
                    break;
                case "bf":
                    bf();
                    break;
                case "wf":
                    wf();
                    break;
                case "de":
                    de();
                    break;
                default:
                    System.out.println("Error: Invalid Command");
            }
        }
    }

    static void add() {
        try {
            if (input.length < 3 || Integer.parseInt(input[1]) < 1 || Integer.parseInt(input[2]) < 1 || Integer.parseInt(input[2]) > 100) {
                System.out.println("Error: Invalid Input.");
                return;
            }
            for (int i = 0; i < jobs.size(); i++)
                if (jobs.get(i).getPid() == Integer.parseInt(input[1])) {
                    System.out.println("Error: Job Already Exists.");
                    return;
                }
            jobs.add(new Job(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Input.");
        }
    }

    static void jobs() {
        for (int i = 0; i < jobs.size(); i++)
            System.out.print(jobs.get(i).toString() + " ");
        System.out.println();
    }

    static void list() {
        temp3 = head;
        do {
            System.out.print(temp3.toString() + " ");
            temp3 = temp3.getNext();
        } while (temp3 != null);
        System.out.println();
    }

    //Finds first space available
    static boolean ff() {
        try {
            if (input.length < 2 || Integer.parseInt(input[1]) < 1) {
                System.out.println("Error: Invalid Input.");
                return false;
            }
            if (!segmentExists(input[1]) && jobExists(input[1]))
                if (head.getPid() == 0 && head.getLength() >= getJobSize(input[1])) {
                    if (getJobSize(input[1]) == 100)
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), null);
                    else {
                        head.setLength(head.getLength() - getJobSize(input[1]));
                        head.setStart(getJobSize(input[1]));
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), head);
                        if (head.getNext().getLength() == 0)
                            head.setNext(head.getNext().getNext());
                    }
                    temp = head;
                    return true;
                } else {
                    temp = head;
                    while (temp.getNext() != null) {
                        temp2 = temp;
                        temp = temp.getNext();
                        if (temp.getPid() == 0 && temp.getLength() >= getJobSize(input[1])) {
                            temp.setLength(temp.getLength() - getJobSize(input[1]));
                            temp.setStart(temp.getStart() + getJobSize(input[1]));
                            if (temp.getLength() == 0)
                                temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp.getNext()));
                            else
                                temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp));
                            return true;
                        }
                    }
                    System.out.println("Error: Not enough space.");
                }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Input.");
        }
        return false;
    }

    //Uses last known index and finds next fit
    //NOTE: Deallocating will modify the pointer (Not sure if it's supposed to, but easy fix either way)
    //
    static boolean nf() {
        try {
            if (input.length < 2 || Integer.parseInt(input[1]) < 1) {
                System.out.println("Error: Invalid Input.");
                return false;
            }
            if (!segmentExists(input[1]) && jobExists(input[1]))
                if (temp == head && head.getPid() == 0 && head.getLength() >= getJobSize(input[1])) {
                    if (getJobSize(input[1]) == 100)
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), null);
                    else {
                        head.setLength(head.getLength() - getJobSize(input[1]));
                        head.setStart(getJobSize(input[1]));
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), head);
                        if (head.getNext().getLength() == 0)
                            head.setNext(head.getNext().getNext());
                    }
                    temp = head;
                    return true;
                } else {
                    while (temp != null) {
                        if (temp.getPid() == 0 && temp.getLength() >= getJobSize(input[1])) {
                            temp.setLength(temp.getLength() - getJobSize(input[1]));
                            temp.setStart(temp.getStart() + getJobSize(input[1]));
                            if (temp.getLength() == 0)
                                temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp.getNext()));
                            else
                                temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp));
                            temp = temp2;
                            return true;
                        }
                        temp2 = temp;
                        temp = temp.getNext();
                    }
                    System.out.println("Error: Not enough space.");
                }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Input.");
        }
        return false;
    }

    //Uses space that fits best
    static boolean bf() {
        try {
            if (input.length < 2 || Integer.parseInt(input[1]) < 1) {
                System.out.println("Error: Invalid Input.");
                return false;
            }
            if (!segmentExists(input[1]) && jobExists(input[1])) {
                temp = head;
                closest = Integer.MAX_VALUE;
                segIndex = -1;
                counter = 0;

                if (head.getPid() == 0 && head.getLength() >= getJobSize(input[1])) {
                    closest = Math.abs(head.getLength() - getJobSize(input[1]));
                    segIndex = 0;
                }

                while (temp.getNext() != null) {
                    temp2 = temp;
                    temp = temp.getNext();
                    if (temp.getPid() == 0 && temp.getLength() >= getJobSize(input[1]) && Math.abs(temp.getLength() - getJobSize(input[1])) < closest) {
                        closest = Math.abs(temp.getLength() - getJobSize(input[1]));
                        segIndex = counter + 1;
                    }
                    counter++;
                }
                if (segIndex == -1) {
                    System.out.println("Error: Not enough space.");
                    return false;
                } else if (segIndex == 0) {
                    if (getJobSize(input[1]) == 100)
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), null);
                    else {
                        head.setLength(head.getLength() - getJobSize(input[1]));
                        head.setStart(getJobSize(input[1]));
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), head);
                        if (head.getNext().getLength() == 0)
                            head.setNext(head.getNext().getNext());
                    }
                    temp = head;
                    return true;
                }
                temp = head;
                for (int i = 0; i < segIndex; i++) {
                    temp2 = temp;
                    temp = temp.getNext();
                }
                temp.setLength(temp.getLength() - getJobSize(input[1]));
                temp.setStart(temp.getStart() + getJobSize(input[1]));
                if (temp.getLength() == 0)
                    temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp.getNext()));
                else
                    temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp));
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Input.");
        }
        return false;
    }

    //Uses worst fit space
    static boolean wf() {
        try {
            if (input.length < 2 || Integer.parseInt(input[1]) < 1) {
                System.out.println("Error: Invalid Input.");
                return false;
            }
            if (!segmentExists(input[1]) && jobExists(input[1])) {
                temp = head;
                closest = segIndex = -1;
                counter = 0;

                if (head.getPid() == 0 && head.getLength() >= getJobSize(input[1])) {
                    closest = Math.abs(head.getLength() - getJobSize(input[1]));
                    segIndex = 0;
                }

                while (temp.getNext() != null) {
                    temp2 = temp;
                    temp = temp.getNext();
                    if (temp.getPid() == 0 && temp.getLength() >= getJobSize(input[1]) && Math.abs(temp.getLength() - getJobSize(input[1])) > closest) {
                        closest = Math.abs(temp.getLength() - getJobSize(input[1]));
                        segIndex = counter + 1;
                    }
                    counter++;
                }
                if (segIndex == -1) {
                    System.out.println("Error: Not enough space.");
                    return false;
                } else if (segIndex == 0) {
                    if (getJobSize(input[1]) == 100)
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), null);
                    else {
                        head.setLength(head.getLength() - getJobSize(input[1]));
                        head.setStart(getJobSize(input[1]));
                        head = new Segment(Integer.parseInt(input[1]), 0, getJobSize(input[1]), head);
                        if (head.getNext().getLength() == 0)
                            head.setNext(head.getNext().getNext());
                    }
                    temp = head;
                    return true;
                }
                temp = head;
                for (int i = 0; i < segIndex; i++) {
                    temp2 = temp;
                    temp = temp.getNext();
                }
                temp.setLength(temp.getLength() - getJobSize(input[1]));
                temp.setStart(temp.getStart() + getJobSize(input[1]));
                if (temp.getLength() == 0)
                    temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp.getNext()));
                else
                    temp2.setNext(new Segment(Integer.parseInt(input[1]), temp2.getStart() + temp2.getLength(), getJobSize(input[1]), temp));
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Input.");
        }
        return false;
    }

    static void de() {
        if (input.length < 2 || Integer.parseInt(input[1]) < 1) {
            System.out.println("Error: Invalid Input.");
            return;
        }
        if (head.getPid() == Integer.parseInt(input[1]))
            if (head.getNext() != null && head.getNext().getPid() == 0) {
                head = head.getNext();
                head.setLength(head.getStart() + head.getLength());
                head.setStart(0);
            } else{
                head.setPid(0);
                temp = head;
            }
        else {
            temp = head;
            while (temp.getNext() != null) {
                temp2 = temp;
                temp = temp.getNext();
                if (temp.getPid() == Integer.parseInt(input[1])) {
                    temp.setPid(0);
                    if (temp.getNext() != null && temp.getNext().getPid() == 0) {
                        temp.setLength(temp.getLength() + temp.getNext().getLength());
                        temp.setNext(temp.getNext().getNext());
                    }
                    if (temp2.getPid() == 0) {
                        temp2.setLength(temp2.getLength() + temp.getLength());
                        temp2.setNext(temp.getNext());
                    }
                    temp = temp2;
                    return;
                }
            }
            System.out.println("Error: Segment Not Found.");
        }
    }

    static boolean segmentExists(String job) {
        temp3 = head;
        do {
            if (Integer.parseInt(job) == temp3.getPid()) {
                System.out.println("Error: Segment Already Exists.");
                return true;
            }
            temp3 = temp3.getNext();
        } while (temp3 != null);
        return false;
    }

    static int getJobSize(String job) {
        for (int i = 0; i < jobs.size(); i++)
            if (jobs.get(i).getPid() == Integer.parseInt(job))
                return jobs.get(i).getSize();
        System.out.println("Error: Job Does Not Exist.");
        return -1;
    }

    static boolean jobExists(String job) {
        for (int i = 0; i < jobs.size(); i++)
            if (jobs.get(i).getPid() == Integer.parseInt(job))
                return true;
        System.out.println("Error: Job Does Not Exist.");
        return false;
    }
}
