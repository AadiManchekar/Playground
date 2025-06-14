# Observer Design Pattern

## Overview

The **Observer Design Pattern** is a behavioral design pattern where an object (the *Observable* or *Subject*) maintains a list of its dependents (*Observers*) and notifies them automatically of any state changes, usually by calling one of their methods. This pattern is commonly used to implement distributed event-handling systems.

## How It Works

- **Observable**: The object being observed. It notifies observers about changes.
- **Observer**: The object that wants to be notified about changes in the observable.

When the observable's state changes, it notifies all registered observers, allowing them to react accordingly.

## Code Flow and File References

### 1. Observer Interface

- **File:** `Observer/Observer.java`
- **Purpose:** Defines the contract for observers. Any class that wants to receive updates must implement this interface.

```java
public interface Observer {
    public void update();
    public void update(String msg);
}
```

### 2. Observable Interface

- **File:** `Observable/Observable.java`
- **Purpose:** Defines the contract for observables. Allows adding/removing observers, notifying them, and setting/getting data (generic).

```java
public interface Observable {
    void add(Observer obj);
    void remove(Observer obj);
    void notifySubscribers(String msg);
    <T> void setData(T data);
    <T> T getData();
}
```

### 3. Concrete Observable

- **File:** `Observable/IphoneObservable.java`
- **Purpose:** Implements the `Observable` interface. Manages stock for iPhones and notifies observers when stock is updated.

### 4. Observer Implementation

- **File:** `Observer/Person.java`
- **Purpose:** Implements the `Observer` interface. Represents a person who wants to be notified (e.g., via email) when the observable changes.

### 5. Notification Mechanism

- **Files:** 
  - `Observer/notify/EmailNotify.java` (interface for notification)
  - `Observer/notify/EmailNotifier.java` (concrete implementation)
- **Purpose:** Abstracts the notification mechanism (e.g., sending emails).

## Flow Example

1. **Person** objects are created with a reference to an `Observable` (e.g., `IphoneObservable`) and auto-subscribe themselves.
2. When the stock is updated in `IphoneObservable` via `setData`, it checks if stock was previously zero and notifies all observers if new stock is available.
3. Each `Person` receives the notification and uses their `EmailNotifier` to send an email.

## Extensibility with Observable Interface

By using the `Observable` interface, you can easily create new types of observables. For example:

- **EventObservable**: Instead of tracking integer stock, it could track a `String` date for an event.

```java
public class EventObservable implements Observable {
    String eventDate;
    // ...implement add, remove, notifySubscribers, setData, getData...
}
```

This flexibility is possible because the interface uses Java generics for `setData` and `getData`, allowing any data type (e.g., `Integer` for stock, `String` for dates, etc.).

## Summary

- The Observer pattern decouples the observable and observers, allowing for flexible and reusable code.
- The use of interfaces (`Observable`, `Observer`, `EmailNotify`) makes the system extensible and easy to modify for different use cases.
