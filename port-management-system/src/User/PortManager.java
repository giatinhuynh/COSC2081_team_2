package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Container.*;
import Trip.Trip;

/**
 * Represents a Port Manager who manages containers and trips at a port.
 * This class extends the User class and implements the IPortManager interface.
 */
public class PortManager extends User implements IPortManager {

    private String portId;  // Identifier for the port the manager is responsible for
    private List<Container> containerList = new ArrayList<>();  // List of containers managed by this port manager
    private List<Trip> tripList = new ArrayList<>();  // List of trips managed by this port manager

    /**
     * Constructs a new Port Manager.
     *
     * @param username The username for the Port Manager
     * @param password The password for the Port Manager
     * @param portId   The identifier for the port the manager is responsible for
     */
    public PortManager(String username, String password, String portId) {
        super(username, password);
        this.portId = portId;
        loadContainers();  // Load existing containers from file
    }

    /**
     * Private method to load existing containers from a file.
     */
    private void loadContainers() {
        try {
            File file = new File("Database/containers.txt");  // Data source for containers
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                // Parse the container attributes from the line
                double weight = Double.parseDouble(parts[2]);
                // Logic to create appropriate type of Container instance based on the data
                switch (parts[1]) {
                    // Create specific type of Container based on type identifier
                    case "DryStorage":
                        containerList.add(new DryStorage(parts[0], weight));
                        break;
                    // (Similar cases for other container types)
                    // ...
                    default:
                        System.out.println("Unknown container type: " + parts[1]);
                        break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    /**
     * Private method to save the current list of containers back to the file.
     */
    private void saveContainers() {
        try {
            FileWriter writer = new FileWriter("Database/containers.txt");
            for (Container container : containerList) {
                // Convert each Container object to its string representation and write to file
                writer.write(container.getId() + "," + container.getClass().getSimpleName() + "," + container.getWeight() + "," + container.getFuelConsumptionPerKmForShip() + "," + container.getFuelConsumptionPerKmForTruck() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * Adds a new container to the managed list and saves it to the file.
     *
     * @param container The new Container object to add
     */
    @Override
    public void createContainer(Container container) {
        containerList.add(container);
        saveContainers();  // Save updated list to file
    }

    /**
     * Finds and returns a container by its identifier.
     *
     * @param id The identifier of the Container to find
     * @return The found Container object, or null if not found
     */
    @Override
    public Container readContainer(String id) {
        for (Container container : containerList) {
            if (container.getId().equals(id)) {
                return container;
            }
        }
        return null;  // Container not found
    }

    /**
     * Updates an existing container's details and saves it to the file.
     *
     * @param container The Container object with updated details
     */
    @Override
    public void updateContainer(Container container) {
        for (int i = 0; i < containerList.size(); i++) {
            if (containerList.get(i).getId().equals(container.getId())) {
                containerList.set(i, container);  // Update the container details
                break;
            }
        }
        saveContainers();  // Save updated list to file
    }

    /**
     * Deletes a container by its identifier and saves the updated list to the file.
     *
     * @param id The identifier of the Container to delete
     */
    @Override
    public void deleteContainer(String id) {
        containerList.removeIf(container -> container.getId().equals(id));  // Remove the container by id
        saveContainers();  // Save updated list to file
    }

    /**
     * Lists all trips managed by this port manager.
     *
     * @return List of Trip objects
     */
    @Override
    public List<Trip> listTrips() {
        return tripList;  // Return list of managed trips
    }
}
