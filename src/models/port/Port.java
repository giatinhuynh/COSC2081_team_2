package models.port;

import models.container.Container;
import models.vehicle.Vehicle;
import utils.DistanceCalculator;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Port implements Serializable {

    // Attributes
    private String portId;
    private String name;
    private double latitude;
    private double longitude;
    private double storingCapacity;
    private boolean landingAbility;
    private List<Container> currentContainers;
    private List<Vehicle> currentVehicles;

    // Constructors
    public Port(String portId) {
        this.portId = portId;
    }

    public Port(String portId, String name, double latitude, double longitude, double storingCapacity, boolean landingAbility) {
        this.portId = portId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.currentContainers = new ArrayList<>();
        this.currentVehicles = new ArrayList<>();
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

    public List<Container> getCurrentContainers() {
        return currentContainers;
    }

    // Methods
    /**
     * Calculates the distance in kilometers between the current port and another port
     * using their respective latitude and longitude coordinates. This method utilizes
     * the Haversine formula for distance calculation.
     *
     * @param anotherPort The port to which the distance is calculated.
     * @return The distance in kilometers between the current port and the specified port.
     */
    public double calculateDistanceToAnotherPort(Port anotherPort) {
        return DistanceCalculator.calculateDistance(
                this.latitude, this.longitude, anotherPort.latitude, anotherPort.longitude
        );
    }

    /**
     * Adds a vehicle to the list of vehicles associated with this port.
     *
     * @param vehicle The vehicle to be added.
     */
    public void addVehicle(Vehicle vehicle) {
        this.currentVehicles.add(vehicle);
    }

    /**
     * Removes a vehicle from the list of vehicles associated with this port.
     *
     * @param vehicle The vehicle to be removed.
     */
    public void removeVehicle(Vehicle vehicle) {
        this.currentVehicles.remove(vehicle);
    }

    /**
     * Adds a container to the list of containers associated with this port.
     *
     * @param container The container to be added.
     */
    public void addContainer(Container container) {
        this.currentContainers.add(container);
    }

    /**
     * Removes a container from the list of containers associated with this port.
     *
     * @param container The container to be removed.
     */
    public void removeContainer(Container container) {
        this.currentContainers.remove(container);
    }
}
