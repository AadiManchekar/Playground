# Decorator Design Pattern

## What is the Decorator Pattern?

The **Decorator Pattern** is a structural design pattern that allows you to dynamically add new behavior or responsibilities to objects without modifying their existing code. It achieves this by wrapping the original object with a new object (the decorator) that adds the new behavior.

- **Key Points:**
  - Promotes composition over inheritance.
  - Useful for adhering to the Open/Closed Principle.
  - Decorators and the original object share a common interface.

## Example: Pizza Ordering System

This project demonstrates the Decorator Pattern using a pizza ordering system, where you can add toppings (decorators) to a base pizza.

### Structure

- **BasePizza.java**: Abstract class representing the pizza interface.
- **FarmHousePizza.java, MargheritaPizza.java**: Concrete implementations of base pizzas.
- **ToppingDecorator.java**: Abstract decorator class extending `BasePizza`.
- **ExtraCheese.java, Mushroom.java**: Concrete decorators (toppings) that add extra cost.
- **OrderMain.java**: Demonstrates how to compose pizzas with toppings.

### How the Pattern is Implemented

#### 1. Base Component

- [`BasePizza.java`](Pizza/BasePizza.java):  
  Abstract class with the method `cost()`.

#### 2. Concrete Components

- [`FarmHousePizza.java`](Pizza/FarmHousePizza.java):  
  Implements `cost()` for a Farmhouse pizza.
- [`MargheritaPizza.java`](Pizza/MargheritaPizza.java):  
  Implements `cost()` for a Margherita pizza.

#### 3. Decorator

- [`ToppingDecorator.java`](Pizza/Toppings/ToppingDecorator.java):  
  Abstract class extending `BasePizza`. All toppings will extend this.

#### 4. Concrete Decorators

- [`ExtraCheese.java`](Pizza/Toppings/ExtraCheese.java):  
  Adds extra cheese topping by wrapping a `BasePizza` and adding 10 to the cost.
- [`Mushroom.java`](Pizza/Toppings/Mushroom.java):  
  Adds mushroom topping by wrapping a `BasePizza` and adding 20 to the cost.

#### 5. Usage Example

- [`OrderMain.java`](Pizza/OrderMain.java):  
  Shows how to create pizzas with different combinations of toppings:

  ```java
  // Farmhouse pizza
  BasePizza pizza1 = new FarmHousePizza();
  System.out.println("pizza1 cost: " + pizza1.cost()); // 200

  // Farmhouse + Extra Cheese
  BasePizza pizza2 = new ExtraCheese(new FarmHousePizza());
  System.out.println("pizza2 cost: " + pizza2.cost()); // 210

  // Farmhouse + Extra Cheese + Mushroom
  BasePizza pizza3 = new Mushroom(new ExtraCheese(new FarmHousePizza()));
  System.out.println("pizza3 cost: " + pizza3.cost()); // 230
  ```

---

## Why Does `CondimentDecorator` Extend `Beverage`? (Decorator Pattern Deep Dive)

In the Decorator Pattern, the abstract class `CondimentDecorator` extends `Beverage` for several important reasons:

### 1. **Interface Compatibility**
By extending `Beverage`, `CondimentDecorator` ensures that all decorators (like `Mocha`, `Milk`, etc.) have the same interface as the objects they decorate. This allows decorators to be used anywhere a `Beverage` is expected.

```java
public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;
    public abstract String getDescription();
}
```

### 2. **Recursive Composition**
Decorators can wrap other decorators or base beverages, enabling flexible and dynamic combinations:

```java
Beverage order = new Mocha(new Milk(new DarkRoast()));
```
Each decorator "is a" `Beverage` and can wrap another `Beverage`, forming a chain.

### 3. **Polymorphism**
Client code can treat decorated and undecorated objects uniformly. All objects respond to the same methods (`cost()`, `getDescription()`), regardless of how many decorators are applied.

### 4. **Open/Closed Principle**
You can add new functionality (like new condiments) without modifying existing code, simply by creating new decorators that extend `CondimentDecorator`.

**TLDR:**  
`CondimentDecorator` extends `Beverage` to maintain a consistent interface, enable recursive wrapping, support polymorphism, and adhere to the Open/Closed Principle. This is the foundation that makes the Decorator Pattern powerful

---

### Summary

- The Decorator Pattern allows you to add toppings (decorators) to pizzas (components) at runtime.
- Each topping wraps the pizza and adds its own cost, demonstrating dynamic behavior addition without modifying existing pizza classes.
- Decorator pattern is perfect example of Open/Closed principles
- Decorators have the same supertype as the objects they decorate.
- **The decorator adds its own behavior before and/or after delegating to the object it decorates to do the rest of the job.**
- If we rely on inheritance, then our behavior can only be determined statically at compile time. With composition, we can mix and match decorators any way we like...at runtime. Hence we use composition in Decorator class
---
