# Java Access Modifiers

## Table of Contents
1. Overview  
2. Types of Access Modifiers  
3. Access Modifiers for Classes  
4. Access Modifiers for Methods  
5. Access Modifiers for Variables  
6. Package-Level Access Explained  
7. Access Matrix  
8. Common Rules & Pitfalls  
9. Final Recap  

---

## 1. Overview
Access modifiers control **visibility and accessibility** of classes, methods, and variables.

- Enforce encapsulation
- Improve security
- Define API boundaries

---

## 2. Types of Access Modifiers
Java provides **four** access levels:

- `public`
- `protected`
- *(default)* – no keyword
- `private`

---

## 3. Access Modifiers for Classes

### Top-Level Classes
Only **two** modifiers allowed:

- `public`
- *(default)*

```java
public class MyClass { }
class PackageClass { }
```

### Rules
- `private` and `protected` **not allowed** for top-level classes
- One `public` class per file
- File name must match public class name

---

## 4. Access Modifiers for Methods

```java
public void m1() { }
protected void m2() { }
void m3() { }          // default
private void m4() { }
```

### Visibility
- `public` → accessible everywhere
- `protected` → same package + subclasses
- default → same package only
- `private` → same class only

---

## 5. Access Modifiers for Variables (Data Members)

```java
public int a;
protected int b;
int c;        // default
private int d;
```

### Key Points
- Same rules as methods
- Used heavily for encapsulation
- Usually kept `private` with getters/setters

```java
private int age;

public int getAge() {
    return age;
}
```

---

## 6. Package-Level Access (Default)
When **no modifier** is specified:

- Accessible within same package only
- Not accessible outside package

```java
class Helper { }
```

> Often called *package-private*

---

## 7. Access Matrix

| Modifier   | Same Class | Same Package | Subclass (diff pkg) | Outside |
|-----------|------------|--------------|---------------------|---------|
| public    | ✔          | ✔            | ✔                   | ✔       |
| protected | ✔          | ✔            | ✔                   | ✘       |
| default   | ✔          | ✔            | ✘                   | ✘       |
| private   | ✔          | ✘            | ✘                   | ✘       |

---

## 8. Common Rules & Pitfalls

- Overridden method **cannot reduce visibility**
```java
// INVALID
protected void run() { }
```

- Interface methods are **implicitly public**
- Interface variables are **public static final**
- Constructors can also have access modifiers
- `protected` ≠ package-only (subclasses outside package allowed)

---

## 9. Final Recap
- Access modifiers define visibility
- Classes: only `public` or default
- Methods & variables: all four allowed
- Prefer `private` for fields
- Use `public` APIs intentionally

---
