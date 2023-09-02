package Vehicle;

import Port.IPort;
import Container.Container;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements IVehicle{
    private String id;
    private String name;
    private double currentFuel;
    private double carryingCapacity;
    private double fuelCapacity;
    private IPort currentPort;
    private List<Container> containers;
    private int totalContainers;

    public Vehicle() {
    }

    public Vehicle(String id, String name, double currentFuel, double carryingCapacity, double fuelCapacity, IPort currentPort) {
        this.id = id;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
        this.containers = new ArrayList<>();
        this.totalContainers = 0;
    }

    @Override
    public void loadContainer(Container container) {
        if (canLoadContainer(container)) {
            containers.add(container);
            totalContainers++;
        }
    }

    @Override
    public void unloadContainer(Container container) {
        if (containers.contains(container)) {
            containers.remove(container);
            totalContainers--;
        }
    }

    @Override
    public void moveTo(IPort port) {
        if (canMoveTo(port)) {
            currentPort = port;
        }
    }

    @Override
    public void refuel() {
        currentFuel = fuelCapacity;
    }

    @Override
    public boolean canMoveTo(IPort port) {
        // Vehicle needs enough fuel to move to another port
        return true;
    }

    public boolean canLoadContainer(Container container) {
        // Ship can load any container, while truck can only load some containers
        // Truck can only load to "landing" ports
        return true;
    }
}
