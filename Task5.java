import java.util.*;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
         System.out.print("Enter 1st number: ");
        int num1 = scan.nextInt();
         System.out.print("Enter 2nd number: ");
        int num2 = scan.nextInt();
         System.out.print("Enter 3rd number: ");
        int num3 = scan.nextInt();
      
        if ((num1 == num2)&&(num2 == num3)){
             System.out.println("All numbers are equal");
        } else {
            int largest;
            if (num1 >= num2&&num2 >= num3){
                largest = num1;
            }
            else if (num1 >= num2&&num2 >= num3){
                largest = num2;
            }else{
                largest = num3;
            } 
            System.out.println(largest);
        
        }
    }
}