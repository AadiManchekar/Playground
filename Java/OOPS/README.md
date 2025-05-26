# Java OOPs Concepts

## Pillars of OOPS

### 1. Abstraction

* Abstraction is hiding complexities.
* Hiding implementation and exposing interfaces.
* In Java, we achieve abstraction by using **interfaces** and **abstract classes**.

### 2. Encapsulation

* Bundling the data (variables) and the methods (functions) that operate on the data into a single unit. Basically **class**.
* In procedural programming, **data moves freely**. i.e function to function and any function can change the state of the data. But when it comes to Object oriented programming, with help of encapsulation the **class takes the ownership of its data**.
* **Data Hiding**: Declare your variables as private and have getters and setters as public.
* Encapsulation can also help us to create **immutable objects**. Basically to create a class with variables as private, have parameterized constructor and no setters. This ensures that state will never be changed after creation of object. This enhances security and simplifies concurrent programming.

### 3. Inheritance

* Inheritance is the mechanism that allows one class to acquire all the properties from another class by inheriting the class.
* `public class Car extends Vehicle` When we extend a class, we form an **IS-A relationship**. The Car IS-A Vehicle. So, it has all the characteristics of a Vehicle.
* Java supports:

  * **Single inheritance**: `Class A -> Class B` \[Here, A is a parent class to B], Calling `super();` in class B calls constructor of A. 
  * **Multi-level inheritance**: `Class A -> Class B`,  `Class B -> Class C` \[Here, A is a parent class to B which is again parent class to C], Calling `super();` in B calls constructor of A and calling `super();` in C calls constructor of B.
  * **Hierarchical inheritance**: `Class A -> Class B`,  `Class A -> Class C` \[Here, A is a parent class to both B and C]
  * **Multiple inheritance**: `Class A -> Class C`,   `Class B -> Class C` ❌ Not allowed in Java. Through interface we can resolve the diamond problem 🔻 as we can do `Class C implements A, B` where A and B are interfaces. Interfaces do not have constructors so we cannot call `super();`. But if there is default functions with same name in both interfaces then we can use `super();`
  ```java
  interface A {
    default void foo() {
        System.out.println("A's foo");
    }
  }

  interface B {
      default void foo() {
          System.out.println("B's foo");
      }
  }

  class C implements A, B {
      public void callFooFromA() {
          A.super.foo();
      }

      public void callFooFromB() {
          B.super.foo();
      }

      public static void main(String[] args) {
          C c = new C();
          c.callFooFromA(); // Output: A's foo
          c.callFooFromB(); // Output: B's foo
      }
  }
  ```

* `public class ArmoredCar extends Car` Here, the ArmouredCar extends Car, and Car extends Vehicle. So, ArmouredCar inherits properties from both Car and Vehicle.
* In child class, we can override a method which is inherited from parent. This is **method overriding**. This is also termed as **runtime polymorphism**.
* We can achieve Polymorphism using inheritance.

📚 More on the diamond problem: [https://www.ccbp.in/blog/articles/diamond-problem-in-java](https://www.ccbp.in/blog/articles/diamond-problem-in-java)

Rules in multiple inheritance with interfaces and default methods:

* **Class Over Interface**: If a class implements an interface and also inherits a method from a superclass, the class's or superclass's method takes precedence.
* **Most Specific Interface**: If multiple interfaces provide default methods, the method from the more specific interface (closer in inheritance hierarchy) is used.
* **Explicit Override**: If there's a conflict (same method in multiple interfaces), the implementing class must explicitly override the method to resolve the ambiguity.

### 4. Polymorphism

* Poly means **Many** and Morphism means **Form**.
* A same method behaves differently in different situations.
* Method overloads in Java are only **allowed** for **methods with different signatures (parameters)**.
* You **cannot** do method overloading with **different return type**:

```java
public int foo() { ... }
public float foo() { ... }

...
foo(); // which one?
```

* The rationale is that the return value alone is not sufficient for the compiler to figure out which function to call.

📚 Beautifully explained here: [https://www.baeldung.com/java-oop#polymorphism](https://www.baeldung.com/java-oop#polymorphism)

## Types of Polymorphism

* **Compile time / static polymorphism / method overloading**
* **Run time / dynamic polymorphism / method overriding**

---

## Aggregation and Composition

📚 Reference: [https://medium.com/@salvipriya97/java-aggregation-and-composition-explained-with-examples-66cbffd21b9c](https://medium.com/@salvipriya97/java-aggregation-and-composition-explained-with-examples-66cbffd21b9c)

---

## Access Modifiers / Access Specifiers:
Java provides four access modifiers to set access levels for classes, variables, methods and constructors.
- Public: can be access through any class in any package
- Private: can be access by methods only in same class
- Protected: can be access by other methods in same package or other sub-classes in different package
- Default: if we dont mention anything, then Default access specifier is used in java. It can only be access by methods in same package

---

## Additional Java OOPS Questions

1. **What is the difference between abstraction and encapsulation?**
2. **Can we instantiate an abstract class in Java?** ➝ No, but we can have a reference of abstract class pointing to subclass object.
3. **What is the purpose of the `super` keyword?** ➝ To refer to the **immediate** parent class. [https://www.programiz.com/java-programming/super-keyword](https://www.programiz.com/java-programming/super-keyword)
4. **Can constructors be inherited?** ➝ No, constructors are not inherited.
5. **What is method hiding in Java?** ➝ When a static method in subclass has the same signature as a static method in parent class.
6. **Difference between method overloading and method overriding?**
7. **Can you override a private or static method?** ➝ No.
8. **What is the use of final keyword in Java?** ➝ Final variable (constant), final method (can't be overridden), final class (can't be extended).
9. **What happens if a class implements two interfaces with same default method?** ➝ You must override the method and resolve ambiguity.

---
