package services.admin;

import interfaces.CRUD.VehicleCRUD;
import utils.Constants;
import database.DatabaseHandler;
import models.vehicle.Vehicle;
import models.vehicle.Truck;
import models.vehicle.Ship;
import exceptions.InputValidation;

import java.util.*;

public class VehicleServicesAdmin extends AdminBaseServices implements VehicleCRUD {

    private final Scanner scanner = new Scanner(System.in);
    private final String VEHICLE_FILE_PATH = Constants.VEHICLE_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private final TripServicesAdmin tripController = new TripServicesAdmin();
    private final InputValidation idValidation = new InputValidation();

    public List<Vehicle> fetchVehiclesFromDatabase() {
        List<Vehicle> vehiclesList;
        try {
            Vehicle[] vehiclesArray = (Vehicle[]) dbHandler.readObjects(VEHICLE_FILE_PATH);
            vehiclesList = new ArrayList<>(Arrays.asList(vehiclesArray));
        } catch (Exception e) {
            vehiclesList = new ArrayList<>();
        }
        return vehiclesList;
    }

    @Override
    public void createNewVehicle() {
        System.out.println("VEHICLE CREATE WIZARD");
        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter vehicle name: ");
        String name = scanner.nextLine();
        System.out.print("Enter current fuel: ");
        double currentFuel = scanner.nextDouble();
        System.out.print("Enter carrying capacity: ");
        double carryingCapacity = scanner.nextDouble();
        System.out.print("Enter fuel capacity: ");
        double fuelCapacity = scanner.nextDouble();
        scanner.nextLine();  // To consume any leftover newline

        System.out.print("Enter vehicle type (Truck/Ship): ");
        String vehicleType = scanner.nextLine();

        Vehicle newVehicle;
        if (vehicleType.equalsIgnoreCase("Truck")) {
            System.out.print("Enter truck type (BASIC/REEFER/TANKER): ");
            Truck.TruckType type = Truck.TruckType.valueOf(scanner.nextLine().toUpperCase());
            newVehicle = new Truck(vehicleId, name, currentFuel, carryingCapacity, fuelCapacity, type);
        } else { // Assuming any other input to be a ship for simplicity
            newVehicle = new Ship(vehicleId, name, currentFuel, carryingCapacity, fuelCapacity);
        }

        List<Vehicle> vehiclesList = fetchVehiclesFromDatabase();
        vehiclesList.add(newVehicle);
        dbHandler.writeObjects(VEHICLE_FILE_PATH, vehiclesList.toArray(new Vehicle[0]));
    }

