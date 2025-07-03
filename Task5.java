import java.util.*;


public class Task5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List <Integer> num = new ArrayList<>();
        int nums;
        

         System.out.print("Enter 1st number: ");
         nums = scan.nextInt();
         num.add(nums);
         System.out.print("Enter 2nd number: ");
         nums = scan.nextInt();
         num.add(nums);
         System.out.print("Enter 3rd number: ");
         nums = scan.nextInt();
         num.add(nums);
         int largest = num.get(2);
         Collections.sort(num);
         

        if (num.get(0).equals(num.get(1)) || num.get(1).equals(num.get(2)) || num.get(0).equals(num.get(2))) {
            System.out.println("All numbers are equal");
        } else {
            System.out.println(largest);
        }
            // if ((num1 == num2)&&(num2 == num3)){
        //      
        // } else {
        //     
        //     if (num1 >= num2&&num2 >= num3){
        //         largest = num1;
        //     }
        //     else if (num1 >= num2&&num2 >= num3){
        //         largest = num2;
        //     }else{
        //         largest = num3;
        //     } 
        //     
        
        // }
    }
}