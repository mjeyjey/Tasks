package assignment;
import java.util.Scanner;

// String Methods.

public class StringMethods {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a String: ");
        String input = scan.nextLine();

        System.out.println("Length: " + input.length());

        System.out.println("Uppercase: " + input.toUpperCase());

        System.out.println("Lowercase: " + input.toLowerCase());

        if (!input.isEmpty()) {
            System.out.println("First character: " + input.charAt(0));
        } else {
            System.out.println("String is empty.");
        }

        if (!input.isEmpty()) {
            System.out.println("Last character: " + input.charAt(input.length() - 1));
        }

        if (input.length() >= 5) {
            System.out.println("Substring: " + input.substring(1, 5));
        } else {
            System.out.println("String is too short ");
        }
    }
}
