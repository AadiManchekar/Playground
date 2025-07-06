// Product

package pizza;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;

    // some common behaviours
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
    }

    public void bake() {
        System.out.println("Bake for 25 mins at 350");
    }
    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Placing the pizza into a box");
    }

    // getter for name
    public String getName() {
        return name;
    }
}