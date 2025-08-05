package assignment;
import java.util.Scanner;

// Count number of word in a input.

public class WordCounter {
    public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);

        System.out.print("Type a sentence: ");
        String sentence = scanner.nextLine();  

        sentence = sentence.trim();

        if (sentence.isEmpty()) {
            System.out.println("There are no words.");
        } else {
            String[] words = sentence.split(" ");

            System.out.println("You typed " + words.length + " words.");
        }

    }
}
