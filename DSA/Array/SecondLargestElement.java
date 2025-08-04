import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondLargestElement {
    public static void main(String[] args) {
        // Array has some values repeated, so be careful!
        // Second largest would be 5 and not 7
        List<Integer> arr = new ArrayList<>(List.of(5,2,1,4,5,7,7));
        // List<Integer> arr = new ArrayList<>(List.of(7,7,7,7,7,1));

        // Find the second largest element in array
        approach1(arr);
        approach2(arr);
        approach3(arr);
    }

    // Time complexity: O(NlogN + N)
    public static void approach1(List<Integer> arr) {
        // Sort the array and iterate from back to find out
        Collections.sort(arr); //NlogN
        int n = arr.size();
        // O(N) to traverse (worst case)
        for(int i = n-1; i>=0; i--) {
            if(arr.get(i) < arr.get(n-1)) {
                System.out.println(arr.get(i));
                break;
            }
        }
    }

    // Time complexity: O(N + N) = O(2N)
    public static void approach2(List<Integer> arr) {
        // Find the largest Element
        int largestElement = arr.get(0);
        for(int i : arr) {
            if(i > largestElement) {
                largestElement = i;
            }
        }

        // Find the second largest
        int secondLargestElement = arr.get(0);
        for(int i : arr) {
            if(i > secondLargestElement && i < largestElement) {
                secondLargestElement = i;
            }
        }
        System.out.println(secondLargestElement);
    }

    // Time complexity: O(N + N) = O(2N)
    public static void approach3(List<Integer> arr) {

        int largestElement = arr.get(0);
        int secondLargestElement = -1;
        for(int i : arr) {
            if(i > largestElement) {
                secondLargestElement = largestElement;
                largestElement = i;
            }
        }
        System.out.println(secondLargestElement);
    }
}
