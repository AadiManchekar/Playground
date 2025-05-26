import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Examples {
    
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);

        /* 
        Iterable class exposes
        1. iterator
        2. forEach
        */

        // Iterate using iterator
        Iterator<Integer> it = array.iterator();

        while(it.hasNext()) {
            Integer val = it.next();
            System.out.println("it: " + val);
        }

        // Iterate using forEach
        array.forEach(i -> System.out.println("forEach: " + i));

        // even shorter using a method reference:
        array.forEach(System.out::println);
    }
}
