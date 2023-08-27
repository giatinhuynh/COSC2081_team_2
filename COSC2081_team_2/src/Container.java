public class Container {
    private String id;
    private double weight;
    private ContainerType type;

    public Container(String id, double weight, ContainerType type) {
        this.id = id;
        this.weight = weight;
        this.type = type;
    }

    public double fuelConsumptionPerKm(VehicleType vehicleType) {
        return switch (type) {
            case DRY_STORAGE -> vehicleType == VehicleType.SHIP ? 3.5 * weight : 4.6 * weight;
            case OPEN_TOP -> vehicleType == VehicleType.SHIP ? 2.8 * weight : 3.2 * weight;
            case OPEN_SIDE -> vehicleType == VehicleType.SHIP ? 2.7 * weight : 3.2 * weight;
            case REFRIGERATED -> vehicleType == VehicleType.SHIP ? 4.5 * weight : 5.4 * weight;
            case LIQUID -> vehicleType == VehicleType.SHIP ? 4.8 * weight : 5.3 * weight;
        };
    }

    // Getter and Setter methods for id, weight, and type
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }

    // You can add other methods if necessary

    public static void main(String[] args) {
        Container container1 = new Container("c-123", 100, ContainerType.DRY_STORAGE);
        System.out.println("Fuel consumption for ship: " + container1.fuelConsumptionPerKm(VehicleType.SHIP));
        System.out.println("Fuel consumption for truck: " + container1.fuelConsumptionPerKm(VehicleType.TRUCK));
    }
}
