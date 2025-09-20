// LeetCode Problem: https://leetcode.com/problems/sort-an-array/description/

public class BubbleSort {
    // Push the largest element at end by swapping adjacent pairs

    public static void main(String[] args) {
        int[] nums = { 5, 2, 6, 1, 2 };
        bubbleSort(nums);
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private static void bubbleSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted Array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
