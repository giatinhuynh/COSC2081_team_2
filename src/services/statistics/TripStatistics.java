package services.statistics;

import interfaces.statistics.TripStatInterface;
import models.trip.Trip;
import models.vehicle.Ship;
import models.vehicle.Truck;
import services.admin.PortServicesAdmin;
import services.admin.TripServicesAdmin;

import java.util.List;

public class TripStatistics implements TripStatInterface {

    private final TripServicesAdmin tripController = new TripServicesAdmin();

    public void displayTotalTrips() {
        int total = tripController.fetchTripsFromDatabase().size();

        System.out.println("=================================");
        System.out.println("   TOTAL NUMBER OF TRIPS    ");
        System.out.println("=================================");
        System.out.println("             " + total + "             ");
        System.out.println("=================================");
    }

    public void tripStatus() {
        List<Trip> trips = tripController.fetchTripsFromDatabase();
        int loadingCount = 0;
        int inTransitCount = 0;
        int completedCount = 0;

        for (Trip trip : trips) {
            if (trip.getStatus().equals("LOADING")) {
                loadingCount++;
            } else if (trip.getStatus().equals("IN_TRANSIT")) {
                inTransitCount++;
            } else if (trip.getStatus().equals("COMPLETED")) {
                completedCount++;
            }
        }

        System.out.println("-------------------------------");
        System.out.printf("| %-15s | %-7s |\n", "Trip Status", "Count");
        System.out.println("-------------------------------");
        System.out.printf("| %-15s | %-7d |\n", "LOADING", loadingCount);
        System.out.printf("| %-15s | %-7d |\n", "IN_TRANSIT", inTransitCount);
        System.out.printf("| %-15s | %-7d |\n", "COMPLETED", completedCount);
        System.out.println("-------------------------------");
    }

    public void tripByVehicle() {
        List<Trip> trips = tripController.fetchTripsFromDatabase();
        int truckCount = 0;
        int shipCount = 0;

        for (Trip trip : trips) {
            if (trip.getVehicle() instanceof Truck) {
                truckCount++;
            } else if (trip.getVehicle() instanceof Ship) {
                shipCount++;
            }
        }

        System.out.println("-------------------------------");
        System.out.printf("| %-15s | %-7s |\n", "Vehicle Type", "Trips");
        System.out.println("-------------------------------");
        System.out.printf("| %-15s | %-7d |\n", "Truck", truckCount);
        System.out.printf("| %-15s | %-7d |\n", "Ship", shipCount);
        System.out.println("-------------------------------");
    }

    public void averageTripDuration() {
        List<Trip> trips = tripController.fetchTripsFromDatabase();
        int total = 0;
        int count = 0;

        for (Trip trip : trips) {
            if (trip.getStatus().equals("COMPLETED")) {
                total += (int) trip.getDuration();
                count++;
            }
        }

        System.out.println("=================================");
        System.out.println("   AVERAGE TRIP DURATION    ");
        System.out.println("=================================");
        System.out.println("             " + (total / count) + "             ");
        System.out.println("=================================");
    }
}
