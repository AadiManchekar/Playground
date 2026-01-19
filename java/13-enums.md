# Java Enums

## Table of Contents
1. What Is an Enum  
2. Why Enums Exist  
3. Basic Enum Example  
4. Enums with Custom Values  
5. Enum Constructors (Why They Are Private)  
6. Common Methods in Enums  
7. Enum-Specific Method Override  
8. Default Methods from java.lang.Enum  
9. Abstract Methods in Enums  
10. Enums and Interfaces (Real Example)  
11. Why Enums Cannot Be Instantiated  
12. Why Enums Are Better Than static final Constants  
13. Enum Internals  
14. Common Pitfalls  
15. Quick Revision Summary  

---

## 1. What Is an Enum
An enum is a special Java type used to represent a **fixed set of constants**.

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY
}
```

Behind the scenes:
- Enum is a class
- Each constant is an object
- Enum implicitly extends `java.lang.Enum`

---

## 2. Why Enums Exist
Enums provide:
- Type safety
- Readability
- Compile-time checking
- Controlled value set

Avoids this:
```java
String status = "ACTIVE"; // error-prone
```

Prefer:
```java
Status.ACTIVE
```

---

## 3. Basic Enum Example

```java
enum Status {
    ACTIVE,
    INACTIVE,
    BLOCKED
}
```

---

## 4. Enums with Custom Values

Each enum constant gets its own **instance fields and methods**.

```java
enum Status {
    ACTIVE(1),
    INACTIVE(0),
    BLOCKED(-1);

    private final int code;  // Each constant has its own 'code' value

    Status(int code) {
        this.code = code;
    }

    public int getCode() {   // Each constant can call this method
        return code;
    }
}
```

Usage:
```java
Status.ACTIVE.getCode();    // 1
Status.INACTIVE.getCode();  // 0
Status.BLOCKED.getCode();   // -1
```

---

## 5. Enum Constructors (Always Private)

Important rules:
- Enum constructors are **implicitly private**
- You cannot create enum objects manually
- JVM controls enum instantiation

```java
enum Test {
    A;

    private Test() {}
}
```

❌ This is illegal:
```java
new Test(); // compile-time error
```

---

## 6. Common Methods in Enums

```java
enum Color {
    RED, BLUE;

    public boolean isRed() {
        return this == RED;
    }
}
```

Enums can contain:
- Fields
- Methods
- Constructors
- Abstract methods

---

## 7. Enum-Specific Method Override

Enums can have normal methods. Specific constants can override them if needed.

### Basic Method
```java
enum WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public boolean isWeekend() {
        return false;  // Default: not a weekend
    }
}
```

Usage:
```java
WeekDay.MONDAY.isWeekend();     // false
WeekDay.FRIDAY.isWeekend();     // false
```

### Specific Enum Constants Override the Method

```java
enum WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY {
        @Override
        public boolean isWeekend() {
            return true;  // SATURDAY overrides
        }
    },
    SUNDAY {
        @Override
        public boolean isWeekend() {
            return true;  // SUNDAY overrides
        }
    };

    public boolean isWeekend() {
        return false;  // Default implementation
    }
}
```

Usage:
```java
WeekDay.MONDAY.isWeekend();     // false
WeekDay.FRIDAY.isWeekend();     // false
WeekDay.SATURDAY.isWeekend();   // true (overridden)
WeekDay.SUNDAY.isWeekend();     // true (overridden)
```

---

## 8. Default Methods from java.lang.Enum

Every enum automatically inherits methods from `java.lang.Enum`:

```java
enum Color {
    RED, BLUE, GREEN;
}
```

### Common Inherited Methods

**1. name() - Get the constant name**

Returns the **enum constant name itself**, NOT custom values.

```java
Color.RED.name();        // "RED"
Color.BLUE.name();       // "BLUE"
```

**Important:** Even with custom values, `name()` returns the constant name:
```java
enum Color {
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green");

    private final String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
```

Usage:
```java
Color.RED.name();              // "RED" (constant name)
Color.RED.getDisplayName();    // "Red" (custom value)

Color.BLUE.name();             // "BLUE" (constant name)
Color.BLUE.getDisplayName();   // "Blue" (custom value)
```

**2. ordinal() - Get the position (0-indexed)**
```java
Color.RED.ordinal();     // 0
Color.BLUE.ordinal();    // 1
Color.GREEN.ordinal();   // 2
```

**3. values() - Get all enum constants**
```java
Color[] allColors = Color.values();
// [RED, BLUE, GREEN]

for (Color c : Color.values()) {
    System.out.println(c);
}
```

**4. valueOf(String) - Get enum by name**
```java
Color c = Color.valueOf("RED");     // Color.RED
Color c = Color.valueOf("BLUE");    // Color.BLUE
```

**5. compareTo() - Compare ordinal positions**
```java
Color.RED.compareTo(Color.BLUE);     // -1 (RED comes before BLUE)
Color.BLUE.compareTo(Color.RED);     // 1
Color.RED.compareTo(Color.RED);      // 0
```

**6. equals() and hashCode() - Identity based comparison**
```java
Color.RED.equals(Color.RED);         // true
Color.RED.equals(Color.BLUE);        // false

Map<Color, String> map = new HashMap<>();
map.put(Color.RED, "red color");    // Works as key
```

---

## 9. Abstract Methods in Enums

Enums can declare abstract methods.

```java
enum Shape {
    CIRCLE {
        double area(double r) {
            return Math.PI * r * r;
        }
    },
    SQUARE {
        double area(double s) {
            return s * s;
        }
    };

    abstract double area(double value);
}
```

Each enum constant must implement it.

---

## 10. Enums and Interfaces (Real Example)

### Interface
```java
interface CaseConverter {
    String toLowerCase();
}
```

### Enum Implementing Interface
```java
enum WeekDay implements CaseConverter {
    MONDAY, TUESDAY, WEDNESDAY;

    @Override
    public String toLowerCase() {
        return name().toLowerCase();
    }
}
```

Usage:
```java
WeekDay.MONDAY.toLowerCase(); // monday
```

---

## 11. Why Enums Cannot Be Instantiated

```java
new Status(); // ❌ Not allowed
```

Reason:
- Enum instances are created by JVM
- Fixed number of constants
- Prevents breaking singleton behavior
- Guarantees uniqueness

---

## 12. Why Enums Are Better Than static final Constants

### static final approach (bad)
```java
class Status {
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 2;
}
```

Problems:
- No type safety
- Invalid values allowed
- Hard to extend
- No behavior

### Enum approach (good)
```java
enum Status {
    ACTIVE, INACTIVE;
}
```

Benefits:
- Type safe
- Readable
- Supports methods
- Prevents invalid values
- Works with switch

---

## 13. Enum Internals (Behind the Scenes)

```java
enum Color {
    RED, BLUE;
}
```

Compiled as:
```java
final class Color extends Enum<Color> {
    public static final Color RED = new Color("RED", 0);
    public static final Color BLUE = new Color("BLUE", 1);
}
```

---

## 14. Common Pitfalls
- Using ordinal() for persistence
- Trying to extend classes
- Adding public constructors
- Treating enums as constants only
- Forgetting enums can have behavior

---

## 15. Quick Revision Summary
- Enum is a class
- Extends `java.lang.Enum`
- Cannot extend other classes
- Constructors are private
- Supports methods & behavior
- Can implement interfaces
- Better than static final constants
- Safe, readable, powerful

> Rule of thumb:  
> **If values are fixed and behavior is related, use enum.**
