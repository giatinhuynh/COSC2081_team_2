package services;

import models.trip.Trip;
import models.vehicle.Vehicle;
import utils.Constants;
import database.DatabaseHandler;
import models.port.Port;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TripController extends BaseController {

    private final Scanner scanner = new Scanner(System.in);
    private final String TRIP_FILE_PATH = Constants.TRIP_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Override
    public void create() {
        System.out.println("TRIP CREATE WIZARD");

        System.out.print("Enter trip ID: ");
        String tripId = scanner.nextLine();

        // Assuming VehicleController has a method to display all Vehicles for the user to choose from
        VehicleController vehicleController = new VehicleController();
        vehicleController.displayAll(); // Display available vehicles

        System.out.print("Enter vehicle ID for the trip: ");
        String vehicleId = scanner.nextLine();
        Vehicle vehicle = vehicleController.getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Invalid vehicle ID. Aborting trip creation.");
            return;
        }

        // Using PortController to display available Ports for departure and arrival
        PortController portController = new PortController();

        portController.displayAll(); // Display all ports
        System.out.print("Enter departure port ID: ");
        String departurePortId = scanner.nextLine();
        Port departurePort = portController.getPortById(departurePortId);
        if (departurePort == null) {
            System.out.println("Invalid departure port ID. Aborting trip creation.");
            return;
        }

        System.out.print("Enter arrival port ID: ");
        String arrivalPortId = scanner.nextLine();
        Port arrivalPort = portController.getPortById(arrivalPortId);
        if (arrivalPort == null) {
            System.out.println("Invalid arrival port ID. Aborting trip creation.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        System.out.print("Enter departure date (dd-MM-yyyy HH:mm): ");
        Date departureDate;
        try {
            departureDate = sdf.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error parsing date. Please use the correct format.");
            return;
        }

        Trip newTrip = new Trip(tripId, vehicle, departurePort, arrivalPort, departureDate);

        List<Trip> tripsList;
        try {
            Trip[] tripsArray = (Trip[]) dbHandler.readObjects(TRIP_FILE_PATH);
            tripsList = new ArrayList<>(Arrays.asList(tripsArray));
        } catch (Exception e) {
            tripsList = new ArrayList<>();
        }

        tripsList.add(newTrip);

        dbHandler.writeObjects(TRIP_FILE_PATH, tripsList.toArray(new Trip[0]));
        System.out.println("Trip created successfully!");
    }

    @Override
    public void displayOne() {
        System.out.println("DISPLAY TRIP INFO");
        System.out.print("Enter trip ID: ");
        String tripIdToDisplay = scanner.nextLine();

        List<Trip> tripsList;
        try {
            Trip[] tripsArray = (Trip[]) dbHandler.readObjects(TRIP_FILE_PATH);
            tripsList = new ArrayList<>(Arrays.asList(tripsArray));
        } catch (Exception e) {
            System.out.println("Error reading trips or no trips exist.");
            return;
        }

        Trip tripToDisplay = null;
        for (Trip trip : tripsList) {
            if (trip.getTripId().equals(tripIdToDisplay)) {
                tripToDisplay = trip;
                break;
            }
        }

        if (tripToDisplay != null) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    "Trip ID", "Vehicle", "Departure Port", "Arrival Port", "Departure Date", "Arrival Date", "Status");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    tripToDisplay.getTripId(), tripToDisplay.getVehicle().toString(), tripToDisplay.getDeparturePort().getName(),
                    tripToDisplay.getArrivalPort().getName(), sdf.format(tripToDisplay.getDepartureDate()),
                    tripToDisplay.getArrivalDate() != null ? sdf.format(tripToDisplay.getArrivalDate()) : "N/A", tripToDisplay.getStatus());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No trip found with the given ID.");
        }
    }

    @Override
    public void displayAll() {
        System.out.println("DISPLAY ALL TRIPS INFO");

        List<Trip> tripsList;
        try {
            Trip[] tripsArray = (Trip[]) dbHandler.readObjects(TRIP_FILE_PATH);
            tripsList = new ArrayList<>(Arrays.asList(tripsArray));
        } catch (Exception e) {
            System.out.println("Error reading trips or no trips exist.");
            return;
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                "Trip ID", "Vehicle", "Departure Port", "Arrival Port", "Departure Date", "Arrival Date", "Status");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Trip trip : tripsList) {
            System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    trip.getTripId(), trip.getVehicle().toString(), trip.getDeparturePort().getName(),
                    trip.getArrivalPort().getName(), sdf.format(trip.getDepartureDate()),
                    trip.getArrivalDate() != null ? sdf.format(trip.getArrivalDate()) : "N/A", trip.getStatus());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void update() {
        System.out.println("TRIP UPDATE WIZARD");
        System.out.print("Enter trip ID to update: ");
        String tripIdToUpdate = scanner.nextLine();

        List<Trip> tripsList;
        try {
            Trip[] tripsArray = (Trip[]) dbHandler.readObjects(TRIP_FILE_PATH);
            tripsList = new ArrayList<>(Arrays.asList(tripsArray));
        } catch (Exception e) {
            System.out.println("Error reading trips or no trips exist.");
            return;
        }

        Trip tripToUpdate = null;
        for (Trip trip : tripsList) {
            if (trip.getTripId().equals(tripIdToUpdate)) {
                tripToUpdate = trip;
                break;
            }
        }

        if (tripToUpdate != null) {
            System.out.print("Enter new arrival date (dd-MM-yyyy HH:mm, leave blank to keep unchanged): ");
            String arrivalDateStr = scanner.nextLine();
            if (!arrivalDateStr.isEmpty()) {
                Date arrivalDate;
                try {
                    arrivalDate = sdf.parse(arrivalDateStr);
                    tripToUpdate.completeTrip(arrivalDate);
                } catch (ParseException e) {
                    System.out.println("Error parsing date. Please use the correct format.");
                }
            }

            System.out.print("Enter new status (e.g., \"In Transit\", \"Completed\", leave blank to keep unchanged): ");
            String newStatus = scanner.nextLine();
            if (!newStatus.isEmpty()) {
                tripToUpdate.setStatus(newStatus);
            }

            dbHandler.writeObjects(TRIP_FILE_PATH, tripsList.toArray(new Trip[0]));
            System.out.println("Trip updated successfully!");
        } else {
            System.out.println("No trip found with the given ID.");
        }
    }

    @Override
    public void delete() {
        System.out.println("DELETE TRIP");
        System.out.print("Enter trip ID to delete: ");
        String tripIdToDelete = scanner.nextLine();

        List<Trip> tripsList;
        try {
            Trip[] tripsArray = (Trip[]) dbHandler.readObjects(TRIP_FILE_PATH);
            tripsList = new ArrayList<>(Arrays.asList(tripsArray));
        } catch (Exception e) {
            System.out.println("Error reading trips or no trips exist.");
            return;
        }

        Trip tripToDelete = null;
        for (Trip trip : tripsList) {
            if (trip.getTripId().equals(tripIdToDelete)) {
                tripToDelete = trip;
                break;
            }
        }

        if (tripToDelete != null) {
            tripsList.remove(tripToDelete);
            dbHandler.writeObjects(TRIP_FILE_PATH, tripsList.toArray(new Trip[0]));
            System.out.println("Trip deleted successfully!");
        } else {
            System.out.println("No trip found with the given ID.");
        }
    }
}
