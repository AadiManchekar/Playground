package Starbuzz.addOns;

import Starbuzz.Beverage;

public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;
    public abstract String getDescription();
}
