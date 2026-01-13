# Java Methods

## Table of Contents
1. Overview of Java Method Types  
2. Abstract Methods  
3. Default Methods  
4. Static Methods  
5. Final Methods  
6. Private Methods  
7. Synchronized Methods  
8. Native Methods  
9. Common Comparison Table  
10. Quick Revision Summary  

---

## 1. Overview of Java Method Types
Java provides different method types to support:
- Abstraction
- Reusability
- Encapsulation
- Concurrency control
- Performance optimization

Each method type exists for a specific design purpose.

---

## 2. Abstract Methods
- Declared using `abstract`
- No method body
- Must be implemented by subclasses
- Can exist only in abstract classes or interfaces (For interface, methods are just implicitly abstract, u dont need to specify abstract keyword)
- Cannot be `final`, `static`, or `private`

```java
public abstract class Shape {
    abstract double area();
}
```

Use when:
- You want to enforce implementation
- Behavior varies across subclasses

---

## 3. Default Methods (Interface)
- Introduced in Java 8
- Have method body inside interface
- Allow backward compatibility
- Can be overridden by implementing class

```java
public interface Logger {
    default void log(String msg) {
        System.out.println(msg);
    }
}
```

Use when:
- Adding new behavior to existing interfaces
- Providing optional implementation

---

## 4. Static Methods
- Belong to class, not instance
- Cannot access instance variables directly
- Can be called without object creation
- Allowed in classes and interfaces

```java
public class MathUtil {
    static int add(int a, int b) {
        return a + b;
    }
}
```

Use when:
- Behavior is utility-based
- Logic does not depend on object state

---

## 5. Final Methods
- Declared using `final`
- Cannot be overridden
- Can be inherited
- Often used for security or consistency

```java
public class Account {
    public final void validate() {
        // fixed validation logic
    }
}
```

Use when:
- Behavior must remain unchanged
- Prevent accidental overriding

---

## 6. Private Methods
- Accessible only within the same class
- Cannot be overridden
- Used for internal helper logic
- Interfaces allow private methods (Java 9+)

```java
private void calculateTax() {
    // internal logic
}
```

Use when:
- Method is an internal implementation detail

---

## 7. Synchronized Methods
- Used for thread safety
- Locks object or class monitor
- Slower due to locking overhead

```java
public synchronized void increment() {
    count++;
}
```

Use when:
- Shared mutable state exists
- Multiple threads access same method

---

## 8. Native Methods
- Implemented in non-Java languages (C/C++)
- Declared using `native`
- No method body

```java
public native void readFile();
```

Use when:
- Interacting with OS or hardware
- Performance-critical native operations

---

## 9. Common Comparison Table

| Method Type | Has Body | Overridable | Where Used |
|------------|---------|-------------|-----------|
| Abstract | No | Must override | Abstract class / Interface |
| Default | Yes | Yes | Interface |
| Static | Yes | No | Class / Interface |
| Final | Yes | No | Class |
| Private | Yes | No | Class / Interface |
| Synchronized | Yes | Yes | Class |
| Native | No | No | Class |

---

## 10. Quick Revision Summary
- Abstract → force implementation
- Default → optional interface behavior
- Static → utility logic
- Final → prevent overriding
- Private → internal helper
- Synchronized → thread safety
- Native → platform-specific logic

> Rule of thumb:  
> Choose method type based on who controls the behavior and who is allowed to change it
