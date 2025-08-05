package assignment;

// Score to Grade F-A

import java.util.Scanner;

public class ScoretoGrade {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Your Grade: ");
        int grade = scan.nextInt();

        if (grade >= 90) {
            System.out.println("\tGreat You got 'A'");
        } 
        else if(grade >= 80 && grade <= 89){
            System.out.println("Good You got 'B'");
        }
         else if(grade >= 70 && grade <= 79){
            System.out.println("You got 'C'");
        }
         else if(grade >= 60 && grade <= 69){
            System.out.println("You got 'F'");
        }
    }
}