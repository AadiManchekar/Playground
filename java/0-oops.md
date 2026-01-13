# Difference Between Procedural and Object-Oriented Programming

| Feature                  | Procedural Programming       | Object-Oriented Programming |
|--------------------------|------------------------------|-----------------------------|
| Basic Unit               | Programs are organized around functions. | Programs are organized around objects that combine data and behavior. |
| Approach                 | Uses a linear, step-by-step approach. | Uses a modular, object-based approach. |
| Data and Methods         | Data and functions are independent. | Data and methods are bundled together in objects. |
| Code Reusability         | Functions can be reused, but reusability is limited. | Promotes high reusability through inheritance and polymorphism. |
| Scalability              | Less suitable for large, complex systems. | Well-suited for building scalable and maintainable systems. |
| Examples of Languages    | C, Pascal, Fortran.          | Java, Python, C++.          |

> **Note:**
> - **Simplicity and Directness:** For small, simple programs or scripts, the straightforward, step-by-step nature of procedural programming is easier to write, understand, and debug than the overhead of managing classes and objects in OOP.
> - **Lower Overhead:** Procedural languages often have lower overhead compared to some object-oriented counterparts because they do not require the extra processing time associated with object management, such as dynamic dispatch (deciding which method to run at runtime).
> - **Performance and Control:** This makes them suitable for system-level programming, embedded systems, and performance-critical applications.
> - **Paradigm Choice:** In essence, neither paradigm is universally "better" than the other; rather, they are tools suited for different jobs. OOP excels at managing complexity in large, evolving software systems, while procedural programming excels at simplicity, direct control, and performance where an object model is unnecessary.

---

## 4 Pillars of Object-Oriented Programming (OOP)

1. **Encapsulation**
   - Bundling data (variables) and methods (functions) that operate on the data into a single unit called an object. 
   - It restricts direct access to some of the object's components, which helps protect the integrity of the data.

2. **Abstraction**
   - Hiding complex implementation details and showing only the necessary features of an object. This simplifies the interface and reduces complexity for the user.
   - Example: User pressed break and the car's speed is reduce, we dont care how the speed is reduced (like break called breaking-system which applied breaks on all 4 wheels). We dont care about how the break is applied, all we care is pressing break, should lower the speed. So implementation details of break is abstracted away from user
   - Achieved via interfaces and abstract class in java

3. **Inheritance**
   - Mechanism by which one class (child/subclass) can inherit properties and behaviors (methods) from another class (parent/superclass). This promotes code reuse and establishes a relationship between classes.

4. **Polymorphism**
   - The ability of different objects to respond to the same method call in different ways. It allows one interface to be used for a general class of actions, making code more flexible and extensible.

