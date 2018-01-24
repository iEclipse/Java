
import java.util.ArrayList;
import java.util.Scanner;

public class SetNonADT {

    static ArrayList<String> set1 = new ArrayList();
    static ArrayList<String> set2 = new ArrayList();

    static void union() {
        ArrayList<String> temp = (ArrayList<String>) set1.clone();
        for (int i = 0; i < set2.size(); i++) {
            if (!set1.contains(set2.get(i)))
            temp.add(set2.get(i));
        }
        System.out.println("[Set1 or Set2] - " + toString(temp));
    }

    static void intersection() {
        ArrayList<String> temp = new ArrayList();
        for (int i = 0; i < set2.size(); i++) {
            if (set1.contains(set2.get(i))) {
                temp.add(set2.get(i));
            }
        }
        System.out.println("[Set1 and Set2] - " + toString(temp));
    }

    static void difference(int order) {
        if (order == 0) {
            ArrayList<String> temp = (ArrayList) set1.clone();
            for (int i = 0; i < set2.size(); i++) {
                temp.remove(set2.get(i));
            }
            System.out.println("[Set1 and !Set2] - " + toString(temp));
        } else {
            ArrayList<String> temp = (ArrayList) set2.clone();
            for (int i = 0; i < set2.size(); i++) {
                temp.remove(set2.get(i));
            }
            System.out.println("[Set2 and !Set1] - " + toString(temp));
        }
    }

    static public String toString(ArrayList set) {
        String temp = "";
        if (set.isEmpty()) {
            return "Empty";
        }
        for (int i = 0; i < set.size(); i++) {
            temp += set.get(i) + " ";
        }
        return temp.trim();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] input;

        System.out.println("Set Test\n=================");
        System.out.println("Type '?' for a list of commands.");

        while (true) {
            System.out.print("\nCommand: ");
            input = s.nextLine().trim().replaceAll("\\s+|,", ",").split(",");
            input[0] = input[0].toLowerCase();

            switch (input[0]) {
                case "set1":
                    set1 = new ArrayList();
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].length() > 0 && !set1.contains(input[i])) {
                            set1.add(input[i]);
                        }
                    }
                    System.out.println("Modified Set1 Values.");
                    break;

                case "set2":
                    set2 = new ArrayList();
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].length() > 0 && !set2.contains(input[i])) {
                            set2.add(input[i]);
                        }
                    }
                    System.out.println("Modified Set2 Values.");
                    break;

                case "union":
                    union();
                    break;

                case "intersection":
                    intersection();
                    break;

                case "difference":
                    if (input.length > 2 && input[1].equals("set1") && input[2].equals("set2")) {
                        difference(0);
                    } else if (input.length > 2 && input[1].equals("set2") && input[2].equals("set1")) {
                        difference(1);
                    } else {
                        System.out.println("Invalid Input.");
                    }
                    break;

                case "view":
                    System.out.println("Set1: " + toString(set1));
                    System.out.println("Set2: " + toString(set2));
                    break;

                case "?":
                    System.out.println("\nHelp\n--------------");
                    System.out.println("1. Assign Values to Set1(Separated by Space or Comma): [ set1 value1, value2, value3, ..., valueN ].");
                    System.out.println("2. Assign Values to Set1(Separated by Space or Comma): [ set2 value1, value2, value3, ..., valueN ].");
                    System.out.println("3. View the Contents of Both Sets: [ view ].");
                    System.out.println("4. Find Union of Both Sets: [ union ].");
                    System.out.println("5. Find Intersection of Both Sets: [ intersection ].");
                    System.out.println("6. Find Difference Between Sets: [ difference set1 set2 ] or [ difference set2 set1 ].");
                    System.out.println("7. Terminate the Program: [ exit ].");
                    break;

                case "exit":
                    System.out.println("Program Terminated.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Command.");
            }
        }
    }
}
