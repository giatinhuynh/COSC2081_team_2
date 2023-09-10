package models.port;

import models.container.Container;
import models.trip.Trip;
import models.vehicle.Vehicle;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * The Port class represents a port with information about its location, capacity, and operations.
 */
public class Port implements Serializable {
    private String portId;
    private String name;
    private double latitude;
    private double longitude;
    private double storingCapacity;
    private boolean landingAbility;
    private List<Container> containers;
    private List<Vehicle> vehicles;
    private HashMap<String, Trip> trafficHistory;

    // Constructors
    /**
     * Constructs a new Port instance with the provided attributes.
     *
     * @param portId         The unique identifier for the port.
     * @param name           The name of the port.
     * @param latitude       The latitude of the port's location.
     * @param longitude      The longitude of the port's location.
     * @param storingCapacity The maximum capacity for storing containers.
     * @param landingAbility Indicates whether the port has landing ability for ships.
     */
    public Port(String portId, String name, double latitude, double longitude, double storingCapacity, boolean landingAbility) {
        this.portId = portId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.containers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.trafficHistory = new HashMap<>();
    }

    /**
     * Constructs a new Port instance with the provided port ID.
     *
     * @param portId The unique identifier for the port.
     */
    public Port(String portId) {
        this.portId = portId;
    }

    // Getters and setters
    public String getPortId() {
        return portId;
    }
    public void setPortId(String portId) {
        this.portId = portId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public boolean getLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public double calculateDistance(Port otherPort) {
        // Assuming a simple Euclidean distance for the sake of simplicity.
        return Math.sqrt(Math.pow(this.latitude - otherPort.latitude, 2) + Math.pow(this.longitude - otherPort.longitude, 2));
    }

    public boolean canStoreContainer(Container container) {
        double currentWeight = containers.stream().mapToDouble(Container::getWeight).sum();
        return (currentWeight + container.getWeight()) <= storingCapacity;
    }

    public void addContainer(Container container) {
        if (canStoreContainer(container)) {
            this.containers.add(container);
        } else {
            // Handle storage capacity exceeded scenario
            System.out.println("Storage capacity exceeded. Cannot store container.");
        }
    }

    public void removeContainer(Container container) {
        this.containers.remove(container);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
    }

    public void recordTrip(Trip trip) {
        this.trafficHistory.put(trip.getTripId(), trip);
    }

}
