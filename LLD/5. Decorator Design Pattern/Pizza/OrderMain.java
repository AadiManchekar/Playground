package Pizza;

import Pizza.Toppings.ExtraCheese;
import Pizza.Toppings.Mushroom;

public class OrderMain {
    public static void main(String[] args) {
        // Lets order Farmhouse
        BasePizza pizza1 = new FarmHousePizza();
        System.out.println("pizza1 cost: " + pizza1.cost()); // Output: pizza1 cost: 200

        // Lets order Farmhouse + extra cheese
        BasePizza pizza2 = new ExtraCheese(new FarmHousePizza());
        System.out.println("pizza2 cost: " + pizza2.cost()); // Output: pizza2 cost: 210

        // Lets order Margherita + extra cheese + mushroom
        BasePizza pizza3 = new Mushroom(new ExtraCheese(new FarmHousePizza()));
        System.out.println("pizza3 cost: " + pizza3.cost()); // Output: pizza3 cost: 230
    }
}
