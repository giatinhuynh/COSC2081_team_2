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
    private String status;            // Current status (e.g., "In Transit", "Completed")

    public Trip(String tripId, Vehicle vehicle, Port departurePort, Port arrivalPort, Date departureDate) {
        this.tripId = tripId;
        this.vehicle = vehicle;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.departureDate = departureDate;
        this.status = "In Transit";
    }

    public void completeTrip(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
        this.status = "Completed";
    }

    // Getters and Setters

    public String getTripId() {
        return tripId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getStatus() {
        return status;
    }

    // You can also add more methods and attributes as per requirements.
}

