package services.manager;

import database.DatabaseHandler;
import exceptions.InputValidation;
import exceptions.TypeCheck;
import interfaces.manager.ManagerVehicleInterface;
import models.port.Port;
import models.trip.Trip;
import models.user.PortManager;
import models.vehicle.Ship;
import models.vehicle.Truck;
import models.vehicle.Vehicle;
import services.admin.PortServicesAdmin;
import services.admin.TripServicesAdmin;
import services.admin.VehicleServicesAdmin;
import utils.Constants;
import utils.CurrentUser;
import utils.DateUtils;
import utils.UiUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VehicleServicesManager extends ManagerBaseServices implements ManagerVehicleInterface {

    private final Port managedPort;
    private final Scanner scanner = new Scanner(System.in);
    private final String TRIP_FILE_PATH = Constants.TRIP_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private final VehicleServicesAdmin vehicleController = new VehicleServicesAdmin();
    private final PortServicesAdmin portController = new PortServicesAdmin();
    private final TripServicesAdmin tripController = new TripServicesAdmin();
    private final DateUtils dateUtils = new DateUtils();
    private final InputValidation inputValidation = new InputValidation();
    private final UiUtils uiUtils = new UiUtils();
    private final TypeCheck typeCheck = new TypeCheck();

    public VehicleServicesManager() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    public void refuelVehicle() {
        uiUtils.clearScreen();

        uiUtils.printFunctionName("REFUEL VEHICLE", 100);
        System.out.println();

        // Display all vehicles at the managed port
        List<Vehicle> vehicles = vehicleController.fetchVehiclesFromDatabase();
        uiUtils.printTopBorderWithTableName("VEHICLES AT YOUR PORT", 15, 15, 20, 20);
        System.out.printf("| %-15s | %-15s | %-20s | %-20s ", "Vehicle ID", "Licensed Plate", "Current Fuel", "Fuel Capacity");
        uiUtils.printHorizontalLine(15, 15, 20, 20);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCurrentPort().getPortId().equals(managedPort.getPortId())) {
                System.out.printf("| %-15s | %-15s | %-20.2f | %-20.2f ", vehicle.getVehicleId(), vehicle.getName(), vehicle.getCurrentFuel(), vehicle.getFuelCapacity());
            }
        }
        uiUtils.printHorizontalLine(15, 15, 20, 20);
        System.out.println();


        // Get input for vehicle id
        String vehicleId = inputValidation.idValidation("V", "Enter the vehicle ID you want to refuel:");
        System.out.println();
        Vehicle selectedVehicle = findVehicleById(vehicleId);

        // Process the fueling
        selectedVehicle.refuel();
        vehicleController.updateVehicleInDatabase(selectedVehicle);
        uiUtils.printSuccessMessage("Refuel successful!");
    }

    public void deployVehicle() throws ParseException {

        // Display all vehicles at the managed port
        List<Vehicle> vehicleList = vehicleController.fetchVehiclesFromDatabase();

        uiUtils.printTopBorderWithTableName("IDLE VEHICLES", 15, 20, 20, 25, 20, 15, 15);
        System.out.printf("| %-15s | %-20s | %-20s | %-25s | %-20s | %-15s | %-15s |\n",
                "Vehicle ID", "Name", "Current Fuel (l)", "Carrying Capacity (kg)",
                "Fuel Capacity (l)", "Vehicle Type", "Truck Type");
        uiUtils.printHorizontalLine(15, 20, 20, 25, 20, 15, 15);
        for (Vehicle vehicle : vehicleList) {
            if (tripController.vehicleAvailCheck(vehicle)) {
                if (vehicle.getCurrentPort().getPortId().equals(managedPort.getPortId())) {
                    String vehicleType = "";
                    String truckType = "N/A";

                    if (vehicle instanceof Truck) {
                        vehicleType = "Truck";
                        truckType = ((Truck) vehicle).getType();
                    } else if (vehicle instanceof Ship) {
                        vehicleType = "Ship";
                    }

                    System.out.printf("| %-15s | %-20s | %-20.2f | %-25.2f | %-20.2f | %-15s | %-15s |\n",
                            vehicle.getVehicleId(), vehicle.getName(), vehicle.getCurrentFuel(),
                            vehicle.getCarryingCapacity(), vehicle.getFuelCapacity(), vehicleType, truckType);
                }
            }
        }
        uiUtils.printHorizontalLine(15, 20, 20, 25, 20, 15, 15);

        // Get input for vehicle id
        String vehicleId = inputValidation.idValidation("V", "Enter the vehicle ID you want to deploy:");
        System.out.println();
        Vehicle selectedVehicle = findVehicleById(vehicleId);

       // Ask for destination port
        System.out.println("Enter the destination port ID:");
        String destinationPortId = inputValidation.idValidation("P", "Enter the destination port ID:");
        Port selectedDestinationPort = portController.getPortById(destinationPortId);

        // Ask for departure date
        System.out.println("Enter the departure date (dd-MM-yyyy):");
        String departureDateString = scanner.nextLine();
        Date departureDate = sdf.parse(departureDateString);

        // Calculate arrival date
        Date arrivalDate = sdf.parse(dateUtils.hoursToDate(selectedVehicle.calculateTimeNeeded(selectedDestinationPort), "yyyy-MM-dd"));

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
        uiUtils.printSuccessMessage("Vehicle deployed successfully!");
    }
}

