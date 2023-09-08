package models.container;

import java.io.Serializable;

public abstract class Container implements Serializable {
    private String containerId;
    private double weight;

    // Constructor
    public Container(String containerId, double weight) {
        this.containerId = containerId;
        this.weight = weight;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Abstract methods to be implemented by each container type.
    public abstract double getFuelConsumptionPerKmForShip();
    public abstract double getFuelConsumptionPerKmForTruck();
}
