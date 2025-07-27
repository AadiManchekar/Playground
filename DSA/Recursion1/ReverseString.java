package Recursion1;

import java.util.ArrayList;
import java.util.List;

public class ReverseString {

    // Input: s = ["h","e","l","l","o"]
    // Output: ["o","l","l","e","h"]
    public static void main(String[] args) {
        List<Character> arr = new ArrayList<>();
        arr.add('h');
        arr.add('e');
        arr.add('l');
        arr.add('l');
        arr.add('o');
        System.out.println(arr);

        approach1(arr);
    }

    private static void approach1(List<Character> arr) {
        List<Character> arrClone = new ArrayList<>(arr);
        recursiveReverse(arrClone);
        System.out.println(arrClone);
    }

    private static void recursiveReverse(List<Character> arr) {
        if (arr.size() == 0) {
            return;
        }

        Character ch = arr.get(0);
        arr.remove(0);
        recursiveReverse(arr);
        arr.add(ch);
    }
}
