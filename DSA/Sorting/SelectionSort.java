// LeetCode Problem: https://leetcode.com/problems/sort-an-array/description/

class SelectionSort {
    public static void main(String[] args) {
        int[] nums = {5,3,1,2,8};
        selectionSort(nums);
        // return nums;
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private static void selectionSort(int[] nums) {
        // assume first element is small, find smallestElement element and swap with first element
        // increment first;

        int n = nums.length;

        for(int i=0; i<n-1; i++) {
            int smallestElement = Integer.MAX_VALUE;
            int smallestElementIndex = -1;
            for(int j = i; j<n; j++) {
                if(nums[j] < smallestElement) {
                    smallestElement = nums[j];
                    smallestElementIndex = j;
                }
            }
            nums[smallestElementIndex] = nums[i];
            nums[i] = smallestElement;
        }

        System.out.println("Sorted Array: ");
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}