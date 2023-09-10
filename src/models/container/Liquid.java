package models.container;

public class Liquid extends Container {
    public Liquid(String containerId, double weight) {
        super(containerId, weight);
    }

    @Override
    public String getContainerType() {
        return "Liquid";
    }

    @Override
    public double getFuelConsumptionPerKmForShip() {
        return 4.8;
    }

    @Override
    public double getFuelConsumptionPerKmForTruck() {
        return 5.3;
    }
}
