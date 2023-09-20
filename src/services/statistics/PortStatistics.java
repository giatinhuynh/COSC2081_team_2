package services.statistics;

import interfaces.statistics.PortStatInterface;
import models.port.Port;
import models.trip.Trip;
import services.admin.PortServicesAdmin;
import services.admin.TripServicesAdmin;

import java.util.List;
import java.util.stream.Collectors;

public class PortStatistics implements PortStatInterface {

    private final PortServicesAdmin portController = new PortServicesAdmin();
    private final TripServicesAdmin tripController = new TripServicesAdmin();

    public void displayTotalNumberOfPorts() {
        int total = portController.fetchPortsFromDatabase().size();

        System.out.println("=================================");
        System.out.println("   TOTAL NUMBER OF PORTS    ");
        System.out.println("=================================");
        System.out.println("             " + total + "             ");
        System.out.println("=================================");
    }

    public void portUsedCapacity() {
        int total = portController.fetchPortsFromDatabase().size();
        int used = portController.fetchPortsFromDatabase().stream()
                .filter(port -> port.getOccupiedCapacity() > 0)
                .toList()
                .size();

        System.out.println("=================================");
        System.out.println("   PORT USED CAPACITY    ");
        System.out.println("=================================");
        System.out.println("             " + used + "/" + total + "             ");
        System.out.println("=================================");
    }

    public void portTripCount() {
        List<Trip> allTrips = tripController.fetchTripsFromDatabase();
        List<Port> allPorts = portController.fetchPortsFromDatabase();

        System.out.println("-------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-10s |\n", "Port Name", "Departures", "Arrivals");
        System.out.println("-------------------------------------------------");

        for (Port port : allPorts) {
            long departureCount = allTrips.stream()
                    .filter(trip -> trip.getDeparturePort().getPortId().equals(port.getPortId()))
                    .count();

            long arrivalCount = allTrips.stream()
                    .filter(trip -> trip.getArrivalPort().getPortId().equals(port.getPortId()))
                    .count();

            System.out.printf("| %-20s | %-10d | %-10d |\n", port.getName(), departureCount, arrivalCount);
        }

        System.out.println("-------------------------------------------------");
    }

}
