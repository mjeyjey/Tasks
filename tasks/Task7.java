import java.util.*;

public class Task7 {
    int sum(int a, int b){
        return a+b;
    }
    int diff(int a, int b){
        return a - b;
    }
     int product(int a, int b){
        return a * b;
    }
     float quotient(int a, int b){
        return a / b;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Task7 rt = new Task7();
        System.out.println("Arithmetic operation");
        int a, b;
        System.out.print("Enter First Number: ");
        a = scan.nextInt();
        System.out.print("Enter Second Number: ");
        b = scan.nextInt();
        
        System.out.println("a + b = "+rt.sum(a,b));
        System.out.println("a - b = "+rt.diff(a,b));
        System.out.println("a * b = "+rt.product(a,b));
        System.out.println("a / b = "+rt.quotient(a,b));
  
    }
}