    @Override
    public void findVehicle() {
        System.out.println("FIND VEHICLE");
        System.out.print("Enter vehicle ID: ");
        String vehicleIdToDisplay = idValidation.idValidation("V");

        List<Vehicle> vehiclesList = fetchVehiclesFromDatabase();

        Vehicle vehicleToDisplay = null;
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getVehicleId().equals(vehicleIdToDisplay)) {
                vehicleToDisplay = vehicle;
                break;
            }
        }

        int tableWidth = 10 + 20 + 20 + 25 + 20 + 16; // column widths + 6 borders (3 on left and 3 on right)

        if (vehicleToDisplay != null) {
            printTopBorderWithCenteredTitle(tableWidth, "VEHICLE INFORMATION");

            // Print the header row
            System.out.printf("| %-10s | %-20s | %-20s | %-25s | %-20s |\n", "Vehicle ID", "Licensed Plate", "Current Fuel", "Carrying Capacity", "Fuel Capacity");

            // Print the separator between header and data rows
            printBorder(tableWidth);

            // Print the data row
            System.out.printf("| %-10s | %-20s | %-20.2f | %-25.2f | %-20.2f |\n",
                    vehicleToDisplay.getVehicleId(), vehicleToDisplay.getName(), vehicleToDisplay.getCurrentFuel(),
                    vehicleToDisplay.getCarryingCapacity(), vehicleToDisplay.getFuelCapacity());

            // Print the bottom border of the table
            printBorder(tableWidth);
        } else {
            System.out.println("No vehicle found with the given ID.");
        }
    }

    @Override
    public void displayAllVehicles() {
        List<Vehicle> vehiclesList = fetchVehiclesFromDatabase();

        // Adjust the table width for the new columns
        int tableWidth = 15 + 20 + 20 + 25 + 20 + 16 + 15 + 15; // column widths + 8 borders (4 on left and 4 on right)

        // Print the top border with the title centered
        printTopBorderWithCenteredTitle(tableWidth, "VEHICLES INFORMATION");

        // Print the header row with the new columns
        System.out.printf("| %-15s | %-20s | %-20s | %-25s | %-20s | %-15s | %-15s |\n",
                "Vehicle ID", "Licensed Plate", "Current Fuel (l)", "Carrying Capacity (kg)",
                "Fuel Capacity (l)", "Vehicle Type", "Truck Type");

        // Print the separator between header and data rows
        printBorder(tableWidth);

        // Print the data rows with the new columns
        for (Vehicle vehicle : vehiclesList) {
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

        // Print the bottom border of the table
        printBorder(tableWidth);
    }


    private void printBorder(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void printTopBorderWithCenteredTitle(int width, String title) {
        int paddingSize = (width - title.length() - 2) / 2; // Subtracting 2 for the spaces on either side of the title
        for (int i = 0; i < paddingSize; i++) {
            System.out.print("-");
        }
        System.out.print(" " + title + " ");
        for (int i = 0; i < width - title.length() - 2 - paddingSize * 2; i++) {
            System.out.print("-");
        }
        for (int i = 0; i < paddingSize; i++) {
            System.out.print("-");
        }
        System.out.println();
    }


    @Override
    public void updateVehicle() {
        System.out.println("VEHICLE UPDATE WIZARD");
        System.out.print("Enter vehicle ID to update: ");
        String vehicleIdToUpdate = scanner.nextLine();

        List<Vehicle> vehicleList = fetchVehiclesFromDatabase();

        Vehicle vehicleToUpdate = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleId().equals(vehicleIdToUpdate)) {
                vehicleToUpdate = vehicle;
                break;
            }
        }

        if (vehicleToUpdate != null) {
            System.out.print("Enter new vehicle name (leave blank to keep unchanged): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                vehicleToUpdate.setName(name);
            }

            System.out.print("Enter new current fuel amount (leave blank to keep unchanged): ");
            String currentFuelStr = scanner.nextLine();
            if (!currentFuelStr.isEmpty()) {
                double currentFuel = Double.parseDouble(currentFuelStr);
                vehicleToUpdate.setCurrentFuel(currentFuel);
            }

            System.out.print("Enter new carrying capacity (leave blank to keep unchanged): ");
            String carryingCapacityStr = scanner.nextLine();
            if (!carryingCapacityStr.isEmpty()) {
                double carryingCapacity = Double.parseDouble(carryingCapacityStr);
                vehicleToUpdate.setCarryingCapacity(carryingCapacity);
            }

            System.out.print("Enter new fuel capacity (leave blank to keep unchanged): ");
            String fuelCapacityStr = scanner.nextLine();
            if (!fuelCapacityStr.isEmpty()) {
                double fuelCapacity = Double.parseDouble(fuelCapacityStr);
                vehicleToUpdate.setFuelCapacity(fuelCapacity);
            }

            dbHandler.writeObjects(Constants.VEHICLE_FILE_PATH, vehicleList.toArray(new Vehicle[0]));
            System.out.println("Vehicle with ID " + vehicleIdToUpdate + " updated successfully.");
        } else {
            System.out.println("No vehicle found with the given ID.");
        }
    }


    @Override
    public void deleteVehicle() {
        System.out.println("VEHICLE DELETE WIZARD");
        System.out.print("Enter vehicle ID to delete: ");
        String vehicleIdToDelete = scanner.nextLine();

        List<Vehicle> vehicleList = fetchVehiclesFromDatabase();
        boolean isDeleted = false;
        Iterator<Vehicle> iterator = vehicleList.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getVehicleId().equals(vehicleIdToDelete)) {
                iterator.remove();
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            dbHandler.writeObjects(Constants.VEHICLE_FILE_PATH, vehicleList.toArray(new Vehicle[0]));
            System.out.println("Vehicle with ID " + vehicleIdToDelete + " deleted successfully.");
        } else {
            System.out.println("No vehicle found with the given ID.");
        }
    }

    public Vehicle getVehicleById(String vehicleId) {
        List<Vehicle> vehicleList;
        try {
            Vehicle[] vehicleArray = (Vehicle[]) dbHandler.readObjects(Constants.VEHICLE_FILE_PATH);
            vehicleList = new ArrayList<>(Arrays.asList(vehicleArray));
        } catch (Exception e) {
            System.out.println("Error reading vehicles or no vehicles exist.");
            return null;
        }

        Vehicle vehicleToReturn = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                vehicleToReturn = vehicle;
                break;
            }
        }
        return vehicleToReturn;
    }

    public void displayIdleVehicles() {
        List<Vehicle> vehicleList;
        try {
            Vehicle[] vehicleArray = (Vehicle[]) dbHandler.readObjects(Constants.VEHICLE_FILE_PATH);
            vehicleList = new ArrayList<>(Arrays.asList(vehicleArray));
        } catch (Exception e) {
            System.out.println("Error reading vehicles or no vehicles exist.");
            return;
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-20s | %-15s |\n", "Vehicle ID", "Name", "Current Fuel", "Carrying Capacity", "Fuel Capacity");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (Vehicle vehicle : vehicleList) {
            if (tripController.vehicleAvailCheck(vehicle)) {
                System.out.printf("| %-10s | %-20s | %-15.2f | %-20.2f | %-15.2f |\n",
                        vehicle.getVehicleId(), vehicle.getName(), vehicle.getCurrentFuel(), vehicle.getCarryingCapacity(),
                        vehicle.getFuelCapacity());
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }
}
