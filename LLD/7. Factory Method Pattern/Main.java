import pizza.Pizza;
import pizzastore.ChicagoPizzaStore;
import pizzastore.NYPizzaStore;
import pizzastore.PizzaStore;

public class Main {
    public static void main(String[] args) {
        // Create a New York Pizza Store
        PizzaStore nyPizzaStore = new NYPizzaStore();
        // Order a New York Style Cheese Pizza
        Pizza nyCheesePizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ordered a " + nyCheesePizza.getName() + "\n");

        // Create a Chicago Pizza Store
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        // Order a Chicago Style Veggie Pizza
        Pizza chicagoVeggiePizza = chicagoPizzaStore.orderPizza("veggie");
        System.out.println("Ordered a " + chicagoVeggiePizza.getName() + "\n");
    }
}
