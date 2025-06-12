# Open-Closed Principle (OCP) in Java

The Open-Closed Principle is the 'O' in SOLID principles. It states that software entities (classes, modules, functions, etc.) should be:
- **Open for extension**: New functionality can be added without modifying existing code
- **Closed for modification**: Existing code should not be changed to add new features

---

## Problem Example

Here's code that violates the Open-Closed Principle:
```Java
// Bad example - Violates OCP
public class Shape {}

public class Square extends Shape{
    private int side;

    public Square(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length must be positive");
        }
        this.side = side;
    }

    double calculateArea() {
        return (double) side * side;
    }
}

public class Rectangle extends Shape{
    private int length;
    private int breadth;

    public Rectangle(int length, int breadth) {
        if (length <= 0 || breadth <= 0) {
            throw new IllegalArgumentException("Length and breadth must be positive");
        }
        this.length = length;
        this.breadth = breadth;
    }

    double calculateArea() {
        return (double) length * breadth;
    }
}

public class AreaCalculator  {
    public double calculateArea(Shape shape) {
        // Need to modify this method every time a new shape is added
        if (shape instanceof Rectangle) {
            Rectangle rec = (Rectangle) shape;
            return rec.calculateArea();
        } else if (shape instanceof Square) {
            Square sq = (Square) shape;
            return sq.calculateArea();
        }
        throw new IllegalArgumentException("Unknown shape type");
    }
}
```

- The AreaCalculator class needs modification every time we add a new shape
- This violates OCP because the class is not closed for modification
- Adding a new shape requires changing existing code


## Solution Using OCP

Here's how to refactor the code to follow OCP:

```Java
// Good example - Follows OCP
public abstract class Shape {
    abstract double calculateArea();
}

public class Square extends Shape {
    private int side;

    public Square(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length must be positive");
        }
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return (double) side * side;
    }
}

public class Rectangle extends Shape {
    private int length;
    private int breadth;

    public Rectangle(int length, int breadth) {
        if (length <= 0 || breadth <= 0) {
            throw new IllegalArgumentException("Length and breadth must be positive");
        }
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public double calculateArea() {
        return (double) length * breadth;
    }
}

public class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}
```
- Benefits
    - New shapes can be added by extending Shape class
    - No modification needed in AreaCalculator
    - Existing code remains untouched
    - Reduces risk of bugs in production code
- Also just FYI u cannot create an object of Shape so while calling AreaCalculator->calculateArea u need to pass an concrete class only.

---

## Summary
- By making `Shape` abstract with an abstract `calculateArea()` method, we ensure all shapes must implement area calculation
- `AreaCalculator` depends on abstraction rather than concrete implementations
- New shapes can be added without modifying existing code
- This demonstrates the Open-Closed Principle effectively

## Note
Remember: The key is to rely on abstractions (abstract classes or interfaces) rather than concrete implementations when designing your classes.