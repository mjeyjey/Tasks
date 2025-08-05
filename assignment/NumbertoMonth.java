package assignment;
import java.util.Scanner;

// Number to Month.

public class NumbertoMonth {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        
        System.out.println("(1) January");
        System.out.println("(2) February");
        System.out.println("(3) March");
        System.out.println("(4) April");
        System.out.println("(5) May");
        System.out.println("(6) June");
        System.out.println("(7) July");
        System.out.println("(8) August");
        System.out.println("(9) September");
        System.out.println("(10) October");
        System.out.println("(11) November");
        System.out.println("(12) December");
        System.out.print("\tEnter a number of Month: ");
        int month = scan.nextInt();


        switch (month) {
            case 1:
                System.out.println("\t("+ month + ") January have 31 days");
                break;
            
            case 2:
                System.out.println("\t("+ month + ") February have 28 days");
                break;

            case 3:
                System.out.println("\t("+ month + ") March have 31 days");
                break;

            case 4:
                System.out.println("\t("+ month + ") April have 30 days");
                break;

            case 5:
                System.out.println("\t("+ month + ") May have 31 days");
                break;

            case 6:
                System.out.println("\t("+ month + ") June have 30 days");
                break;

            case 7:
                System.out.println("\t("+ month + ") July have 31 days");
                break;

            case 8:
                System.out.println("\t("+ month + ") August have 31 days");
                break;

            case 9:
                System.out.println("\t("+ month + ") September have 30 days");
                break;

            case 10:
                System.out.println("\t("+ month + ") October have 31 days");
                break;

            case 11:
                System.out.println("\t("+ month + ") November have 30 days");
                break;

            case 12:
                System.out.println("\t("+ month + ") December have 31 days");
                break;

            default:
                System.out.println("\t There's Only 12 months");
                break;
        }
    }
}
