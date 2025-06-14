### Anatomy
1. Java is high-level programming language
2. Java is not a completely object-oriented programming language because it has the support of primitive data types like int, float, char, boolean, double, etc.
3. Java is both a **statically-typed and strongly-typed language**:
    - **Statically-Typed**: This means that the type of a variable is checked at compile-time, before the program runs. You must explicitly declare the data type of a variable (e.g., int x;, String name;) and it cannot change during runtime. This helps catch type-related errors early in the development cycle.
    - **Strongly-Typed**: This implies strict enforcement of data types, meaning that Java does not allow implicit type conversions between incompatible types (e.g., trying to assign a String to an int directly). If a conversion is needed between different types, it usually requires explicit casting, which makes the code more robust and less prone to unexpected runtime errors.
3. Java adheres to **“write once, run anywhere” philosophy**. You write code in `.java` file and with help of compiler (javac) you compile it to `.class` file (which is basically bytecode). 
4. Now you can feed this .class to any JVM. (Note that **jvm is platform dependent**, whereas when the `.class` **(Bytecode) is platform independent**, Hence the philosophy “write once, run anywhere”).

![Java Compilation Flow](image.png)

*Source: [@alxkm/how-compilation-works-in-java](https://medium.com/@alxkm/how-compilation-works-in-java-0ac4d1e95b99)*


![Running java flow](image-1.png)

*Source: [https://stackoverflow.com/a/36394113](https://stackoverflow.com/a/36394113)*


3. Code written in Java is:
    - First compiled to bytecode by a compiler (javac) as shown in the left section of the image above; 
    - Then, as shown in the right section of the above image, another program called java starts the Java runtime environment and it may compile and/or interpret the bytecode by using the Java Interpreter/JIT Compiler.

4. JDK, JRE, JVM:

![JDK, JRE, JVM](image-2.png)

5. Java compilation types 
    - Ahead-of-Time (AOT) Compilation
        - Converts Java bytecode to native machine code before runtime.
        - Faster startup, smaller memory footprint.
        - Request hits the app → Code already compiled → Immediate execution.
    - Just-In-Time (JIT) Compilation
        - Compiles bytecode to native code at runtime, during execution.
        - Slower startup due to compilation overhead.
        - Request hits app → JVM interprets bytecode → Hot methods (frequently used) compiled on-the-fly → Faster later execution.
    - Synchronous Compilation
        - JIT compiles code inline during execution.
        - Request thread blocks during compilation.
        - Request hits hot code → Thread pauses → Method compiled → Then executed.
    - Asynchronous Compilation
        - JIT compiles in a background thread.
        - No pause in main request flow.
        - Request hits hot code → Executes interpreted version → JIT compiles in background → Future requests use compiled code.

Good read: https://medium.com/@alxkm/how-compilation-works-in-java-0ac4d1e95b99

---

### Rules:
- When a Java file is compiled, the compiler creates a separate .class file for each class defined within it.
- The name of the .java file must match the name of the public class
- A source file can have only one public class. But why? [During execution, the Java Virtual Machine (JVM) starts by looking for the main method within a public class. If multiple public classes were allowed, it would create ambiguity as to which class should be the entry point of the program. By restricting it to one public class, the JVM can easily identify where to begin execution.]

---

### Java Editions Comparison

Java has three main editions that cater to different types of application development:
1. **JSE (Java Standard Edition)**: Core Java programming for desktop and general-purpose applications. 
    - Includes:
        - Core libraries (java.lang, java.util, etc.)
        - JVM, JDK, JRE
        - APIs for I/O, networking, collections, multithreading
        - GUI libraries: Swing, AWT 
2. **JEE (Java Enterprise Edition) (Now Jakarta EE)**: Java for enterprise applications – large-scale, distributed, web-based. Built on top of JSE
    - Includes:
        - JPA (Java Persistence API)
        - Servlets, JSP
        - Transactions
3. **JME (Java Micro Edition)**: Java for small devices with limited resources (IoT, embedded systems, old mobile phones).
    - Includes:
        - Subset of JSE + device-specific APIs

---

### Primitive data types

```Java
/*
        Char is 2 bytes
        Char is representation of ASCII Values
        Range 0 to 65536
    */
    char ch = 'A';
    System.out.println(ch); // Outputs: A

    // we can also assign ASCII value to it
    char A = 65;
    System.out.println(A);  // Outputs: A
    char a = 97;
    System.out.println(a);  // Outputs: a

    // ------------------------------------------------------------

    /*
        shortis 2 bytes
        shortis signed (which means it has both +ve and -ve value)
        Range -32,768 to 32,767
        default value 0
    */
    short myShort = 15000; 
    System.out.println(myShort); // Outputs: 15000

    // Range of short (max and min values)
    short maxShort = Short.MAX_VALUE;
    short minShort = Short.MIN_VALUE;
    System.out.println("Maximum value for short: " + maxShort); // Outputs: 32767
    System.out.println("Minimum value for short: " + minShort); // Outputs: -32768

    // ------------------------------------------------------------

    /*
        int is 4 bytes
        int is signed (which means it has both +ve and -ve value)
        Range -2^31 to 2^31 - 1 
        default value 0
    */
    int myInt = 15;
    System.out.println(myInt); // Outputs: 15

    // Range of int (max and min values)
    int maxInt = Integer.MAX_VALUE;
    int minInt = Integer.MIN_VALUE;
    System.out.println("Maximum value for int: " + maxInt); // Outputs: 2147483647
    System.out.println("Minimum value for int: " + minInt); // Outputs: -2147483648

    // ------------------------------------------------------------

    /*
        long is 4 bytes
        long is signed (which means it has both +ve and -ve value)
        Range -2^63 to 2^63 - 1 
        default value 0
    */

    // Notice the 'L' suffix. While optional for small numbers,
    // it's crucial for values exceeding int's range (2,147,483,647)
    // to tell the compiler it's a long literal, not an int.
    long populationOfWorld1 = 8_000_000_000L; // 8 billion
    long populationOfWorld2 = 8000000000L; // 8 billion
    System.out.println(populationOfWorld1); // Outputs: 8000000000
    System.out.println(populationOfWorld2); // Outputs: 8000000000

    long maxLong = Long.MAX_VALUE;
    long minLong = Long.MIN_VALUE;

    System.out.println("Maximum value for long: " + maxLong); // Outputs: 9223372036854775807
    System.out.println("Minimum value for long: " + minLong); // Outputs: -9223372036854775808

    // ------------------------------------------------------------

    /*
        boolean is 1 bit
        default value false
    */

    boolean trueValue = true;
    System.out.println(trueValue);    // Outputs: true
```

--- 

### Java String Constant Pool
- The String Constant Pool (also known as the String Pool or String Literal Pool) is a special memory area within the Java Heap where string literals are stored. Its primary purpose is to optimize memory usage by storing only one copy of each unique string literal.
- The String Constant Pool is located in the Heap memory
- This means strings in the pool are now subject to garbage collection like other objects in the Heap

```Java
    // 1. String Literals:
    // When you create strings using literal syntax ("..."),
    // Java first checks the String Constant Pool.
    // If the string already exists, it returns a reference to that existing object.
    // If not, it creates a new String object in the pool and returns its reference.
    String s1 = "Hello";
    String s2 = "Hello";
    String s3 = "World";

    System.out.println("s1 = " + s1);
    System.out.println("s2 = " + s2);
    System.out.println("s3 = " + s3);

    // Comparing references (using ==)
    // s1 and s2 refer to the *same* object in the String Pool because "Hello" is unique.
    System.out.println("s1 == s2: " + (s1 == s2)); // true (same object reference)
    // s1 and s3 refer to different objects
    System.out.println("s1 == s3: " + (s1 == s3)); // false (different object references)

    System.out.println("\n--- Demonstrating 'new String()' ---");

    // 2. 'new String()':
    // When you create a string using the 'new' keyword,
    // it *always* creates a new String object in the Heap,
    // even if an identical string literal exists in the pool.
    String s4 = new String("Hello");
    String s5 = new String("Hello");

    System.out.println("s4 = " + s4);
    System.out.println("s5 = " + s5);

    // Comparing references:
    // s4 and s5 are different objects in the Heap.
    System.out.println("s4 == s5: " + (s4 == s5)); // false (different objects)

    // Comparing s4 (new Heap object) with s1 (pool object)
    // They have the same content but are different objects.
    System.out.println("s1 == s4: " + (s1 == s4)); // false (different objects)

    // Comparing content (using equals() method)
    // This checks if the characters are the same, regardless of object identity.
    System.out.println("s1.equals(s4): " + s1.equals(s4)); // true (content is the same)
```

### Array
```Java
import java.util.Arrays; // Import for easy printing of array contents

public class ArrayDemonstration {

    public static void main(String[] args) {

        System.out.println("--- One-Dimensional Integer Arrays ---");

        // 1. Declaring and initializing a 1D array using 'new int[size]'
        // This creates an array of 5 integers, initialized to their default value (0 for int).
        int[] numbers1 = new int[5];
        System.out.println("\nArray 'numbers1' initialized with new int[5]:");
        System.out.println("Default values: " + Arrays.toString(numbers1));

        // Assigning values to elements
        numbers1[0] = 10;
        numbers1[1] = 20;
        numbers1[2] = 30;
        numbers1[3] = 40;
        numbers1[4] = 50;
        System.out.println("After assigning values: " + Arrays.toString(numbers1));

        // Accessing elements
        System.out.println("Element at index 2 (numbers1[2]): " + numbers1[2]); // Output: 30

        // Getting array length
        System.out.println("Length of numbers1: " + numbers1.length); // Output: 5

        // Iterating through the array using a for-loop
        System.out.print("Elements of numbers1 using a for-loop: ");
        for (int i = 0; i < numbers1.length; i++) {
            System.out.print(numbers1[i] + " ");
        }
        System.out.println(); // New line

        // Iterating through the array using an enhanced for-loop (for-each)
        System.out.print("Elements of numbers1 using a for-each loop: ");
        for (int num : numbers1) {
            System.out.print(num + " ");
        }
        System.out.println(); // New line


        // 2. Declaring and initializing a 1D array using an initializer list '{...}'
        // This automatically sets the size and initializes elements.
        int[] numbers2 = {100, 200, 300, 400, 500, 600};
        System.out.println("\nArray 'numbers2' initialized with {100, 200, ...}:");
        System.out.println("Values: " + Arrays.toString(numbers2));
        System.out.println("Length of numbers2: " + numbers2.length); // Output: 6


        System.out.println("\n--- Two-Dimensional Integer Arrays (Matrices) ---");

        // 1. Declaring and initializing a 2D array using 'new int[rows][cols]'
        // This creates a 3x4 matrix (3 rows, 4 columns), all elements initialized to 0.
        int[][] matrix1 = new int[3][4]; // 3 rows, 4 columns
        System.out.println("\nMatrix 'matrix1' initialized with new int[3][4]:");

        // Assigning values to elements (row by row, column by column)
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[0][2] = 3;
        matrix1[0][3] = 4;
        matrix1[1][0] = 5;
        matrix1[1][1] = 6;
        matrix1[1][2] = 7;
        matrix1[1][3] = 8;
        matrix1[2][0] = 9;
        matrix1[2][1] = 10;
        matrix1[2][2] = 11;
        matrix1[2][3] = 12;

        // Printing the matrix (using nested loops)
        System.out.println("After assigning values:");
        for (int i = 0; i < matrix1.length; i++) { // Looping through rows
            for (int j = 0; j < matrix1[i].length; j++) { // Looping through columns in the current row
                System.out.print(matrix1[i][j] + "\t"); // \t for tab spacing
            }
            System.out.println(); // New line after each row
        }

        // Getting dimensions of the 2D array
        System.out.println("Number of rows in matrix1 (matrix1.length): " + matrix1.length); // Output: 3
        System.out.println("Number of columns in row 0 (matrix1[0].length): " + matrix1[0].length); // Output: 4


        // 2. Declaring and initializing a 2D array using an initializer list '{{...},{...}}'
        int[][] matrix2 = {
            {10, 20, 30},
            {40, 50, 60},
            {70, 80, 90}
        };
        System.out.println("\nMatrix 'matrix2' initialized with {{10, 20, ...}, ...}:");

        // Printing the matrix
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.print(matrix2[i][j] + "\t");
            }
            System.out.println();
        }

        // Getting dimensions
        System.out.println("Number of rows in matrix2: " + matrix2.length);     // Output: 3
        System.out.println("Number of columns in row 1: " + matrix2[1].length); // Output: 3

        // Demonstrating a "ragged" 2D array (rows with different column counts)
        int[][] raggedMatrix = {
            {1, 2},
            {3, 4, 5, 6},
            {7}
        };
        System.out.println("\nRagged Matrix Demonstration:");
        for (int i = 0; i < raggedMatrix.length; i++) {
            for (int j = 0; j < raggedMatrix[i].length; j++) {
                System.out.print(raggedMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("Length of raggedMatrix (rows): " + raggedMatrix.length); // Output: 3
        System.out.println("Length of row 0: " + raggedMatrix[0].length); // Output: 2
        System.out.println("Length of row 1: " + raggedMatrix[1].length); // Output: 4
        System.out.println("Length of row 2: " + raggedMatrix[2].length); // Output: 1

        // int[] a, b; declares a as an int array and b as an int
        // int a[], b; declares both a and b as int arrays.
    }
}
```