package Maths;

import java.util.Scanner;

public class ArmStrongNumber {
    public static void main(String[] args) {

        // Armstrong numbers are numbers that are equal to the sum of their own digits each raised to the power of the number of digits.
        // For example, 153 is an Armstrong number because 1^3 + 5^3 + 3^3 = 153.
        // Another example is 9474, since 9^4 + 4^4 + 7^4 + 4^4 = 9474.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean isArmstrong = isArmstrongNumber(n);
        System.out.println(n + " is an Armstrong number: " + isArmstrong);
        sc.close(); // Close the scanner
    }

    // Function to check if a number is an Armstrong number
    // O(log₁₀(N)) time, O(1) space
    private static boolean isArmstrongNumber(int n) {
        int originalNumber = n;
        int sum = 0;
        int numberOfDigits = countDigits(n);
        while (n != 0) {
            int lastDigit = n % 10;
            sum += Math.pow(lastDigit, numberOfDigits);
            n /= 10;
        }
        return sum == originalNumber;
    }

    private static int countDigits(int n) {
        return (int) Math.floor(Math.log10(Math.abs(n))) + 1;
    }
}
