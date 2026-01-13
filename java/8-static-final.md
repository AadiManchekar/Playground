# static, final & static final

## Table of Contents
1. Overview  
2. `static` Keyword  
3. `final` Keyword  
4. `static final` Combination  
5. Usage for Classes  
6. Usage for Methods  
7. Usage for Variables (Data Members)  
8. Common Rules & Pitfalls  
9. Final Recap  

---

## 1. Overview
`static`, `final`, and `static final` control **lifecycle, mutability, and scope** of classes, methods, and variables.

- `static` → associated to class
- `final` → cannot change
- `static final` → constant

---

## 2. `static` Keyword

### Meaning
- Belongs to **class**, not object
- Single copy shared across all instances

---

## 3. `final` Keyword

### Meaning
- Prevents **modification**
- Behavior depends on usage target

---

## 4. `static final` Combination

### Meaning
- Class-level constant
- Initialized once
- Cannot be changed

```java
static final int MAX = 100;
```

---

## 5. Usage for Classes

### `static` with Classes
- **Not allowed** for top-level classes
- Allowed for **inner (nested) classes**

```java
class Outer {
    static class Inner { }
}
```

### `final` with Classes
- Class **cannot be inherited**

```java
final class Utility { }
```

### `static final` with Classes
- ❌ Not applicable

---

## 6. Usage for Methods

### `static` Methods
- Can be called without object
- Cannot access non-static members directly

```java
static void run() { }
```

### `final` Methods
- Cannot be overridden

```java
final void process() { }
```

### `static final` Methods
- Valid but redundant
- Static methods cannot be overridden anyway

```java
static final void log() { }
```

---

## 7. Usage for Variables (Data Members)

### `static` Variables
- Single shared variable
- Stored in method area

```java
static int count;
```

---

### `final` Variables
- Value cannot change after assignment

```java
final int x = 10;
```

- Must be initialized:
  - At declaration
  - In constructor
  - In initializer block

---

### `static final` Variables (Constants)
- Class-level constants
- Convention: UPPER_CASE

```java
static final double PI = 3.14;
```

---

## 8. Common Rules & Pitfalls

- `final` reference ≠ immutable object
```java
final List<Integer> list = new ArrayList<>();
list.add(1); // allowed
```

- Static methods:
  - Cannot use `this` or `super`
  - Are **hidden**, not overridden

- Final variables:
  - Must be initialized exactly once

- Interface fields:
  - Implicitly `public static final`

---

## 9. Final Recap
- `static` → shared, class-level
- `final` → cannot change / extend / override
- `static final` → constants
- Classes: `final` allowed, `static` only for inner classes
- Methods: `static` & `final` both allowed
- Variables: `static final` preferred for constants

---
