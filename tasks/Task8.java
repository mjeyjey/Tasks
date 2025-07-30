
public class Task8 {
    public static int comulativeNum(int... nums){
        int totalSum = 0;

        for (int num: nums){
            int sum = 0;
            for (int i = 1; i <= num; i++) {
                sum += i;
            }
            // System.out.print("Numbers: ");
            System.out.print("\n" +num +"= " );
            // System.out.print("Comulative: ");
            System.out.print( sum );
                totalSum += sum;
        }
        return totalSum;
    }
   
        public static void main(String []args){
            int totalSum = comulativeNum(4, 5, 10);
            System.out.println("\nTotal: " + totalSum);
        }
    }