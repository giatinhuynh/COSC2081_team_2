package Vehicle;

import Port.IPort;
import Container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vehicle implements IVehicle{
    private String id;
    private String name;
    private double currentFuel;
    private double carryingCapacity;
    private double fuelCapacity;
    private IPort currentPort;
    // Keep track of containers both in general and each type
    private List<Container> containers;
    private int totalContainers;
    private int dryStorageContainers;
    private int openTopContainers;
    private int openSideContainers;
    private int refrigeratedContainers;
    private int liquidContainers;

    // Setter for currentPort
    public void setCurrentPort(IPort currentPort) {
        this.currentPort = currentPort;
    }

    // Getters

    public IPort getCurrentPort() {
        return currentPort;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public int getTotalContainers() {
        return totalContainers;
    }

    public int getDryStorageContainers() {
        return dryStorageContainers;
    }

    public int getOpenTopContainers() {
        return openTopContainers;
    }

    public int getOpenSideContainers() {
        return openSideContainers;
    }

    public int getRefrigeratedContainers() {
        return refrigeratedContainers;
    }

    public int getLiquidContainers() {
        return liquidContainers;
    }

    // Constructor

    public Vehicle() {
    }

    public Vehicle(String id, String name, double carryingCapacity, double fuelCapacity) {
        this.id = id;
        this.name = name;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentFuel = fuelCapacity; // Initially current fuel is full capacity

        // Ask user to set currentPort
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter current port: (enter port name, if null enter null)");
        String currentPortName = scanner.nextLine();
        // Use getPortByName from port, check if port exists
        if (Port.Port.getPortByName(currentPortName) != null && !currentPortName.equals("null")) {
            this.currentPort = Port.Port.getPortByName(currentPortName);
        } else {
            System.out.println("Set to null.");
            this.currentPort = null;
        }

        this.currentPort = null; // Initially the vehicle is not at any port (sailaway)
        this.containers = new ArrayList<>();
        this.totalContainers = 0;
        this.dryStorageContainers = 0;
        this.openTopContainers = 0;
        this.openSideContainers = 0;
        this.refrigeratedContainers = 0;
        this.liquidContainers = 0;
    }

    // Methods
    @Override
    public boolean loadContainer(Container container) {
        if (canLoadContainer(container)) {
            containers.add(container);
            totalContainers++;
            // add to container type
            if (container.getType().equals("Dry Storage")) { // use getType from container
                dryStorageContainers++;
            } else if (container.getType().equals("Open Top")) {
                openTopContainers++;
            } else if (container.getType().equals("Open Side")) {
                openSideContainers++;
            } else if (container.getType().equals("Refrigerated")) {
                refrigeratedContainers++;
            } else if (container.getType().equals("Liquid")) {
                liquidContainers++;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean unloadContainer(String id) {
        for (Container container : containers) {
            if (container.getId().equals(id)) { // use getId from container
                containers.remove(container);
                totalContainers--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveTo(IPort port) {
        if (canMoveTo(port)) {
            currentPort = port;
        }
    }

    @Override
    //automatic refuel
    public void refuel() {
        currentFuel = fuelCapacity;
    }

    @Override
    //manual refuel
    public void refuel(double amount) {
        currentFuel += amount;
        if (currentFuel > fuelCapacity) {
            currentFuel = fuelCapacity;
        }
    }

    @Override
    public boolean canMoveTo(IPort port) {
        // Vehicle needs enough fuel to move to another port
        // Calculate fuel needed to move to another port
        double fuelNeeded = 0;
        if (currentPort != null) {
            float distance = currentPort.getDistance(port); // use getDistance from port
            // get amount of fuel needed for all containers per km
            double fuelPerKm = 0;
            for (Container container : containers) {
                fuelPerKm += container.getFuelPerKm(); // use getFuelPerKm from container (fuel per unit of weight per km)
            }
            // get amount of fuel needed for vehicle
            fuelNeeded = distance * fuelPerKm;
        }
        return currentFuel >= fuelNeeded;
    }

    public boolean canLoadContainer(Container container) {
        // Ship can load any container, while truck can only load some containers
        return true; // for Ship
    }
}
