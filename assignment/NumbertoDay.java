package assignment;
import java.util.Scanner;

//  Numner to day of the week   

public class NumbertoDay {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a Number: ");
        int day = scan.nextInt();
        
        switch (day) {
            case 1:
                System.out.println( day + " | Monday");
                break;

            case 2:
                System.out.println( day + " | Tuesday");
                break;

            case 3:
                System.out.println( day + " | Wednesday");
                break;

            case 4:
                System.out.println( day + " | Thursday");
                break;

            case 5:
                System.out.println( day + " | Friday");
                break;

            case 6:
                System.out.println( day + " | Saturday");
                break;

            case 7:
                System.out.println( day + " | Sunday");
                break;
        
            default:
            System.out.println("Invalid input");
                break;
        }
    }
}
