package services;

import models.port.Port;
import models.user.PortManager;
import models.user.User;
import utils.Constants;
import database.DatabaseHandler;
import models.container.*;
import models.vehicle.*;
import services.UserServices;

import java.util.*;

public class ContainerServices extends BaseController {

    private final Scanner scanner = new Scanner(System.in);
    private final String CONTAINER_FILE_PATH = Constants.CONTAINER_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private final PortManager currentUser = (PortManager) UserServices.getCurrentUser();
    private final PortServices portServices = new PortServices();

    @Override
    public void create() {
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
            case 1 -> newContainer = new DryStorage(containerId, weight);
            case 2 -> newContainer = new OpenSide(containerId, weight);
            case 3 -> newContainer = new OpenTop(containerId, weight);
            case 4 -> newContainer = new Liquid(containerId, weight);
            case 5 -> newContainer = new Refrigerated(containerId, weight);
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

        dbHandler.writeObjects(CONTAINER_FILE_PATH, containerList.toArray(new Container[0]));
    }

    @Override
    public void displayOne() {
        System.out.println("DISPLAY CONTAINER INFO");
        System.out.print("Enter container ID: ");
        String containerIdToDisplay = scanner.nextLine();

        List<Container> containerList;
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            containerList = new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {
            System.out.println("Error reading containers or no containers exist.");
            return;
        }

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

    @Override
    public void displayAll() {
        System.out.println("DISPLAY ALL CONTAINERS INFO");

        List<Container> containerList;
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            containerList = new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {
            System.out.println("Error reading containers or no containers exist.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-20s | %-15s |\n", "Container ID","Container Type", "Weight");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Container container : containerList) {
            System.out.printf("| %-15s | %-20s | %-15.2f |\n", container.getContainerId(), container.getContainerId(), container.getWeight());
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    @Override
    public void update() {
        System.out.println("CONTAINER UPDATE WIZARD");
        System.out.print("Enter container ID to update: ");
        String containerIdToUpdate = scanner.nextLine();

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


    @Override
    public void delete() {
        System.out.println("CONTAINER DELETE WIZARD");
        System.out.print("Enter container ID to delete: ");
        String containerIdToDelete = scanner.nextLine();

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

    private Container getContainerById(List<Container> containers, String containerId) {
        for (Container container : containers) {
            if (Objects.equals(container.getContainerId(), containerId)) {
                return container;
            }
        }
        return null;
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

    private final Port managedPort = currentUser.getManagedPort();

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
                loadContainerFlow();  // Restart the flow
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
