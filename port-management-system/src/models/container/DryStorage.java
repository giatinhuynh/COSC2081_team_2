package models.container;

public class DryStorage extends Container {
    public DryStorage(String containerId, double weight) {
        super(containerId, weight);
    }

    @Override
    public String getContainerType() {
        return "Dry Storage";
    }

    @Override
    public double getFuelConsumptionPerKmForShip() {
        return 3.5;
    }

    @Override
    public double getFuelConsumptionPerKmForTruck() {
        return 4.6;
    }
}
