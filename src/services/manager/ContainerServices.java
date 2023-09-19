package services.manager;

import database.DatabaseHandler;
import interfaces.manager.ManagerContainerInterface;
import models.container.*;
import models.port.Port;
import models.user.PortManager;
import models.vehicle.Ship;
import models.vehicle.Vehicle;
import services.PortController;
import utils.Constants;
import utils.CurrentUser;

import java.util.*;

public class ContainerServices extends BaseServices implements ManagerContainerInterface {

    private final PortController portServices = new PortController();
    private final Port managedPort;
    private final Scanner scanner = new Scanner(System.in);
    private final String CONTAINER_FILE_PATH = Constants.CONTAINER_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();

    public ContainerServices() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    public void loadContainerFlow() {
        // Display all containers at the managed port
        System.out.println("Containers at your port:");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-20s | %-15s |\n", "Container ID", "Container Type", "Weight");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Container container : managedPort.getCurrentContainers()) {
            System.out.printf("| %-15s | %-20s | %-15.2f |\n", container.getContainerId(), container.getContainerType(), container.getWeight());
        }
        System.out.println("--------------------------------------------------------------------------------------");


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

    public void createNewContainer() {
        System.out.println("CONTAINER CREATE WIZARD");
        System.out.print("Enter container ID: ");
        String containerId = scanner.nextLine();
        System.out.print("Enter container weight: ");
        double weight = scanner.nextDouble();
        System.out.println("Select container type:");
        System.out.println("1. Dry storage");
        System.out.println("2. Open Side");
        System.out.println("3. Open Top");
        System.out.println("4. Liquid");
        System.out.println("5. Refrigerated");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // To consume any leftover newline
        Container newContainer = null;
        switch (choice) {
            case 1 -> newContainer = new DryStorage(containerId, weight, managedPort);
            case 2 -> newContainer = new OpenSide(containerId, weight, managedPort);
            case 3 -> newContainer = new OpenTop(containerId, weight, managedPort);
            case 4 -> newContainer = new Liquid(containerId, weight, managedPort);
            case 5 -> newContainer = new Refrigerated(containerId, weight, managedPort);
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        List<Container> containerList;
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            containerList = new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {
            containerList = new ArrayList<>();
        }

        containerList.add(newContainer);
        managedPort.getCurrentContainers().add(newContainer);

        dbHandler.writeObjects(CONTAINER_FILE_PATH, containerList.toArray(new Container[0]));
    }

    public void findContainer() {
        System.out.println("DISPLAY CONTAINER INFO");
        System.out.print("Enter container ID: ");
        String containerIdToDisplay = scanner.nextLine();

        List<Container> containerList = managedPort.getCurrentContainers();

        Container containerToDisplay = null;
        for (Container container : containerList) {
            if (container.getContainerId().equals(containerIdToDisplay)) {
                containerToDisplay = container;
                break;
            }
        }

        if (containerToDisplay != null) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("| %-15s | %-20s | %-15s |\n", "Container ID", "Container Type", "Weight");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("| %-15s | %-20s | %-15.2f |\n", containerToDisplay.getContainerId(), containerToDisplay.getContainerType(), containerToDisplay.getWeight());
            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No container found with the given ID.");
        }
    }

    public void displayAllContainers() {
        System.out.println("DISPLAY ALL CONTAINERS INFO");

        List<Container> containerList = managedPort.getCurrentContainers();

        displayContainers(containerList);
    }

    private void displayContainers(List<Container> containers) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-20s | %-15s |\n", "Container ID", "Container Type", "Weight");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Container container : containers) {
            System.out.printf("| %-15s | %-20s | %-15.2f |\n", container.getContainerId(), container.getContainerType(), container.getWeight());
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void updateContainer() {
        System.out.println("CONTAINER UPDATE WIZARD");
        System.out.print("Enter container ID to update: ");
        String containerIdToUpdate = scanner.nextLine();

        // Check if the container is in the managedPort
        Container managedPortContainer = null;
        for (Container container : managedPort.getCurrentContainers()) {
            if (container.getContainerId().equals(containerIdToUpdate)) {
                managedPortContainer = container;
                break;
            }
        }

        if (managedPortContainer == null) {
            System.out.println("Container not found in the managed port.");
            return;
        }

        List<Container> containerList;
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            containerList = new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {
            System.out.println("Error reading containers or no containers exist.");
            return;
        }

        Container containerToUpdate = null;
        int indexToUpdate = -1;  // new variable to keep track of the index in the list
        for (int i = 0; i < containerList.size(); i++) {
            if (containerList.get(i).getContainerId().equals(containerIdToUpdate)) {
                containerToUpdate = containerList.get(i);
                indexToUpdate = i;
                break;
            }
        }

        if (containerToUpdate != null) {
            System.out.print("Enter new container weight (leave blank to keep unchanged): ");
            String weightStr = scanner.nextLine();
            double newWeight = !weightStr.isEmpty() ? Double.parseDouble(weightStr) : containerToUpdate.getWeight();

            System.out.println("Select new container type (or leave blank to keep unchanged):");
            System.out.println("1. Dry storage");
            System.out.println("2. Open Side");
            System.out.println("3. Open Top");
            System.out.println("4. Liquid");
            System.out.println("5. Refrigerated storage");
            System.out.print("Enter your choice: ");
            String typeChoiceStr = scanner.nextLine();

            if (!typeChoiceStr.isEmpty()) {
                int typeChoice = Integer.parseInt(typeChoiceStr);
                switch (typeChoice) {
                    case 1 -> containerToUpdate = new DryStorage(containerIdToUpdate, newWeight);
                    case 2 -> containerToUpdate = new OpenSide(containerIdToUpdate, newWeight);
                    case 3 -> containerToUpdate = new OpenTop(containerIdToUpdate, newWeight);
                    case 4 -> containerToUpdate = new Liquid(containerIdToUpdate, newWeight);
                    case 5 -> containerToUpdate = new Refrigerated(containerIdToUpdate, newWeight);
                    default -> {
                        System.out.println("Invalid choice.");
                        return;
                    }
                }
                containerList.set(indexToUpdate, containerToUpdate);  // Replace the old container with the updated one
            } else if (!weightStr.isEmpty()) {
                containerToUpdate.setWeight(newWeight);
            }

            dbHandler.writeObjects(CONTAINER_FILE_PATH, containerList.toArray(new Container[0]));
            System.out.println("Container with ID " + containerIdToUpdate + " updated successfully.");
        } else {
            System.out.println("No container found with the given ID.");
        }
    }

    public void deleteContainer() {
        System.out.println("CONTAINER DELETE WIZARD");
        System.out.print("Enter container ID to delete: ");
        String containerIdToDelete = scanner.nextLine();

        // Check if the container is in the managedPort
        boolean existsInManagedPort = false;
        for (Container container : managedPort.getCurrentContainers()) {
            if (container.getContainerId().equals(containerIdToDelete)) {
                existsInManagedPort = true;
                break;
            }
        }

        if (!existsInManagedPort) {
            System.out.println("Container not found in the managed port.");
            return;
        }

        List<Container> containerList;
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            containerList = new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {
            System.out.println("Error reading containers or no containers exist.");
            return;
        }

        boolean isDeleted = false;
        Iterator<Container> iterator = containerList.iterator();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            if (container.getContainerId().equals(containerIdToDelete)) {
                iterator.remove();
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            dbHandler.writeObjects(CONTAINER_FILE_PATH, containerList.toArray(new Container[0]));
            System.out.println("Container with ID " + containerIdToDelete + " deleted successfully.");
        } else {
            System.out.println("No container found with the given ID.");
        }
    }
}
