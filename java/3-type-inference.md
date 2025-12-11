# Type Inference in Java

Type inference is a feature that allows the Java compiler to automatically determine the type of a variable or expression based on the context, reducing the need for explicit type declarations.

## Static vs Dynamic Typing
- **Statically Typed Languages:** The type of every variable and expression is known at compile time. Errors related to types are caught before the program runs. Examples: Java, C, C++.
- **Dynamically Typed Languages:** The type of variables is determined at runtime. Type errors may only appear when the code is executed. Examples: Python, JavaScript.

### Why is Java Statically Typed?
Java is statically typed because:
- The compiler checks and enforces types during compilation.
- Every variable, method parameter, and return type must have a declared type (even when using type inference, the compiler determines the type at compile time).
- This leads to safer code, fewer runtime errors, and better performance optimizations.

Type inference in Java does not make it dynamically typed; it simply reduces the need for explicit type declarations while keeping type safety.

## Where is Type Inference Used?
- **Generics:** The compiler can infer generic types in method calls and object creation.
- **Local Variable Type Inference (`var`):** Introduced in Java 10, allows the compiler to infer the type of local variables.

## Examples

### 1. Generics Type Inference
```java
// Explicit type
List<String> list1 = new ArrayList<String>();

// Type inference (Java 7+)
List<String> list2 = new ArrayList<>();

// Method type inference
doSomething(new ArrayList<>()); // Compiler infers the type
```

### 2. Local Variable Type Inference (Java 10+)
```java
var message = "Hello, World!"; // Inferred as String
var count = 42;                // Inferred as int
var list = new ArrayList<String>(); // Inferred as ArrayList<String>
```

### 3. Lambda Expressions
Type inference is also used in lambda expressions:
```java
List<Integer> nums = Arrays.asList(1, 2, 3);
nums.forEach(n -> System.out.println(n)); // 'n' inferred as Integer
```

### 4. Function<T, R> Type Inference
The compiler can infer the types for generic functional interfaces like `Function<T, R>`:
```java
Function<String, Integer> lengthFunc = s -> s.length(); // 's' inferred as String, return as Integer

// Using var (Java 11+)
var lengthFunc2 = (Function<String, Integer>) (s -> s.length());
System.out.println(lengthFunc2.apply("Type Inference")); // Output: 13
```

## Notes
- Type inference makes code cleaner and easier to read, but the actual type is still determined at compile time. As a result, it doesn't have any performance impact.
- `var` can only be used for local variables, not for fields, method parameters, or return types.
- Type inference does not mean Java is dynamically typed; it remains a statically typed language.
