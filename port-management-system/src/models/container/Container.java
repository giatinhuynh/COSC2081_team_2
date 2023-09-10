package models.container;

import models.vehicle.Vehicle;
import models.port.Port;

import java.io.Serializable;

public abstract class Container implements Serializable {
    private String containerId;
    private double weight;
    private Vehicle currentVehicle;
    private Port currentPort;

    // Constructor
    public Container(String containerId, double weight) {
        this.containerId = containerId;
        this.weight = weight;
    }

    public Container(String containerId, double weight, Vehicle currentVehicle) {
        this.containerId = containerId;
        this.weight = weight;
        this.currentVehicle = currentVehicle;
    }

    public Container(String containerId, double weight, Port currentPort) {
        this.containerId = containerId;
        this.weight = weight;
        this.currentPort = currentPort;
    }

    // Getters and setters
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

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    // Abstract methods to be implemented by each container type.
    public abstract String getContainerType();
    public abstract double getFuelConsumptionPerKmForShip();
    public abstract double getFuelConsumptionPerKmForTruck();
}
