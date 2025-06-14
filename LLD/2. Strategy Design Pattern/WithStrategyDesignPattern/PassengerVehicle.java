package WithStrategyDesignPattern;

import WithStrategyDesignPattern.Strategy.NormalDriveStrategy;

public class PassengerVehicle extends Vehicle{
    PassengerVehicle(){
        super(new NormalDriveStrategy());
    }
}
