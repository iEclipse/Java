
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SetADT<String> adt1 = new Set();
        SetADT<String> adt2 = new Set();
        String[] input;

        System.out.println("Set Test\n=================");
        System.out.println("Type '?' for a list of commands.");
        
        while (true) {
            System.out.print("\nCommand: ");
            input = s.nextLine().trim().replaceAll("\\s+|,", ",").split(",");
            input[0] = input[0].toLowerCase();
            
            switch (input[0]) {                
                case "set1":
                    adt1 = new Set();
                    for (int i = 1; i < input.length; i++)
                        if (input[i].length() > 0)
                            adt1.add(input[i]);
                    System.out.println("Modified Set1 Values.");
                    break;
                    
                case "set2":
                    adt2 = new Set();
                    for (int i = 1; i < input.length; i++)
                        if (input[i].length() > 0)
                            adt2.add(input[i]);
                    System.out.println("Modified Set2 Values.");
                    break;
                    
                case "union":
                    System.out.println("[Set1 or Set2] - " + adt1.union(adt2).toString());
                    break;
                    
                case "intersection":
                    System.out.println("[Set1 and Set2] - " + adt1.intersection(adt2).toString());
                    break;
                    
                case "difference":
                    if (input.length > 2 && input[1].equals("set1") && input[2].equals("set2"))
                        System.out.println("[Set1 and !Set2] - " + adt1.difference(adt2).toString());
                    else if (input.length > 2 && input[1].equals("set2") && input[2].equals("set1"))
                        System.out.println("[Set2 and !Set1] - " + adt2.difference(adt1).toString());
                    else
                        System.out.println("Invalid Input.");
                    break;
                    
                case "view":
                    System.out.println("Set1: " + adt1.toString());
                    System.out.println("Set2: " + adt2.toString());
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
