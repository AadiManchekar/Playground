public class Main {
    public static void main(String[] args) {
        LuxuryVehicleFactory luxuryVehicleFactory = new LuxuryVehicleFactory();
        EconomyVehicleFactory economyVehicleFactory = new EconomyVehicleFactory();

        Vehicle mercedes = luxuryVehicleFactory.getVehicle("Mercedes");
        mercedes.name();

        Vehicle nano = economyVehicleFactory.getVehicle("Nano");
        nano.name();
    }
}
