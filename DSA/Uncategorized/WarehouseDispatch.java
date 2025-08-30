package Uncategorized;

import java.util.*;

// Problem: https://leetcode.com/discuss/post/7111329/amazon-online-assessment-oa-june-2025-by-idox/
public class WarehouseDispatch {
    public int maxCredits(List<Integer> inventory, int dispatch1, int dispatch2, int skips) {
        int credits = 0;
        List<Integer> skipCosts = new ArrayList<>();

        for (int inv : inventory) {
            long cycle = (long) dispatch1 + dispatch2; 
            long fullCycles = inv / cycle;
            long rem = inv % cycle;

            if (rem == 0) {
                // Ends on co-worker naturally, need 1 skip to flip
                skipCosts.add(1);
            } else if (rem <= dispatch1) {
                // You finish naturally
                credits++;
            } else {
                // Co-worker finishes unless you use skips
                // Extra units after your turn: rem - dispatch1
                long extra = rem - dispatch1;
                // Each skip lets you insert another dispatch1
                long cost = (extra + dispatch1 - 1) / dispatch1; // ceil
                skipCosts.add((int) cost);
            }
        }

        // Use skips greedily starting with cheapest warehouses
        Collections.sort(skipCosts);

        for (int cost : skipCosts) {
            if (skips >= cost) {
                skips -= cost;
                credits++;
            } else {
                break;
            }
        }

        return credits;
    }

    // Example run
    public static void main(String[] args) {
        WarehouseDispatch solver = new WarehouseDispatch();
        List<Integer> inventory1 = Arrays.asList(10, 6, 12, 8, 15, 1);
        System.out.println(solver.maxCredits(inventory1, 2, 3, 3)); // Output: 5

        List<Integer> inventory2 = Arrays.asList(7, 7, 7);
        System.out.println(solver.maxCredits(inventory2, 5, 3, 2)); // Output: 2

        List<Integer> inventory3 = Arrays.asList(100, 100);
        System.out.println(solver.maxCredits(inventory3, 10, 90, 0)); // Output: 0
    }
}
