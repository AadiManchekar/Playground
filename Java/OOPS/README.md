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
* All the constructors in an object’s inheritance tree must run when you make a new object.
```Java
class Vehicle {
    Vehicle() {
        System.out.println("Vehicle constructor called");
    }
}
class Car extends Vehicle {
    Car() {
        System.out.println("Car constructor called");
    }
}
class Main {
    public static void main(String[] args) {
        Car myCar = new Car(); 
        // Output: 
        // Vehicle constructor called
        // Car constructor called
    }
}
```
* The thing that stops a class from being subclassed is the keyword modifier final. A final class means that it’s the end of the inheritance line. Nobody, ever, can extend a final class.
* if a class has only private constructors, it can’t be subclassed.
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
* With polymorphism, you can write code that doesn’t have to change when you introduce new subclass types into the program.
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

## Are you using Inheritance or Abusing it?
- Do use inheritance when you have a clear **IS-A** relationship.
- DO consider inheritance when you have behavior (implemented code) that should be shared among multiple classes of the same general type.
- Example: `public class Dog extends Animal` is a good use of inheritance because a Dog IS-A Animal.
- Be aware, however, that while inheritance is one of the key features of object-oriented programming, it’s not necessarily the best way to achieve behavior reuse. 
- Do Not use inheritance when you have a **HAS-A** relationship. Use composition instead.
- DO NOT use inheritance just so that you can reuse code from another class

---

## Abstract Classes

- **Abstract Class**: Can have both abstract and concrete methods, instance variables, and constructors. Supports single inheritance (can extend only one class). Cannot be instantiated directly.
- **When to use Abstract Class**: When you want to provide a common base class with some shared implementation and state, and you expect subclasses to extend it.
- If you declare an abstract method, you MUST mark the class abstract as well. You can’t have an abstract method in a non-abstract class.

```java
abstract class Animal {
    String name; // Instance variable
    Animal(String name) { // Constructor
        this.name = name;
    }
    abstract void makeSound(); // Abstract method
    void sleep() { // Concrete method
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }
    void makeSound() { // Implementing abstract method
        System.out.println("Woof!");
    }
}
```

---

## Object Class (java.lang.Object)
- Every class in Java extends class Object.
- **Object Class**: The root class of all classes in Java. Every class is a subclass of the Object class, either directly or indirectly. It provides basic methods like `toString()`, `equals()`, and `hashCode()`.
- Some of the methods are marked final, which means you can’t override them. You’re encouraged (strongly) to override hashCode(), equals(), and toString() in your own classes.
- Some of the most important methods in Object are related to threads. 
  - `wait()`: Causes the current thread to wait until another thread invokes the notify() or notifyAll() method for this object.
  - `notify()`: Wakes up a single thread that is waiting on this object's monitor.
  - `notifyAll()`: Wakes up all threads that are waiting on this object's monitor.
- If Object is the parent of all other classes, then why dont we use it for every return type? 
  - Because Object is not type-safe. If you return an Object, you have to cast it back to the original type, which can lead to `ClassCastException` at runtime if the object is not of the expected type.
  - Also, using Object as a return type can lead to less readable and maintainable code, as it obscures the actual type being returned.
  - You would defeat the whole point of “type-safety,” one of Java’s greatest protection mechanisms for your code. With type-safety, Java guarantees that you won’t ask the wrong object to do something you meant to ask of another object type. Like, ask a Ferrari (which you think is a Toaster) to cook itself.
  - When objects are referred to by an Object reference type, Java thinks it’s referring to an instance of type Object. And that means the only methods you’re allowed to call on that object are the ones declared in class Object! So if you were to say:
    ```java
    class Ferrari {
        void goFast() {
            System.out.println("Vroom Vroom!");
        }
    }

    class Main {
        public static void main(String[] args) {
            Object o = new Ferrari();
            o.goFast(); // Not legal!
        }
    }
    ```
  - The compiler will complain that the method goFast() is not defined in class Object.
  - **The compiler decides whether you can call a method based on the reference type, not the actual object type.**
  - So, if you want to call methods specific to the Ferrari class, you need to cast the Object reference back to Ferrari:
    ```java
    class Main {
        public static void main(String[] args) {    
            Object o = new Ferrari();
            ((Ferrari) o).goFast(); // Legal, but requires casting
        }
    }

    // you can also use instanceof to check the type before casting to avoid ClassCastException
    class Main {
        public static void main(String[] args) {    
            Object o = new Ferrari();
            if (o instanceof Ferrari) {
                ((Ferrari) o).goFast(); // Safe casting
            } else {
                System.out.println("Not a Ferrari!");
            }
        }
    }
    ```

---

## Interfaces vs Abstract Classes
- Interfaces are used for broader systems, where the classes that implement it are not necessarily related.
- Whereas, usually, classes extending an abstract class would be somewhat related.
- Abstract classes are used when you have a common base class that provides some shared implementation and state.
- Interfaces only guarantee behavior, and the methods will need to be implemented in the classes implementing them.
- If the classes share (or could share) some fields and implementation, use an abstract class. If they only share behavior, use an interface.
- A class can implement multiple interfaces, which provides a workaround for Java's lack of multiple inheritance.
- https://byjus.com/gate/difference-between-abstract-class-and-interface-in-java/
- https://harsh05.medium.com/abstract-classes-vs-interfaces-in-java-when-and-how-to-use-them-5ca5d5c825b5

---

## How do you know whether to make a class, a subclass, an abstract class, or an interface?
- Make a **class** that doesn’t extend anything (other than Object) when your new class doesn’t pass the IS-A test for any other type.
- Make a **subclass** (in other words, extend a class) only when you need to make a more specific version of a class and need to override or add new behaviors.
- When you don’t want a class to be instantiated (in other words, you don’t want anyone to make a new object of that class type), mark the class with the **abstract** keyword.
- Use an **abstract class** when you want to define a template for a group of subclasses, and you have at least some implementation code that all subclasses could use. Make the class abstract when you want to guarantee that nobody can make objects of that type.
- Use an **interface** when you want to define a role that other classes can play, regardless of where those classes are in the inheritance tree.


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
