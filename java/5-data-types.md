# Java Data Types

## Table of Contents
1. Overview  
2. Primitive Data Types  
3. Non-Primitive (Reference) Data Types  
4. Java is always Pass-by-Value  
5. Why Java Has No Pointers  
6. String Basics  
7. String Constant Pool (SCP)  
8. `==` vs `equals()`  
9. Final Recap  

---

## 1. Overview
Java data types define **what kind of data** a variable can store.

### Classification
- **Primitive data types** → store actual values
- **Non-Primitive/Reference data types** → store references to objects

---

## 2. Primitive Data Types
- Fixed size
- Store values directly
- High performance

| Type     | Size | Default | Example |
|----------|------|---------|---------|
| byte     | 1B   | 0       | `byte b = 10;` |
| short    | 2B   | 0       | `short s = 100;` |
| int      | 4B   | 0       | `int i = 1000;` |
| long     | 8B   | 0L      | `long l = 10_000L;` |
| float    | 4B   | 0.0f    | `float f = 10.5f;` |
| double   | 8B   | 0.0d    | `double d = 99.99;` |
| char     | 2B   | '\u0000' | `char c = 'A';` |
| boolean  | JVM  | false   | `boolean ok = true;` |

### Key Points
- Stored in **stack**
- Cannot be `null`
- No methods

---

## 3. Non-Primitive (Reference) Data Types
- Store reference to heap objects

### Examples
- String
- Array
- Class
- Interface
- Enum

```java
String name = "Aadi";
int[] nums = {1, 2, 3};
```

### Key Points
- Objects in **heap**
- Reference in stack
- Default value is `null`
- Can have methods

---

## 4. Java is always Pass-by-Value
Java always passes **copies**.

### Primitive
```java
void update(int x) {
    x = 10;
}

int a = 5;
update(a); // a = 5
```

### Object Reference
```java
void update(String s) {
    s = "Java";
}

String name = "Aadi";
update(name); // name = "Aadi"
```

> Reference copy is passed, not the object.

---

## 5. Why Java Has No Pointers
Problems in C/C++:
- Memory leaks
- Dangling pointers
- Unsafe memory access

### Java Solution
- Uses references
- No pointer arithmetic
- Automatic garbage collection

> Safer memory model

---

## 6. String Basics
- `String` is non-primitive
- Immutable by design

```java
String s = "Java";
s.concat(" World"); // no change
```

### Why Immutable
- Security
- Thread safety
- Hashcode caching
- SCP optimization

---

## 7. String Constant Pool (SCP)
- Special memory inside **heap**
- Stores string literals

```java
String a = "Java";
String b = "Java"; // same SCP object
```

```java
String c = new String("Java"); // new heap object
```

### Literal vs Object
- Literal → reused from SCP
- `new String()` → always new object

---

## 8. `==` vs `equals()`
```java
String a = "Java";
String b = "Java";
String c = new String("Java");
```

- `a == b` → true (same reference)
- `a == c` → false (different reference)
- `a.equals(c)` → true (same content)

> `==` → reference comparison  
> `equals()` → content comparison

---

## 9. Final Recap
- Primitives store values
- References store addresses
- Java is pass-by-value
- No pointers
- Strings are immutable and SCP-backed

---
