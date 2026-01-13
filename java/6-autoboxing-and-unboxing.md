# Autoboxing vs Unboxing

## Table of Contents
1. Overview  
2. What is Autoboxing  
3. What is Unboxing  
4. Why Autoboxing & Unboxing Are Needed  
5. Wrapper Comparison & Integer Cache  
6. Performance Considerations  
7. Final Recap  

---

## 1. Overview
Autoboxing and Unboxing manage **conversion between primitives and wrapper objects**.

- Introduced in **Java 5**
- Enables primitives to work with object-based APIs
- Can introduce hidden performance and logic issues

---

## 2. What is Autoboxing
**Automatic conversion** from primitive → wrapper object.

```java
int a = 10;
Integer x = a; // autoboxing
```

### Key Points
- Primitive converted to object
- Required where **objects are mandatory**
- Wrapper may be reused from cache

---

## 3. What is Unboxing
**Automatic conversion** from wrapper → primitive.

```java
Integer x = 20;
int a = x; // unboxing
```

### Key Points
- Object converted to primitive
- Can throw `NullPointerException`
- Happens implicitly

---

## 4. Why Autoboxing & Unboxing Are Needed

### 4.1 Collections Work Only with Objects
Primitives **cannot** be stored in collections.

```java
List<Integer> list = new ArrayList<>();
list.add(10); // autoboxing
```

> Collections require wrapper classes

---

### 4.2 Method Parameters & APIs Expect Objects
Frameworks, generics, and utilities work with objects.

```java
void process(Integer x) { }

process(5); // autoboxing
```

---

### 4.3 Mutability vs Pass-by-Value (Important Clarification)
Java is **always pass-by-value**.

#### Primitive Case
```java
void change(int x) {
    x = 10;
}

int a = 5;
change(a); // a = 5
```

#### Wrapper Case
```java
void change(Integer x) {
    x = 10;
}

Integer a = 5;
change(a); // a = 5
```

> Autoboxing does **NOT** allow changing caller values  
> Wrapper classes are **immutable**

---

### 4.4 When Change Is Possible
Change is possible only when:
- Object is **mutable**
- Internal state is modified

```java
void update(List<Integer> list) {
    list.add(10);
}
```

---

## 5. Wrapper Comparison & Integer Cache

### 5.1 Integer Cache
Java caches `Integer` objects in range:

> **-128 to 127**

```java
Integer a = 100;
Integer b = 100;

a == b // true
```

```java
Integer x = 200;
Integer y = 200;

x == y // false
```

### Why Cache Exists
- Improves performance
- Avoids repeated object creation
- Applied for small, frequently used numbers

---

### 5.2 Correct Way to Compare Wrappers

#### Wrong
```java
Integer a = 200;
Integer b = 200;

a == b // false
```

#### Correct
```java
a.equals(b) // true
```

### Rules
- `==` → reference comparison
- `equals()` → value comparison
- Cache affects only `==`

---

## 6. Performance Considerations
- Autoboxing creates objects
- More GC overhead
- Risk of NPE during unboxing

```java
// Avoid
for (Integer i = 0; i < 1000; i++) { }
```

```java
// Prefer
for (int i = 0; i < 1000; i++) { }
```

---

## 7. Final Recap
- Autoboxing: primitive → wrapper
- Unboxing: wrapper → primitive
- Needed for collections and object-based APIs
- Does NOT bypass pass-by-value
- Wrapper cache affects `==`
- Use `equals()` for comparison
- Prefer primitives for performance

---
