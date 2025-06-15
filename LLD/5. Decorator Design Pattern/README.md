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

### Summary

- The Decorator Pattern allows you to add toppings (decorators) to pizzas (components) at runtime.
- Each topping wraps the pizza and adds its own cost, demonstrating dynamic behavior addition without modifying existing pizza classes.

---
