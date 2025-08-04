public class CheckIfArrayIsSortedAndRotated {
    // Leetcode: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/
    
    public boolean check(int[] nums) {
        int n = nums.length;

        // Normally, in a sorted array, every element should be <= the next.
        // But in a rotated sorted array, this condition may be broken once
        // At the rotation point.

        // So if there is <= 1 rotation point then fine

        // Valid cases
        // [3, 4, 5, 1, 2] -> 1 rotation point only
        // [1,2,3] -> 0 rotation point

        // Edge case
        // [10,1,1,10]

        // Invalid case
        // [2,1,3,4] -> 2 break points

        int rotationPoints = 0;

        for(int i = 0; i < n; i++) {
            if(nums[i] > nums[(i+1)%n]) {
                // (i + 1)%n because we have to compare first and last element too
                rotationPoints++;
            }
        }

        return rotationPoints > 1 ? false : true;
    }
}
