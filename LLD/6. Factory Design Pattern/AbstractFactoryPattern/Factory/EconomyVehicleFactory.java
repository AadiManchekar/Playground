public class EconomyVehicleFactory implements VehicleFactory{
    @Override
    public Vehicle getVehicle(String input) {
        switch (input) {
            case "Zen":
                return new Zen();
            case "Nano":
                return new Nano();
            default:
                return null;
        }
    }
}
