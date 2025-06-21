# Strategy Design Pattern Example

## Problem Statement (Without Strategy Pattern)

In the [WithoutStrategyDesignPattern](WithoutStrategyDesignPattern) implementation, we face several challenges:

1. **Code Duplication**: The same drive capability implementation is duplicated in [`SportsVehicle`](WithoutStrategyDesignPattern/SportsVehicle.java) and [`OffRoadVehicle`](WithoutStrategyDesignPattern/OffRoadvehicle.java) classes:
```java
public void drive() {
    System.out.println("Sports drive capability");
}
```

2. **Inflexible Design**: Base [`Vehicle`](WithoutStrategyDesignPattern/Vehicle.java) class has a fixed drive behavior that may not suit all child classes.

3. **Violation of OCP**: To add new driving behaviors, we need to modify existing classes.

## Solution: Strategy Pattern

The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

### Implementation

In [WithStrategyDesignPattern](WithStrategyDesignPattern), we solve these issues by:

1. **Creating Strategy Interface**: [`DriveStrategy`](WithStrategyDesignPattern/Strategy/DriveStrategy.java) interface defines the contract for different driving behaviors.

2. **Implementing Concrete Strategies**:
   - [`NormalDriveStrategy`](WithStrategyDesignPattern/Strategy/NormalDriveStrategy.java) - For regular vehicles
   - [`SportsDriveStrategy`](WithStrategyDesignPattern/Strategy/SportsDriveStrategy.java) - For sports and off-road vehicles

3. **Strategy Injection**: [`Vehicle`](WithStrategyDesignPattern/Vehicle.java) class accepts driving strategy through constructor:
```java
Vehicle(DriveStrategy driveStrategy) {
    this.driveStrategy = driveStrategy;
}
```

### Benefits Achieved

1. **Eliminated Code Duplication**: Both [`SportsVehicle`](WithStrategyDesignPattern/SportsVehicle.java) and [`OffRoadVehicle`](WithStrategyDesignPattern/OffRoadvehicle.java) reuse the same `SportsDriveStrategy`.

2. **Flexible & Maintainable**: New driving behaviors can be added by creating new strategy implementations without modifying existing code.

3. **Runtime Behavior Change**: Driving behavior can be changed at runtime by injecting different strategies.

4. **Better Separation of Concerns**: Driving algorithms are separated from the vehicle classes that use them.

## Usage Example

```java
// Creating vehicles with different driving strategies
Vehicle sportsVehicle = new SportsVehicle();         // Uses SportsDriveStrategy
Vehicle passengerVehicle = new PassengerVehicle();   // Uses NormalDriveStrategy
Vehicle offRoadVehicle = new OffRoadVehicle();       // Uses SportsDriveStrategy

// Each vehicle uses its specific driving behavior
sportsVehicle.drive();    // Output: Sports drive capability
passengerVehicle.drive(); // Output: Normal drive capability
offRoadVehicle.drive();   // Output: Sports drive capability
```

---

### Best Explanation
- The behavior of a class needs to change dynamically at runtime.
- You want to avoid cluttering your code with conditional logic (like if-else or switch statements) for every variation.
- Easy plug-and-play behavior at runtime
- A design that is open for extension, but closed for modification
- Whenever you see Open/Closed Principle getting violated, try using Strategy Pattern. They both go hand in hand.
- https://blog.algomaster.io/p/9c9932d8-80ad-4848-bf28-d6e01892a011