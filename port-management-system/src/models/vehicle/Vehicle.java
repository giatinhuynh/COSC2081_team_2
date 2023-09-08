package models.vehicle;

import models.container.Container;

import java.io.Serializable;
import java.util.List;

public abstract class Vehicle implements Serializable {
    protected String vehicleId;
    protected String name;
    protected double currentFuel;
    protected double carryingCapacity;
    protected double fuelCapacity;
    protected String currentPort; // Can be null if sailaway
    protected List<Container> containers; // List of containers currently on the vehicle

    // Constructor
    public Vehicle(String vehicleId, String name, double currentFuel, double carryingCapacity, double fuelCapacity) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
    }

    public abstract boolean canCarry(Container container);

    public double calculateFuelForWeight(double weight) {
        // To be implemented: Calculation for fuel based on weight
        return 0;
    }

    public boolean canMoveToPort(double distance) {
        // To be implemented: Check if vehicle has enough fuel to cover the distance
        return true;
    }

    public void refuel() {
        // Refill the vehicle's fuel to its max capacity
        this.currentFuel = this.fuelCapacity;
    }

    // Setters and getters
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleID) {
        this.vehicleId = vehicleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }
}



