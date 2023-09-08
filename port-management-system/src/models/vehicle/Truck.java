package models.vehicle;

import models.container.*;

public class Truck extends Vehicle {
    public enum TruckType {
        BASIC, REEFER, TANKER
    }

    private final TruckType type;

    public Truck(String vehicleId, String name, double currentFuel, double carryingCapacity, double fuelCapacity, TruckType type) {
        super(vehicleId, name, currentFuel, carryingCapacity, fuelCapacity);
        this.type = type;
    }

    @Override
    public boolean canCarry(Container container) {
        return switch (type) {
            case BASIC ->
                    container instanceof DryStorage || container instanceof OpenTop || container instanceof OpenSide;
            case REEFER -> container instanceof Refrigerated;
            case TANKER -> container instanceof Liquid;
            default -> false;
        };
    }
}