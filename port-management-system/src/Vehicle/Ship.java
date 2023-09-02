package Vehicle;

import Port.IPort;

public class Ship extends Vehicle {
    public Ship(String id, String name, double currentFuel, double carryingCapacity, double fuelCapacity, IPort currentPort) {
        super(id, name, currentFuel, carryingCapacity, fuelCapacity, currentPort);
    }
}
