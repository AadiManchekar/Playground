import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class LargestElement {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(List.of(5,2,1,4,5,7,7));

        // Find the largest element in array
        approach1(arr);
        approach2(arr);
    }

    private static void approach1(List<Integer> arr) {
        // we can sort and return the last element
        Collections.sort(arr);

        int n = arr.size();

        System.out.println(arr.get(n-1));
    }

    private static void approach2(List<Integer> arr) {
        int largestElement = arr.get(0);
        for(int i : arr) {
            if(i > largestElement) {
                largestElement = i;
            }
        }

        System.out.println(largestElement);
    }
}
