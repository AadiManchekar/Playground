# SOLID Principles Guide

## 1. Single Responsibility Principle (SRP)
### Definition
> A class should have only one reason to change, meaning it should have one job or responsibility.

### Why?
This reduces the complexity of the system, makes code easier to understand, test, and maintain.

### Example
* ❌ **Bad**: A User class that handles user data and also manages user database operations.
* ✅ **Good**: A User class for holding user data, and a separate UserRepository class for database operations.

---

## 2. Open/Closed Principle (OCP)
### Definition
> A class should be open for extension but closed for modification.

### Why?
You should be able to add new features or behaviors without changing the existing, tested, and working code.

### How?
Use abstractions (interfaces or abstract classes) and inheritance or composition.

### Example
* ❌ **Bad**: Modifying an existing class every time a new type of payment is introduced.
* ✅ **Good**: Define a Payment interface and create new classes like CreditCardPayment, PaypalPayment that implement this interface.

---

## 3. Liskov Substitution Principle (LSP)
### Definition
> * Subclasses should be fully substitutable for their parent classes.
> * A child class should extend the functionality of the parent without breaking expected behavior.
> * Child classes should not weaken or break the contract established by the parent class.

### Why?
Ensures that derived classes enhance or respect the behavior of base classes. Ensure that the "is-a" relationship is logically correct.

### Example
#### ❌ Bad Example (Bird-Penguin Problem)
```java
class Bird {
    void fly() {
        System.out.println("Bird is flying");
    }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}
```
**Problem**: A Penguin is-a Bird, but penguins cannot fly. If you substitute Bird with Penguin in existing code expecting flying behavior, it will break.

#### ✅ Good Example (Solution with Better Abstraction)
```java
interface Bird {
    void eat();
}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void eat() {
        System.out.println("Sparrow is eating");
    }
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

class Penguin implements Bird {
    public void eat() {
        System.out.println("Penguin is eating");
    }
}
```
* FlyingBird interface separates birds that can fly
* Penguin now correctly does not implement fly(), so no substitution problem occurs
* Both classes can now be safely substituted within their correct expectations

---

## 4. Interface Segregation Principle (ISP)
### Definition
> Clients should not be forced to depend on interfaces they do not use/require.

Interfaces should be small and focused — each interface should have a clear, single purpose.

### Why?
Fat (large) interfaces force classes to implement unnecessary methods.

### How?
Split large interfaces into smaller, more specific ones.

### Example
#### ❌ Bad Example (Fat Interface)
```java
interface MultiFunctionMachine {
    void print();
    void scan();
    void fax();
}

class OldPrinter implements MultiFunctionMachine {
    public void print() { System.out.println("Printing"); }
    public void scan() { throw new UnsupportedOperationException(); }
    public void fax() { throw new UnsupportedOperationException(); }
}
```
**Problem**: OldPrinter is forced to implement scan() and fax(), even though it doesn't support them.

#### ✅ Good Example (Split Interfaces)
```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void fax();
}

class SimplePrinter implements Printer {
    public void print() { System.out.println("Printing"); }
}

class AllInOneMachine implements Printer, Scanner, Fax {
    public void print() { System.out.println("Printing"); }
    public void scan() { System.out.println("Scanning"); }
    public void fax() { System.out.println("Faxing"); }
}
```
* SimplePrinter only implements what it supports
* AllInOneMachine implements all features

---

## 5. Dependency Inversion Principle (DIP)
### Definition
> High-level modules should not depend on low-level modules. Both should depend on abstractions.

### Why?
This reduces tight coupling between classes, making systems more flexible and easier to modify.

### How?
Depend on interfaces or abstract classes, not on concrete implementations.

### Example
* ❌ **Bad**: A Store class directly creating and using a FileLogger
* ✅ **Good**: Store depends on a Logger interface. The actual logging implementation (FileLogger, DatabaseLogger) is injected