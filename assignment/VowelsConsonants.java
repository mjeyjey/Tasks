package assignment;
import java.util.Scanner;

// Check vowels and consonants.

public class VowelsConsonants {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 

        System.out.print("Enter a Character: ");
        char ch = scan.next().charAt(0);

        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            System.out.println(ch + " is a vowel.");
        } else if (ch != 'a' || ch != 'e' || ch != 'i' || ch != 'o' || ch != 'u') {
                System.out.println(ch + " is a consonant.");
        } else {
            System.out.println("Invalid Input");
        }
    }
}
