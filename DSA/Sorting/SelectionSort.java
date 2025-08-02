// LeetCode Problem: https://leetcode.com/problems/sort-an-array/description/

class SelectionSort {
    public int[] sortArray(int[] nums) {
        selectionSort(nums);
        return nums;
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private void selectionSort(int[] nums) {
        // assume first element is small, find smallest element and swap with first element
        // increment first;
        int n = nums.length - 1;
        for(int i=0; i <= n-1; i++) {
            int smallestElement = nums[i];
            int smallestElementIndex = i;
            for(int j = i; j <= n; j++) {
                if(nums[j] < smallestElement) {
                    smallestElement = nums[j];
                    smallestElementIndex = j;
                }
            }
            nums[smallestElementIndex] = nums[i];
            nums[i] = smallestElement;
        }
    }
}