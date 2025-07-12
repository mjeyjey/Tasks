import java.util.Arrays;

class TwoSum {
    
    public static String twoSum (int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return "target not found";
        } 

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return "[" + i + ", " + j + "]";
                }
            }
        }
        return "target not found";

    }
    public static void main(String[] args) {
        String result = twoSum(new int[]{3, 2, 4}, 6);
        System.out.println(result);
    }
}