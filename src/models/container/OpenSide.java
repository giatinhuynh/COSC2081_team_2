package models.container;

public class OpenSide extends Container {
    public OpenSide(String containerId, double weight) {
        super(containerId, weight);
    }

    @Override
    public String getContainerType() {
        return "Open Side";
    }

    @Override
    public double getFuelConsumptionPerKmForShip() {
        return 2.7;
    }

    @Override
    public double getFuelConsumptionPerKmForTruck() {
        return 3.2;
    }
}
