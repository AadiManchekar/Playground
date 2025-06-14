package WithStrategyDesignPattern;

import WithStrategyDesignPattern.Strategy.SportsDriveStrategy;

public class OffRoadvehicle extends Vehicle {
    // Avoids code duplication as we are injecting the concrete class that we really need
    OffRoadvehicle() {
        super(new SportsDriveStrategy());
    }
}
