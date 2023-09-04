package Container;

/**
 * The Container class serves as a base class for different types of containers.
 * It contains common properties like id, weight, type, and fuel consumption details.
 */
public abstract class Container {

    // Attributes
    protected String id;
    protected String type;
    protected double weight;
    protected double fuelConsumptionPerKmForShip;
    protected double fuelConsumptionPerKmForTruck;

    /**
     * Constructs a new Container object.
     *
     * @param id The unique ID of the container.
     * @param type The type of the container.
     * @param weight The weight of the container.
     * @param fuelConsumptionPerKmForShip Fuel consumption per km for ships.
     * @param fuelConsumptionPerKmForTruck Fuel consumption per km for trucks.
     */
    public Container(String id, String type, double weight, double fuelConsumptionPerKmForShip, double fuelConsumptionPerKmForTruck) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.fuelConsumptionPerKmForShip = fuelConsumptionPerKmForShip;
        this.fuelConsumptionPerKmForTruck = fuelConsumptionPerKmForTruck;
    }

    // Getter methods

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public double getFuelConsumptionPerKmForShip() {
        return fuelConsumptionPerKmForShip;
    }

    public double getFuelConsumptionPerKmForTruck() {
        return fuelConsumptionPerKmForTruck;
    }

    // Setter methods

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setFuelConsumptionPerKmForShip(double fuelConsumptionPerKmForShip) {
        this.fuelConsumptionPerKmForShip = fuelConsumptionPerKmForShip;
    }

    public void setFuelConsumptionPerKmForTruck(double fuelConsumptionPerKmForTruck) {
        this.fuelConsumptionPerKmForTruck = fuelConsumptionPerKmForTruck;
    }
}
