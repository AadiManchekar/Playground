package Base;

// This class is a simple generic Pair class that can hold two values of any type.
// It is used in the PascalsTriangle2 class for memoization.

// You can use javafx.util.Pair but for Java SE 21 the Pair class is not available by default.
// Hence, we define our own Pair class here.
// This class is immutable, meaning once created, the values cannot be changed.
// It is a simple data structure that holds two values of types A and B.
public class Pair<A, B> {
    public final A first;
    public final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}
