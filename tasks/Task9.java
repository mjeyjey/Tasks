import java.util.Scanner;
import static java.lang.Math.*;

public class Task9 {
    public static int add(int a, int b) {
       return Math.addExact(a, b);
    }
    public static int subtract(int a, int b) {
       return Math.subtractExact(a, b);
    }
    public static int multiply(int a, int b) {
       return Math.multiplyExact(a, b);
    }
    public static float divide(int a, int b) {
       return Math.floorDiv(a, b);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Task9 op = new Task9();
        int a, b;
        System.out.print("enter number: ");
        a = scan.nextInt();
        // System.out.println("choose operation: +, -, *, /");
        // add = scan.nextInt();
        System.out.print("enter number: ");
        b = scan.nextInt();

        // int sum = add(a,b); 
        System.out.println(a + " - " + b + " = " + subtract(a,b));
        System.out.println(a + " * " + b + " = " + multiply(a,b));
        System.out.println(a + " / " + b + " = " + divide(a,b));
    }
}
