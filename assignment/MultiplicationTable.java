package assignment;

// Multiplication Table 10x10 

public class MultiplicationTable {
    public static void main(String[] args) {
        int rows = 10;
        int columns = 10;

        System.out.println("\t\tMultiplication Table");

           for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                System.out.print((i * j) + "\t"); // tab-separated
            }
            System.out.println();
        }
    }
}
