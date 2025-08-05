package assignment;
import java.util.Scanner;

// String Builder 10 characters.

public class StringBuilder10 {
    public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);

        System.out.print("Enter a string with at least 10 characters: ");
        String input = scan.nextLine();

        // Validate length
        if (input.length() < 10) {
            System.out.println("The string must be at least 10 characters long.");
            return;
        }

        StringBuilder sb = new StringBuilder(input);

        System.out.println("Length of the string: " + sb.length());
        System.out.println("First character: " + sb.charAt(0));
        System.out.println("Last character: " + sb.charAt(sb.length() - 1));
        System.out.println("Index of first occurrence of 'a': " + sb.indexOf("a"));
        System.out.println("Substring from index 3 to 6: " + sb.substring(3, 7));

        sb.append("123");
        System.out.println("Append '123': " + sb.toString());

        sb.insert(4, "xyz");
        System.out.println("Insert 'xyz' at index 4: " + sb.toString());

        sb.delete(2, 4);
        System.out.println("Delete characters from index 2 to 4: " + sb.toString());

        if (sb.length() > 8) {
            sb.deleteCharAt(8);
            System.out.println("Delete character at index 8: " + sb.toString());
        } else {
            System.out.println("String too short after changes.");
        }

        sb.reverse();
        System.out.println("Reversed string: " + sb.toString());

    }
}
