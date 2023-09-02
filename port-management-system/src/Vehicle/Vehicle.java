package Vehicle;

import Port.IPort;
import Container.Container;
import java.util.List;

public class Vehicle implements IVehicle{
    private String id;
    private String name;
    private double currentFuel;
    private double carryingCapacity;
    private double fuelCapacity;
    private IPort currentPort;
    private List<Container> containers;

    public Vehicle() {
    }

    public Vehicle(String id, String name, double currentFuel, double carryingCapacity, double fuelCapacity, IPort currentPort) {
        this.id = id;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
    }

    @Override
    public void loadContainer(Container container) {
    }

    @Override
    public void unloadContainer(Container container) {
    }

    @Override
    public void moveTo(IPort port) {
    }

    @Override
    public void refuel() {
    }

    @Override
    public boolean canMoveTo(IPort port) {
        return true; // Placeholder return value
    }
}
