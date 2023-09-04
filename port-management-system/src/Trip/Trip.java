package Trip;

import java.util.Date;

public class Trip {
    private String id;
    private Date departureDate;
    private Date arrivalDate;
    private String departurePortId;
    private String arrivalPortId;
    private String vehicleId;
    private String status;

    public Trip() {
        this.id = "Default";
        this.departureDate = new Date();
        this.arrivalDate = new Date();
        this.departurePortId = "Default";
        this.arrivalPortId = "Default";
        this.vehicleId = "Default";
        this.status = "Default";
    }

    public Trip(String id, Date departureDate, Date arrivalDate, String departurePortId, String arrivalPortId, String vehicleId, String status) {
        this.id = id;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePortId = departurePortId;
        this.arrivalPortId = arrivalPortId;
        this.vehicleId = vehicleId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getDeparturePortId() {
        return departurePortId;
    }

    public String getArrivalPortId() {
        return arrivalPortId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getStatus() {
        return status;
    }
}
