import Strategy.SportsDriveStrategy;
import WithoutStrategyDesignPattern.Vehicle;


public class OffRoadvehicle extends Vehicle {
    
    OffRoadvehicle() {
        super(new SportsDriveStrategy());
    }

}
