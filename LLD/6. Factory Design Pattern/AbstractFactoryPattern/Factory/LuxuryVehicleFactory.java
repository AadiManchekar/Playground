public class LuxuryVehicleFactory implements VehicleFactory{

    @Override
    public Vehicle getVehicle(String input) {
        switch (input) {
            case "Mercedes":
                return new Mercedes();
            case "RangeRover":
                return new RangeRover();
            default:
                return null;
        }
    }
    
}
