/*
References:
- https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
- https://www.techiedelight.com/check-if-value-exists-list-java/
- https://www.baeldung.com/java-search-enum-values
 */

package Port;

import Container.Container;
import Vehicle.IVehicle;
import Trip.Trip;
import Vehicle.Vehicle;
import java.util.Date;
import java.util.List;

public class Port implements IPort {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private double storingCapacity;

//    private boolean landingAbility;

    private boolean isLanding;
    private List<Container> containers;
    private List<Trip> trips;

    private List<Vehicle> vehicles; // suggestion
//    private List<IVehicle> vehicles;
//    private List<Trip> tripHistory;

    public Port() {
        this.id = "Default";
        this.name = "Default";
        this.latitude = 0;
        this.longitude = 0;
        this.storingCapacity = 0;
        this.isLanding = false;
    }

    public Port(String id, String name, double latitude, double longitude, double storingCapacity, boolean isLanding) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.isLanding = isLanding;
    }

    @Override
    public double calculateDistanceTo(Port anotherPort) {
        final int R = 6371; // Radius of the Earth
        double latDistance = Math.toRadians(anotherPort.getLatitude() - latitude);
        double lonDistance = Math.toRadians(anotherPort.getLongitude() - longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(anotherPort.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    @Override
    public boolean addContainer(Container container) {
        String[] containerTypes = {"DryStorage", "OpenTop", "OpenSide", "Refrigerated", "Liquid"};
        for (String type : containerTypes) {
            if (type.equalsIgnoreCase(container.getType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void listVehicles() {
        for (Vehicle v : vehicles) {
            System.out.printf("Vehicle id %s, name %s, current fuel %f, carrying capacity %f, fuel capacity %f, current port %s", v.getId(), v.getName(), v.getCurrentFuel(), v.getCarryingCapacity(), v.getFuelCapacity(), v.getCurrentPort());  // String to display current port (check again)
        }
    }

    @Override
    public void listTrips() {
        for (Trip t: trips) {
            System.out.printf("Trip id %s, departure date %s, arrival date %s, departure port id %s, arrival port id %s, vehicle id %s, status %s", t.getId(), t.getDepartureDate(), t.getArrivalDate(), t.getDeparturePortId(), t.getArrivalPortId(), t.getVehicleId(), t.getStatus());
        }
    }

    @Override
    public boolean removeContainer(String containerID) {
        // Validate whether containerID matches the ID of a container existing in the system
        for (Container c: containers) {
            if (containerID.equalsIgnoreCase(c.getId())) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public void addVehicle(Vehicle vehicle) {
//
//    }

//    @Override
//    public void removeVehicle(Vehicle vehicle) {
//
//    }

//    @Override
//    public void addTrip(Trip trip) {
//
//    }

//    @Override
//    public List<Trip> getTripForDate(Date date) {
//        return null;
//    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public boolean isLanding() {
        return isLanding;
    }
}

