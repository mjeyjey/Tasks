package assignment;
import java.util.Scanner;

// Integer input from the user and uses a for loop to generate following pattern

public class LoopforPattern {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a Number: ");
        int num = scan.nextInt();

        for (int i = 1; i <= num; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    
}
