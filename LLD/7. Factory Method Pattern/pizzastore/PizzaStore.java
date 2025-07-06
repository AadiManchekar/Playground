// The Creator class

package pizzastore;

import pizza.Pizza;

public abstract class PizzaStore {
 
	// Factory Method: defines abstract factory method that subclasses implement to create
	// specific products (Pizzas)
	abstract Pizza createPizza(String item);
 
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}