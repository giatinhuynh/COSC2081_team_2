package services.manager;

import database.DatabaseHandler;
import interfaces.manager.ManagerVehicleInterface;
import models.port.Port;
import models.trip.Trip;
import models.user.PortManager;
import models.vehicle.Vehicle;
import services.PortController;
import services.TripController;
import services.VehicleController;
import utils.Constants;
import utils.CurrentUser;
import utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class VehicleServices extends BaseServices implements ManagerVehicleInterface {

    private final Port managedPort;
    private final Scanner scanner = new Scanner(System.in);
    private final String TRIP_FILE_PATH = Constants.TRIP_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private final VehicleController vehicleController = new VehicleController();
    private final PortController portController = new PortController();
    private final TripController tripController = new TripController();
    private final DateUtils dateUtils = new DateUtils();

    public VehicleServices() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    public void refuelVehicle() {
        // Display all vehicles at the managed port
        System.out.println("Vehicle at your port: ");
        for (Vehicle vehicle : managedPort.getCurrentVehicles()) {
            System.out.println(vehicle.getVehicleId() + " - " + vehicle.getName());
        }

        // Get input for vehicle id
        System.out.println("Enter the vehicle ID you want to refuel:");
        String vehicleId = scanner.nextLine();
        Vehicle selectedVehicle = findVehicleById(vehicleId);

        // Process the fuel
        assert selectedVehicle != null;
        selectedVehicle.refuel();
        System.out.println("Refuel successful!");
    }

    public void deployVehicle() throws ParseException {
        // Display all vehicles at the managed port
        System.out.println("Idle vehicles at your port: ");
        vehicleController.displayIdleVehicles();

        // Get input for vehicle id
        System.out.println("Enter the vehicle ID you want to deploy:");
        String vehicleId = scanner.nextLine();
        Vehicle selectedVehicle = findVehicleById(vehicleId);

        // Process the deployment
       // Ask for destination port
        System.out.println("Enter the destination port ID:");
        String destinationPortId = scanner.nextLine();
        Port selectedDestinationPort = portController.getPortById(destinationPortId);

        // Ask for departure date
        System.out.println("Enter the departure date (dd-MM-yyyy HH:mm):");
        String departureDateString = scanner.nextLine();
        Date departureDate = sdf.parse(departureDateString);

        // Calculate arrival date
        Date arrivalDate = sdf.parse(dateUtils.hoursToDate(selectedVehicle.calculateTimeNeeded(selectedDestinationPort), "yyyy-MM-dd HH:mm:ss"));

        // Calculate fuel needed
        double fuelNeeded = selectedVehicle.calculateFuelNeeded(selectedDestinationPort);
        // Check if the vehicle has enough fuel
        if (fuelNeeded > selectedVehicle.getCurrentFuel()) {
            System.out.println("Not enough fuel! Do you want to refuel? (Y/N)");
            String refuelChoice = scanner.nextLine();
            if (refuelChoice.equals("Y")) {
                selectedVehicle.refuel();
                System.out.println("Refuel successful!");
            } else {
                System.out.println("Refuel cancelled.");
                return;
            }
        }

        // Create the trip
        tripController.createNewTrip(selectedVehicle, managedPort, selectedDestinationPort, departureDate, arrivalDate, Trip.Status.valueOf("LOADING"));

        // Send message to the user
        System.out.println("Trip created successfully!");
    }
}

