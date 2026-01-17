# Java Generics — Complete Guide with Examples

## Table of Contents
1. What Are Generics  
2. Why Generics Exist  
3. Generic Class (Getter / Setter Example)  
4. Generics with Inheritance  
5. Multiple Type Parameters  
6. Generic Methods  
7. Bounded Type Parameters  
8. Wildcards (? extends, ? super, ?)  
9. Comparison Table  
10. Quick Revision Summary  

---

## 1. What Are Generics
Generics allow you to:
- Write type-safe code
- Avoid explicit casting
- Catch errors at compile time
- Reuse logic for different data types

---

## 2. Why Generics Exist

Without generics:
```java
Object obj = "Hello"; // Storing as Object, type is lost
String s = (String) obj; // Need to cast explicitly - error-prone
```

With generics:
```java
Box<String> box = new Box<>(); // Type specified - type-safe
String s = box.get(); // No casting needed, compiler knows it's a String
```

---

## 3. Generic Class (Getter / Setter Example)

```java
class Box<T> { // T is a type parameter (placeholder for any type)
    private T value;

    public void set(T value) { // Accepts any type T
        this.value = value;
    }

    public T get() { // Returns type T
        return value;
    }
}
```

Usage:
```java
Box<Integer> intBox = new Box<>(); // T is replaced with Integer
intBox.set(10); // Only accepts Integer values
```

---

## 4. Generics with Inheritance

```java
class Box<T> { // Generic parent class
    T value;
}

class IntegerBox extends Box<Integer> { // Fixed type parameter
}

class FancyBox<T> extends Box<T> { // Keeps type parameter generic
}
```

---

## 5. Multiple Type Parameters

```java
class Pair<K, V> { // K and V are two separate type parameters
    private K key; // Key can be one type
    private V value; // Value can be a different type

    Pair(K key, V value) { // Constructor accepts both types
        this.key = key;
        this.value = value;
    }
}
```

---

## 6. Generic Methods

```java
class Utils {
    static <T> void print(T data) { // <T> makes this a generic method, T isnt the return type
        System.out.println(data); // Works with any type
    }
}
```

---

## 7. Bounded Type Parameters

```java
class Calculator<T extends Number> { // T must be Number or any subclass of Number
    T num;

    Calculator(T num) {
        this.num = num;
    }

    double doubleValue() {
        return num.doubleValue();
    }
}
```

---

## 8. Wildcards

### Unbounded
```java
void print(List<?> list) { 
    // ? means any type - can READ (get) but CANNOT write (add)
    // Example: list.get(0) works, but list.add("something") fails - compiler doesn't know the exact type
}
```

### Upper Bounded
```java
void read(List<? extends Number> list) { 
    // ? is Number or any subclass - can READ but CANNOT write
    // Example: Number n = list.get(0) works, but list.add(5) fails - could be List<Double>, list.add(Integer) would break it
}
```

### Lower Bounded
```java
void write(List<? super Integer> list) { 
    // ? is Integer or any superclass - can WRITE but reading gives Object type
    // Example: list.add(5) works (Integer is accepted by Integer and its supers), but Object o = list.get(0) (uncertain type)
}
```

---

## 9. Comparison Table

| Wildcard | Read | Write |
|--------|------|------|
| ? | Yes | No |
| ? extends T | Yes | No |
| ? super T | No | Yes |

---

## 10. Quick Revision Summary
- Generics give type safety
- `<T>` defines a type
- `extends` → upper bound
- `super` → lower bound
