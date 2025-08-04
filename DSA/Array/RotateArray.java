import java.util.ArrayList;
import java.util.List;

public class RotateArray {

    // Approach1
    // Time Complexity: O(n*k) where n is the length of the array and k is the number of rotations
    public void approach1(int[] nums, int k) {
        int n = nums.length;
        k = k%n;

        if (k==0) {
            return;
        }

        for(int i =1; i<=k; i++) {
            rotateByOnePlace(nums);
        }

        return;
    }

    private void rotateByOnePlace(int[] nums) {
        int n = nums.length;

        int temp = nums[n-1];
        for(int i=n-1-1; i>=0; i--) {
            nums[i+1] = nums[i];
        }
        nums[0] = temp;
    }

    // Approach2
    // Time Complexity: O(n) where n is the length of the array
    // Space Complexity: O(n) for the temporary array
    public void approach2(int[] nums, int k) {
        int n = nums.length;
        k = k%n;

        if (k==0) {
            return;
        }
        List<Integer> tempArr = new ArrayList<>();

        for(int i=0; i<n-k; i++) {
            // copy elements in temp arr
            tempArr.add(nums[i]);
        }

        // Left shift all elements by n-k position
        for(int i=n-k; i<n; i++) {
            nums[i-n+k] = nums[i];
        }

        // update ending k elements from tempArr
        for(int i = k; i<n; i++) {
            nums[i] = tempArr.get(i-k);
        }
    }

}
