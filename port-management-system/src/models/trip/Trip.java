package models.trip;

import models.port.Port;
import models.vehicle.Vehicle;

import java.io.Serializable;
import java.util.Date;

public class Trip implements Serializable {
    private String tripId;            // Unique ID for the trip
    private Vehicle vehicle;          // Vehicle used for the trip
    private Port departurePort;       // Departure port
    private Port arrivalPort;         // Destination port
    private Date departureDate;       // Departure date
    private Date arrivalDate;         // Arrival date
    private Status status;            // Current status (e.g., "In Transit", "Completed")

    private enum Status {
        LOADING, IN_TRANSIT, COMPLETED
    }

    public Trip(String tripId, Vehicle vehicle, Port departurePort, Port arrivalPort, Date departureDate) {
        this.tripId = tripId;
        this.vehicle = vehicle;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.departureDate = departureDate;
        this.status = Status.valueOf("LOADING");
    }

    public void completeTrip(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
        this.status = Status.valueOf("COMPLETED");
    }

    // Getters and Setters
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Port departurePort) {
        this.departurePort = departurePort;
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(Port arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
}

