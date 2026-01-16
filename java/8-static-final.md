# static, final & static final

## Table of Contents
1. Overview  
2. `static` with Classes  
3. `static` with Methods  
4. `static` with Variables  
5. `final` with Classes  
6. `final` with Methods  
7. `final` with Variables  
8. `static final` Combination  
9. Common Rules & Pitfalls  
10. Final Recap  

---

## 1. Overview
`static`, `final`, and `static final` control **lifecycle, mutability, and scope** of classes, methods, and variables.

- `static` → associated to **class**, not objects (shared across all instances)
- `final` → prevents **modification/overriding/inheritance**
- `static final` → class-level **constant**

---

## 2. `static` with Classes

### Meaning
- Only allowed for **inner (nested) classes**, not top-level classes
- Inner class does NOT need outer class instance to be created
- Single copy at class level

### Example
```java
class Outer {
    static class StaticNested {
        void display() {
            System.out.println("Static nested class");
        }
    }
}
```

### Object Creation
```java
// No outer instance needed
Outer.StaticNested obj = new Outer.StaticNested();
obj.display();
```

### Key Points
- Can access only **static members** of outer class
- Behaves like a regular class but scoped within outer class
- Useful for logical grouping without tight coupling

---

## 3. `static` with Methods

### Meaning
- Method belongs to **class**, not object instances
- Can be called without creating an object
- Single copy shared by all instances

### Example
```java
class Calculator {
    static int add(int a, int b) {
        return a + b;
    }
}
```

### Usage
```java
// Called directly on class
int result = Calculator.add(5, 10);

// NOT on objects (though technically allowed)
Calculator calc = new Calculator();
calc.add(5, 10);  // Still calls class method, not instance method
```

### Key Points
- Cannot access non-static members directly (need `this` or object reference)
- Cannot use `this` or `super` keywords
- Are **hidden**, not overridden in subclasses
- Ideal for utility functions (like `Math.sqrt()`, `Arrays.sort()`)

---

## 4. `static` with Variables

### Meaning
- Variable belongs to **class**, not objects
- Single copy shared across all instances
- Stored in **method area** (memory), not heap

### Example
```java
class Counter {
    static int count = 0;  // Shared by all instances
    
    Counter() {
        count++;
    }
}
```

### Usage
```java
Counter c1 = new Counter();  // count = 1
Counter c2 = new Counter();  // count = 2
Counter c3 = new Counter();  // count = 3

System.out.println(Counter.count);  // Output: 3
```

### Key Points
- Initialized when class loads
- Can be accessed/modified via class name or object reference
- Useful for maintaining shared state across instances

---

## 5. `final` with Classes

### Meaning
- Class **cannot be inherited/extended**
- Prevents subclassing

### Example
```java
final class ImmutableClass {
    // Cannot be extended
}

// ❌ Compilation Error
class Child extends ImmutableClass { }
```

### Key Points
- Used for security (e.g., `String`, `Integer` classes)
- Prevents unintended modifications through inheritance
- Allows compiler optimizations

---

## 6. `final` with Methods

### Meaning
- Method **cannot be overridden** in subclasses
- Implementation is locked

### Example
```java
class Parent {
    final void criticalOperation() {
        System.out.println("This cannot be overridden");
    }
}

class Child extends Parent {
    // ❌ Compilation Error - cannot override final method
    // void criticalOperation() { }
}
```

### Key Points
- Used when method behavior must not be changed by subclasses
- Allows compiler optimizations

---

## 7. `final` with Variables

### Meaning
- Variable value **cannot be changed** after initialization
- Creates an immutable reference

### Example
```java
final int x = 10;
// ❌ x = 20;  Compilation Error

final String name = "John";
// ❌ name = "Jane";  Compilation Error
```

### Initialization Rules
Must be initialized exactly once via one of these:
- At declaration
- In constructor
- In initializer block

```java
class Example {
    final int a = 5;           // At declaration
    final int b;
    
    Example(int b) {
        this.b = b;            // In constructor
    }
    
    final int c;
    {
        c = 10;                // In initializer block
    }
}
```

### Important: `final` Reference ≠ Immutable Object
```java
final List<Integer> list = new ArrayList<>();
list.add(1);        // ✓ Allowed (modifying object)
// list = new ArrayList<>();  // ❌ Not allowed (reassigning reference)
```

---

## 8. `static final` Combination

### Meaning
- Class-level **constant**
- Initialized once, never changed
- Shared across all instances

### Example
```java
static final double PI = 3.14159;
static final int MAX_USERS = 100;
static final String APP_NAME = "MyApp";
```

### Convention
- Use **UPPER_CASE** naming
- Commonly used in interfaces for constants

```java
public interface Constants {
    static final int STATUS_ACTIVE = 1;      // Implicitly public static final
    int STATUS_INACTIVE = 0;                 // Same as above
}
```

### Key Points
- Most common way to define constants
- Memory efficient (single copy)
- Thread-safe by design

---

## 9. Common Rules & Pitfalls

### `static` Related
- Static methods cannot use `this` or `super`
- Static variables are shared—use carefully in multi-threaded code
- Static methods are **hidden**, not overridden (different from overriding)

```java
class Parent {
    static void print() { System.out.println("Parent"); }
}

class Child extends Parent {
    static void print() { System.out.println("Child"); }  // Hides, not overrides
}

Parent p = new Child();
p.print();  // Output: Parent (calls Parent's method)
```

### `final` Related
- `final` reference does NOT mean immutable object
- `final` variables must be initialized exactly once
- Cannot modify `final` variables after initialization

### `static final` Related
- Most efficient for constants (no instance overhead)
- Thread-safe by design
- Best practice for defining constants

---

## 10. Final Recap

| Feature | Classes | Methods | Variables |
|---------|---------|---------|-----------|
| `static` | Inner classes only | Class method, no instance | Shared variable, single copy |
| `final` | Cannot be inherited | Cannot be overridden | Cannot be reassigned |
| `static final` | ❌ Not applicable | Valid but redundant | Constant, best practice |

### Quick Summary
- **`static`** → shared, class-level, single copy
- **`final`** → cannot change, override, or extend
- **`static final`** → constants, class-level, immutable
- Use `static` for shared state & utility functions
- Use `final` for security & immutability
- Use `static final` for constants

---
