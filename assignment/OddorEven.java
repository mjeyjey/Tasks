package assignment;
import java.util.Scanner;
// Odd or Even? positive or negetive? or zero?

public class OddorEven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a Number: ");
        int num = scan.nextInt();

        
           if (num == 0) {
            System.out.println("The number is zero.");
        } else {
            
            if (num > 0) {
                System.out.println("The number is positive.");
            } else {
                System.out.println("The number is negative.");
            }

            
            if (num % 2 == 0) {
                System.out.println("The number is even.");
            } else {
                System.out.println("The number is odd.");
            }
        }
    }
}
