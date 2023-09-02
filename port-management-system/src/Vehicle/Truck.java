package Vehicle;

enum TruckType {
    // Placeholder enum
}

public class Truck extends Vehicle {
    private TruckType truckType;

    public Truck() {
    }

    public Truck(TruckType truckType) {
        this.truckType = truckType;
    }
}
