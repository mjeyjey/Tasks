package assignment;
import java.util.Scanner;

// sum and average of input array numbers.

public class SumAveArray {
    public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);

        System.out.print("How many numbers do you want to enter? ");
        int size = scan.nextInt();

        int[] numbers = new int[size];

        int sum = 0;

        for (int i = 0; i < size; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = scan.nextInt();
            sum += numbers[i]; 
        }

        double average = (double) sum / size;

        System.out.println("Sum = " + sum);
        System.out.println("Average = " + average);

    }
}
