package controllers;

import database.DatabaseManager;
import models.vehicle.Ship;
import models.vehicle.Truck;
import models.vehicle.Vehicle;

import java.util.List;
import java.util.Scanner;

public class VehicleController extends BaseController {

    private DatabaseManager dbManager;
    private Scanner scanner;

    // Methods for CRUD operations, refueling, etc.
    public void createNewVehicle() {
        System.out.print("Enter Vehicle Type (Truck/Ship): ");
        String vehicleType = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();

        System.out.print("Enter Vehicle Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Vehicle Current Fuel: ");
        double currentFuel = scanner.nextDouble();

        System.out.print("Enter Vehicle Carrying Capacity: ");
        double carryingCapacity = scanner.nextDouble();

        System.out.print("Enter Vehicle Fuel Capacity: ");
        double fuelCapacity = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        Vehicle newVehicle = null;

        if(vehicleType.equals("truck")) {
            System.out.print("Enter Truck Type (BASIC/REEFER/TANKER): ");
            Truck.TruckType type = Truck.TruckType.valueOf(scanner.nextLine().trim().toUpperCase());
            newVehicle = new Truck(vehicleId, name, currentFuel, carryingCapacity, fuelCapacity, type);
        } else if(vehicleType.equals("ship")) {
            newVehicle = new Ship(vehicleId, name, currentFuel, carryingCapacity, fuelCapacity);
        }

        if(newVehicle != null) {
            dbManager.saveVehicle(newVehicle);
            System.out.println("Vehicle added successfully!");
        } else {
            System.out.println("Invalid vehicle type!");
        }
    }

    public void updateExistingVehicle() {
        System.out.print("Enter the ID of the vehicle you want to update: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicle = dbManager.getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        System.out.println("Updating vehicle with ID: " + vehicleId);
        // You can continue to ask for all the attributes and update them similarly.
        System.out.print("Enter new name for vehicle (Leave blank to keep unchanged): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            vehicle.setName(newName);
        }

        dbManager.updateVehicle(vehicle);
        System.out.println("Vehicle updated successfully!");
    }

    public void deleteVehicle() {
        System.out.print("Enter the ID of the vehicle you want to delete: ");
        String vehicleId = scanner.nextLine();

        if(dbManager.getVehicleById(vehicleId) != null) {
            dbManager.deleteVehicle(vehicleId);
            System.out.println("Vehicle deleted successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    public void searchVehicle() {
        System.out.print("Enter the ID of the vehicle you want to search for: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicle = dbManager.getVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        // Display vehicle details in tabular format
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %15s | %15s | %15s | %15s | %15s |\n", "Vehicle ID", "Name", "Current Fuel", "Carrying Capacity", "Fuel Capacity");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %15s | %15s | %15.2f | %15.2f | %15.2f |\n", vehicle.getVehicleId(), vehicle.getName(), vehicle.getCurrentFuel(), vehicle.getCarryingCapacity(), vehicle.getFuelCapacity());
        System.out.println("---------------------------------------------------------------------");
    }

    public void viewAllVehicles() {
        List<Vehicle> allVehicles = dbManager.getAllVehicles();

        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %15s | %15s | %15s | %15s | %15s |\n", "Vehicle ID", "Name", "Current Fuel", "Carrying Capacity", "Fuel Capacity");
        System.out.println("---------------------------------------------------------------------");

        for (Vehicle vehicle : allVehicles) {
            System.out.printf("| %15s | %15s | %15.2f | %15.2f | %15.2f |\n", vehicle.getVehicleId(), vehicle.getName(), vehicle.getCurrentFuel(), vehicle.getCarryingCapacity(), vehicle.getFuelCapacity());
        }

        System.out.println("---------------------------------------------------------------------");
    }
}
