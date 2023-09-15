package services;

import models.container.Container;
import models.port.Port;
import models.user.PortManager;
import models.vehicle.Ship;
import models.vehicle.Vehicle;
import utils.CurrentUser;

import java.util.Scanner;

public class ContainerServices {

    private final PortController portServices = new PortController();
    private final Port managedPort;

    public ContainerServices() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    public void loadContainerFlow() {
        Scanner scanner = new Scanner(System.in);

        // Display all containers at the managed port
        System.out.println("Containers at your port:");
        for (Container container : managedPort.getCurrentContainers()) {
            System.out.println(container.getContainerId() + " - " + container.getContainerType());
        }

        // Get input for container id
        System.out.println("Enter the container ID you want to load:");
        String containerId = scanner.nextLine();
        Container selectedContainer = findContainerById(containerId);

        // Show all ports except manager's port
        System.out.println("Available destination ports:");
        portServices.displayAll();

        // Get input for destination port
        System.out.println("Enter the destination port ID:");
        String portId = scanner.nextLine();
        Port destinationPort = findPortById(portId);

        // Based on chosen container and port, display possible vehicles
        for (Vehicle vehicle : managedPort.getCurrentVehicles()) {
            assert destinationPort != null;
            if ((destinationPort.getLandingAbility() || vehicle instanceof Ship) && vehicle.canCarry(selectedContainer) && vehicle.canMoveToPort(destinationPort)) {
                System.out.println(vehicle.getVehicleId() + " - " + vehicle.getName());
            }
        }

        // Get input for vehicle choice
        System.out.println("Enter the vehicle ID you want to use:");
        String vehicleId = scanner.nextLine();
        Vehicle selectedVehicle = findVehicleById(vehicleId);

        assert selectedVehicle != null;
        if (selectedVehicle.getCurrentFuel() < selectedVehicle.calculateFuelNeeded(destinationPort)) {
            System.out.println("Vehicle needs refueling. Do you want to refuel? (yes/no)");
            String refuelChoice = scanner.nextLine();
            if ("yes".equalsIgnoreCase(refuelChoice)) {
                selectedVehicle.refuel();
            } else {
                loadContainerFlow();
                return;
            }
        }

        selectedVehicle.loadContainer(selectedContainer);
        System.out.println("Container loaded successfully!");
    }

    public void unloadContainerFlow() {
        Scanner scanner = new Scanner(System.in);

        // Display all loaded containers at the port
        System.out.println("Loaded containers at your port:");
        for (Container container : managedPort.getCurrentContainers()) {
            if (container.getCurrentVehicle() != null) {
                System.out.println(container.getContainerId() + " - " + container.getContainerType());
            }
        }

        // Get input for container id
        System.out.println("Enter the container ID you want to unload:");
        String containerId = scanner.nextLine();
        Container selectedContainer = findContainerById(containerId);

        assert selectedContainer != null;
        Vehicle vehicle = selectedContainer.getCurrentVehicle();
        vehicle.unloadContainer(selectedContainer);
        System.out.println("Container unloaded successfully!");
    }

    private Container findContainerById(String id) {
        for (Container container : managedPort.getCurrentContainers()) {
            if (container.getContainerId().equals(id)) {
                return container;
            }
        }
        return null;
    }

    private Port findPortById(String id) {
        return portServices.getPortById(id);
    }

    private Vehicle findVehicleById(String id) {
        for (Vehicle vehicle : managedPort.getCurrentVehicles()) {
            if (vehicle.getVehicleId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }
}
