## Constructor
- A **constructor** is a special method used to create and initialize objects.
- The **constructor name must be the same as the class name.**
- **Constructors do not have a return type**, not even `void`.
- **Constructors cannot be**:
  - `static`
  - `final`
  - `abstract`
  - `synchronized`
- **Constructors cannot be inherited**, which means they cannot be overridden.
- **Constructor Overloading** is allowed. You can create multiple constructors with different parameter lists.
- **Constructors can be private**, which is commonly used in Singleton patterns.
```Java
public class Employee {
    int empId;
    String name;

    // Private constructor
    private Employee() {}

    // Factory method to get an instance
    public static Employee getInstance() {
        return new Employee();
    }
}

// Usage
Employee emp = Employee.getInstance();
```

### new Keyword
- The `new` keyword in Java is used for creating objects, which are instances of classes.
- It starts the whole constructor chain reaction. All the constructors in an object’s inheritance tree must run when you make a new object.
- It plays a key role in allocating memory for the object and initializing it.
- Memory Allocation:
    - It reserves memory on the heap, where all Java objects reside, to store the object's data.
- Object Initialization:
    - It calls the constructor of the class.
- Returns a Reference:
    - The `new` keyword returns a reference to the newly created object, which is then typically assigned to a variable.
```Java
Car myCar = new Car();
```
- This creates a new Car object, allocates memory for it, calls the Car constructor, and assigns the reference to the myCar variable.

### Constructor chaining
- Constructor chaining using `this` keyword
	- Within the same class, you can chain constructors using this().
	- It helps in code reuse and simplifies initialization logic.
```Java
public class Employee {
    int empId;
    String name;

    Employee() {
        this(10); // Calls Employee(int empId)
    }

    Employee(int empId) {
        this(empId, "Aadi"); // Calls Employee(int empId, String name)
    }

    Employee(int empId, String name) {
        this.empId = empId;
        this.name = name;
    }
}
```
- Constructor chaining using `super` keyword
	- When a class extends another class, you can chain constructors using super().
	- super() is used to call the parent class constructor.
	- If you don’t explicitly call super(), the compiler automatically inserts a call to the default (no-argument) constructor of the parent class.
	- **Constructor Chaining Order:** Always starts from the topmost parent class and flows down to the child classes.
```Java
public class Person {
    Person() {
        System.out.println("In Person class...");
    }
}

public class Employee extends Person {
    Employee() {
        // super(); // Implicitly called if not specified
        System.out.println("In Employee class...");
    }
}

// Usage
Employee emp = new Employee();

/*
Output:
In Person class...
In Employee class...
*/
```
- If the parent class constructor is parameterized, you must explicitly call super(arguments) in the child class constructor.
```Java
public class Person {
    int age;

    Person(int age) {
        this.age = age;
        System.out.println("In Person class...");
    }
}

public class Employee extends Person {
    String name;

    Employee(int age, String name) {
        super(age); // Must be called explicitly
        this.name = name;
        System.out.println("In Employee class...");
    }
}

// Usage
Employee emp = new Employee(23, "Aadi Manchekar");

/*
Output:
In Person class...
In Employee class...
*/
```

### Copy constructor 
- Copy Constructor is the constructor used when we want to initialize the value to the new object from the old object of the same class. 
```Java
public class Employee {
    int age;
    String name;

    Employee(Employee emp) {
        this.age = emp.age;
        this.name = emp.name;
    }
}
```