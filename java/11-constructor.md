# Java Constructors — Rules, Types, and Chaining

## Table of Contents
1. What Is a Constructor  
2. Core Rules of Constructors  
3. Modifiers and Constructors (final, static, abstract)  
4. Types of Constructors  
5. Constructor Chaining  
6. this() vs super()  
7. Common Pitfalls  
8. Quick Revision Summary  

---

## 1. What Is a Constructor
- Special method used to initialize objects
- Name must be same as class name
- No return type (not even void)
- Called automatically during object creation

```java
Car car = new Car();
```

---

## 2. Core Rules of Constructors
- Constructor name == class name
- Cannot have a return type
- Can be overloaded
- Not inherited by subclasses
- Executed once per object creation
- If no constructor is defined, compiler adds a default constructor

---

## 3. Modifiers and Constructors (Important)

### Can a constructor be final?
- No
- Reason: constructors are not inherited, so overriding is impossible

### Can a constructor be static?
- No
- Reason: constructors are tied to object creation, not class-level behavior

### Can a constructor be abstract?
- No
- Reason: abstract methods must be overridden, constructors cannot be overridden

> Rule:  
> Constructors cannot be final, static, or abstract

---

## 4. Types of Constructors

### Default Constructor
- Added by compiler if no constructor is defined
- Has no parameters

```java
class A {
}
```

### No-Argument Constructor
- Explicitly written
- Used for controlled initialization

```java
class A {
    A() {}
}
```

### Parameterized Constructor
- Accepts parameters
- Used to initialize object state

```java
class A {
    int x;
    A(int x) {
        this.x = x;
    }
}
```

---

## 5. Constructor Chaining
Constructor chaining means calling one constructor from another.

Benefits:
- Avoids code duplication
- Centralizes initialization logic
- Improves maintainability

Two ways:
- this()
- super()

---

## 6. this() vs super()

### this()
- Calls another constructor in the same class
- Must be the first statement
- Used for constructor overloading

```java
class A {
    A() {
        this(10);
    }

    A(int x) {
        System.out.println(x);
    }
}
```

---

### super()
- Calls constructor of parent class
- Must be the first statement
- Added implicitly if not written

```java
class B extends A {
    B() {
        super();
    }
}
```

Rules:
- Either this() or super() can be used
- Cannot use both together
- Cannot appear after any other statement

---

## 7. Common Pitfalls
- Constructor name does not match class name
- Assuming constructors are inherited
- Using both this() and super()
- Adding return type accidentally
- Forgetting parent constructor requirements

---

## 8. Quick Revision Summary
- Constructor initializes objects
- No return type
- Constructor cannot be inherited
- Cannot be final, static, or abstract
- Supports overloading, not overriding
- this() calls same class constructor
- super() calls parent class constructor
- Constructor call must be first statement

> Rule of thumb:  
> Use constructor chaining to keep initialization logic clean and centralized
