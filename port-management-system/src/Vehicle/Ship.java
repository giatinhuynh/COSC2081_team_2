package Vehicle;

import Port.IPort;

public class Ship extends Vehicle {
    public Ship(String id, String name, double carryingCapacity, double fuelCapacity) {
        super(id, name, carryingCapacity, fuelCapacity);
    }
}
