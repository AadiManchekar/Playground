public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = { 5, 2, 9, 1, 5, 6 };
        insertionSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    // Check if the currEle is at correct position
    // If yes, continue
    // If no, swap until it gets to correct position
    public static void insertionSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            // check if the currEle is at correct position
            if (nums[i] > nums[i - 1]) {
                // yes, correct position
                continue;
            }
            // not correct position
            // swap until it gets to correct position
            int j = i;
            while (j > 0 && nums[j - 1] > nums[j]) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j--;
            }
        }
    }
}
