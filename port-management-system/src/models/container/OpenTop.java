package models.container;

public class OpenTop extends Container {
    public OpenTop(String containerId, double weight) {
        super(containerId, weight);
    }

    @Override
    public double getFuelConsumptionPerKmForShip() {
        return 2.8;
    }

    @Override
    public double getFuelConsumptionPerKmForTruck() {
        return 3.2;
    }
}
