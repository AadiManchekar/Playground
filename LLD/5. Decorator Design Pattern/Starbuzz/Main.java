package Starbuzz;

import Starbuzz.addOns.Milk;
import Starbuzz.addOns.Mocha;

public class Main {
    public static void main(String[] args) {

        // Creating a beverage of type DarkRoast with double Mocha and Milk
        Beverage beverage = new Mocha(new Mocha(new Milk(new DarkRoast())));
        System.out.println(beverage.getDescription() + " ---> $" + beverage.cost());
    }
}
