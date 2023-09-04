/*
References:
- https://www.geeksforgeeks.org/interfaces-in-java/
*/

package Port;

import Container.Container;
import Vehicle.IVehicle;

import java.util.Date;
import java.util.List;

public interface IPort {
    public double calculateDistanceTo(Port port);

    public boolean addContainer(Container container);

    public boolean removeContainer(String containerID);

    public void listVehicles();

    public void listTrips();

//    public void addVehicle(Vehicle vehicle);

//    public void removeVehicle(Vehicle vehicle);

//    public void addTrip(Trip trip);

//    public List<Trip> getTripForDate(Date date);
}
