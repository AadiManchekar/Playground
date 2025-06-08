## Methods in Java

### Static Methods

- `static` methods are associated with the **class itself**, not with any specific instance (object) of the class.
- They are invoked using the class name, e.g., `ClassName.staticMethod()`.
- Static methods **cannot access non-static instance variables or methods** directly.
- **Static methods cannot be overridden** (they are bound at compile time).
  
#### When to Use `static`?
- When the method does **not modify the state** of any object.
- When creating **utility/helper methods** that only operate on the method parameters and not on instance variables.

#### Example: Static Method with Var-Args
```Java
public class Cart {
    // The var-arg parameter allows passing multiple int values
    public static int cartTotal(int... cartItems) {
        int total = 0;
        for (int item : cartItems) {
            total += item;
        }
        return total;
    }
}

// Method invocation
int total = Cart.cartTotal(5, 10, 15, 20);
System.out.println("Total: " + total);
```
- Only one var-arg can be present in a method's parameters.
- it should be last in parameter's list.

### Final
- A final method cannot be overridden by subclasses.
- This is because the implementation of a final method is considered complete and unchangeable.
- If a parent class has a final method, the child class inherits it as-is and cannot provide a new implementation.
```Java
public class Parent {
    public final void show() {
        System.out.println("This is a final method.");
    }
}

public class Child extends Parent {
    // Compilation error if you try to override the final method
    // public void show() {
    //     System.out.println("Trying to override.");
    // }
}
```

### Abstract 
- Abstract methods are declared using the abstract keyword and do not have a body.
- They must be defined inside an abstract class.
- Subclasses that extend the abstract class are required to implement all abstract methods unless they are also abstract.
```Java
public abstract class Animal {
    // Abstract method declaration (no implementation)
    public abstract void makeSoundNTimes(int n);
}

public class Cat extends Animal {
    // Method definition
    @Override
    public void makeSoundNTimes(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Meow");
        }
    }
}

```