package Starbuzz;

public class Escpresso extends Beverage {
    public Escpresso() {
        description = "Espresso Coffee";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
