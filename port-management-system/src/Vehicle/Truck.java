package Vehicle;

import Port.IPort;
import Container.Container;

enum TruckType {
    // Placeholder enum
    BASIC, REEFER, TANKER
}

public class Truck extends Vehicle {
    private TruckType truckType;

    public Truck(String id, String name, double currentFuel, double carryingCapacity, double fuelCapacity, IPort currentPort, TruckType truckType) {
        super(id, name, currentFuel, carryingCapacity, fuelCapacity, currentPort);
        this.truckType = truckType;
    }

    //Need to implement container type for checking
    @Override
    public boolean canLoadContainer(Container container) {
        // Check if truck can load the container type
        if (truckType == TruckType.BASIC) {
            // Basic trucks can carry dry storage, open top, and open side containers
            return container.getType().equals("Dry Storage") || container.getType().equals("Open Top") || container.getType().equals("Open Side");
        } else if (truckType == TruckType.REEFER) {
            // Reefer trucks can carry refrigerated containers
            return container.getType().equals("Refrigerated");
        } else if (truckType == TruckType.TANKER) {
            // Tanker trucks can carry liquid containers
            return container.getType().equals("Liquid");
        }
        return false;
    }
}
