## Chain of Responsibility Design Pattern

The Chain of Responsibility pattern allows an object to pass a request along a chain of handlers. Each handler decides either to process the request or to pass it to the next handler in the chain. This pattern decouples the sender of a request from its receivers, giving more flexibility in assigning responsibilities to objects.

### Key Points
- Avoids coupling the sender of a request to its receiver.
- Multiple objects can handle a request, but only one will.
- Each handler contains a reference to the next handler.


### Java Example: Purchase Approval Workflow

Let's say a company has a purchase approval process. Requests below ₹10,000 can be approved by a Manager, up to ₹50,000 by a Director, and above that by a Vice President. Each handler checks if it can approve the request, otherwise passes it to the next handler.

```java
abstract class Approver {
    protected Approver next;
    public void setNext(Approver next) {
        this.next = next;
    }
    public abstract void approveRequest(int amount);
}

class Manager extends Approver {
    public void approveRequest(int amount) {
        if (amount <= 10000) {
            System.out.println("Manager approved purchase of ₹" + amount);
        } else if (next != null) {
            next.approveRequest(amount);
        } else {
            System.out.println("Request of ₹" + amount + " cannot be approved");
        }
    }
}

class Director extends Approver {
    public void approveRequest(int amount) {
        if (amount <= 50000) {
            System.out.println("Director approved purchase of ₹" + amount);
        } else if (next != null) {
            next.approveRequest(amount);
        } else {
            System.out.println("Request of ₹" + amount + " cannot be approved");
        }
    }
}

class VicePresident extends Approver {
    public void approveRequest(int amount) {
        if (amount > 50000) {
            System.out.println("Vice President approved purchase of ₹" + amount);
        } else if (next != null) {
            next.approveRequest(amount);
        } else {
            System.out.println("Request of ₹" + amount + " cannot be approved");
        }
    }
}

// Usage
public class ChainDemo {
    public static void main(String[] args) {
        Approver manager = new Manager();
        Approver director = new Director();
        Approver vp = new VicePresident();

        manager.setNext(director);
        director.setNext(vp);

        int[] requests = {5000, 20000, 75000, 100000};
        for (int amount : requests) {
            manager.approveRequest(amount);
        }
    }
}
```

### Output
```
Manager approved purchase of ₹5000
Director approved purchase of ₹20000
Vice President approved purchase of ₹75000
Vice President approved purchase of ₹100000
```

### Summary
The Chain of Responsibility pattern is useful for scenarios like approval workflows, where each handler has its own responsibility and can pass requests it can't handle to the next handler. This keeps your code flexible and maintainable.
