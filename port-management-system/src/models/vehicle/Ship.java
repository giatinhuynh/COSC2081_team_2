package models.vehicle;

import models.container.Container;

public class Ship extends Vehicle {
    public Ship(String vehicleId, String name, double currentFuel, double carryingCapacity, double fuelCapacity) {
        super(vehicleId, name, currentFuel, carryingCapacity, fuelCapacity);
    }

    @Override
    public boolean canCarry(Container container) {
        return true; // Ship can carry all types of containers
    }
}
