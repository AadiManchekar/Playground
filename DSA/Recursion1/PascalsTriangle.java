package Recursion1;

import java.util.Scanner;

// LeetCode: https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/1644/
// f(i,j)=f(i−1,j−1)+f(i−1,j)

public class PascalsTriangle {
    public static int calculatePascalsNumber(int i, int j) {
        if (j == 0 || j == i) {
            return 1;
        }
        return calculatePascalsNumber(i - 1, j - 1) + calculatePascalsNumber(i - 1, j);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the row number (i): ");
        int i = sc.nextInt();
        System.out.print("Enter the position in the row (j): ");
        int j = sc.nextInt();
        int result = calculatePascalsNumber(i, j);
        System.out.println("Pascals Triangle number at row " + i + " and position " + j + " is: " + result);
        sc.close();
    }
}
