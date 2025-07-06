# Factory Method Pattern - Pizza Store Example

## What is the Factory Method Pattern?

- All factory patterns encapsulate object creation
- The Factory Method Pattern encapsulates object creation by letting **subclasses decide** what objects to create.
- The Creator class will be abstract and it will have an abstract factory method that the subclasses will implement to produce Product (i.e concrete objects).
- The Creator has no idea on which Product (i.e concrete object) is created by one of it sublcass. Thats the beauty, by this we achieve decoupling between both.
- The Factory Method Pattern is a creational design pattern that defines an interface for creating an object, but lets subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.

- **Encapsulates object creation**: The logic for creating objects is moved out of the client code and into a factory method.
- **Promotes loose coupling**: The client code works with abstract types and does not depend on concrete classes.

## Structure in This Code

### 1. Product (`Pizza`)

- `Pizza` is an abstract class that defines the interface and some common behavior for all pizzas.
- Concrete pizza classes (e.g., `NYStyleCheesePizza`, `ChicagoStyleVeggiePizza`) extend `Pizza` and set their own properties.

### 2. Creator (`PizzaStore`)

- `PizzaStore` is an abstract class that defines the `orderPizza()` method, which outlines the steps to make a pizza.
- It declares an abstract factory method `createPizza(String type)` that subclasses must implement.

### 3. Concrete Creators (`NYPizzaStore`, `ChicagoPizzaStore`)

- These classes extend `PizzaStore` and implement the `createPizza()` method to instantiate region-specific pizza types.

### 4. Client (`Main`)

- The `Main` class demonstrates how to use the pizza stores to order different types of pizzas without knowing the concrete classes being instantiated.

## How it Works

1. The client creates a specific `PizzaStore` (e.g., `NYPizzaStore`).
2. The client calls `orderPizza("cheese")` on the store.
3. The `orderPizza` method calls the factory method `createPizza("cheese")`, which is implemented by the concrete store.
4. The concrete store creates and returns the appropriate `Pizza` subclass.
5. The pizza is prepared, baked, cut, and boxed using the common methods in the `Pizza` class.

## Class Diagram

```
Main
 |
 v
PizzaStore (abstract)
 |         \
 v          v
NYPizzaStore  ChicagoPizzaStore
 |                |
 v                v
NYStyleCheesePizza  ChicagoStyleVeggiePizza
```

## Example Output

```
Preparing NY Style Sauce and Cheese Pizza
Tossing dough...
Adding sauce...
Bake for 25 mins at 350
Cutting the pizza into diagonal slices
Placing the pizza into a box
Ordered a NY Style Sauce and Cheese Pizza

Preparing Chicago Deep Dish Veggie Pizza
Tossing dough...
Adding sauce...
Bake for 25 mins at 350
Cutting the pizza into diagonal slices
Placing the pizza into a box
Ordered a Chicago Deep Dish Veggie Pizza
```

## Key Points

- The client code (`Main`) is decoupled from the concrete pizza classes.
- Adding a new pizza style or type only requires creating new subclasses, not modifying existing code.
- The Factory Method Pattern is ideal when a class can't anticipate the class of objects it must create.

---
