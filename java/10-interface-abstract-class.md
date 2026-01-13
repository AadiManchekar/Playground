# Java Interfaces vs Abstract Classe

## Table of Contents
1. Interfaces — Core Concepts  
2. Abstract Classes — Core Concepts  
3. Key Differences: Interface vs Abstract Class  
4. When to Use an Interface  
5. When to Use an Abstract Class  
6. Common Design Scenarios  
7. Java Code Examples (Minimal & Clear)  
8. Quick Revision Summary  

---

## 1. Interfaces — Core Concepts
- Defines a contract (what to do, not how)
- Supports multiple inheritance of behavior
- All methods are public by default
- Variables are public static final (constants)
- Cannot have instance state
- Java 8+: default and static methods allowed
- Java 9+: private helper methods allowed

---

## 2. Abstract Classes — Core Concepts
- Represents an is-a relationship
- Can have state (instance variables)
- Can contain abstract and concrete methods
- Supports single inheritance only
- Methods can use any access modifier
- Can have constructors

---

## 3. Key Differences: Interface vs Abstract Class

| Aspect | Interface | Abstract Class |
|------|----------|----------------|
| Inheritance | Multiple allowed | Single only |
| Methods | Abstract, default, static | Abstract and concrete |
| State | No instance variables | Can have instance variables |
| Constructors | Not allowed | Allowed |
| Access Modifiers | Always public | Any |
| Primary Use | Capability / contract | Base class / shared logic |

---

## 4. When to Use an Interface
- Multiple unrelated classes need same capability
- Multiple inheritance required
- Defining public APIs
- Loose coupling is important
- Implementations vary completely

---

## 5. When to Use an Abstract Class
- Classes share common state
- Base functionality must be reused
- Subclasses extend behavior
- You control the inheritance hierarchy

---

## 6. Common Design Scenarios

### Payment Systems
- Different payment methods
- No shared state

Best choice: Interface

### Vehicle Hierarchy
- Shared fields and logic

Best choice: Abstract Class

---

## 7. Java Code Examples

### Interface Example
```java
public interface Payment {
    void pay(double amount);
}
```

```java
public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via UPI: " + amount);
    }
}
```

### Abstract Class Example
```java
public abstract class Vehicle {
    protected int speed;

    abstract void move();

    public void accelerate() {
        speed += 10;
    }
}
```

```java
public class Car extends Vehicle {
    @Override
    void move() {
        System.out.println("Car is moving at " + speed);
    }
}
```

---

## 8. Quick Revision Summary
- Interface → what a class can do
- Abstract Class → what a class is
- Multiple inheritance → Interface
- Shared state/logic → Abstract Class
