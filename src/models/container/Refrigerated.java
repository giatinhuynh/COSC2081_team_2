package models.container;

public class Refrigerated extends Container {
    public Refrigerated(String containerId, double weight) {
        super(containerId, weight);
    }

    @Override
    public String getContainerType() {
        return "Refrigerated";
    }

    @Override
    public double getFuelConsumptionPerKmForShip() {
        return 4.5;
    }

    @Override
    public double getFuelConsumptionPerKmForTruck() {
        return 5.4;
    }
}
