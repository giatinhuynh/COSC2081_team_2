package services.manager;

import interfaces.manager.ManagerInterface;
import models.port.Port;
import models.user.PortManager;
import models.vehicle.Vehicle;
import utils.CurrentUser;

import java.util.Scanner;

public class VehicleServices extends BaseServices implements ManagerInterface {

    private final Port managedPort;
    private final Scanner scanner = new Scanner(System.in);

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
}

