package Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Basic {
    public static void main(String[] args) {

        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(3);
        array.add(4);

        // size
        System.out.println("Size: " + array.size());

        // isEmpty
        System.out.println("isEmpty: " + array.isEmpty());

        // contains, takes object as input
        // Every class has {@code Object} as a superclass
        // So even Integer is an object
        System.out.println("contains: " + array.contains(5));

        array.add(5);

        System.out.println("contains: " + array.contains(5));

        // remove using index
        array.remove(3);
        System.out.println("removed using index: " + array.contains(5));

        // remove using object. removes first occurence of the value
        // we remove first 3 from the array
        array.remove(Integer.valueOf(3));
        System.out.println("removed using object: " + array.contains(3));

        // Creating new stack
        Stack<Integer> stackValues = new Stack<>();
        stackValues.add(6);
        stackValues.add(7);
        stackValues.add(8);

        // addAll(Collection) Accepts any collection as arguement
        array.addAll(stackValues);

        System.out.println("array contains stackValues: " + array.containsAll(stackValues));

        // remove an Integer 7
        array.remove(Integer.valueOf(7));
        System.out.println("array contains stackValues: " + array.containsAll(stackValues));

        // clear all elements
        array.clear();

        System.out.println("isEmpty: " + array.isEmpty());
    }
}
