package WithoutStrategyDesignPattern;

public class OffRoadvehicle extends Vehicle{
    // Code is duplicated as another child class SportsVehicle also has same drive class
    @Override
    public void drive() {
        System.out.println("Sports drive capability");
    }
}
