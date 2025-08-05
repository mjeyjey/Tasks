package assignment;
import java.util.Scanner;

// Sum of all Integer Inputs.

public class SumofAll {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    
        System.out.print("Enter First Integer: ");
        int num1 = scan.nextInt();
        System.out.print("Enter Second Integer: ");
        int num2 = scan.nextInt();
        int res = num1 + num2;
        System.out.print("the Sum of Two Integers is = "+ res);
    }
}
