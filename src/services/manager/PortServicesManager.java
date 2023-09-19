package services.manager;

import database.DatabaseHandler;
import interfaces.manager.ManagerPortInterface;
import models.port.Port;
import models.user.PortManager;
import utils.Constants;
import utils.CurrentUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PortServicesManager extends ManagerBaseServices implements ManagerPortInterface {

    private final Port managedPort;
    private final Scanner scanner = new Scanner(System.in);
    private final String PORT_FILE_PATH = Constants.PORT_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();

    // Modularized method to fetch ports from the database
    private List<Port> fetchPortsFromDatabase() {
        try {
            Port[] portsArray = (Port[]) dbHandler.readObjects(PORT_FILE_PATH);
            return new ArrayList<>(Arrays.asList(portsArray));
        } catch (Exception e) {  // Catching a generic exception as a placeholder.
            System.out.println("Error reading ports or no ports exist.");
            return new ArrayList<>();
        }
    }

    public PortServicesManager() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    public void viewPortInfo() {
        System.out.println("Port Name: " + managedPort.getName());
        System.out.println("Port Latitude: " + managedPort.getLatitude());
        System.out.println("Port Longitude: " + managedPort.getLongitude());
        System.out.println("Port Storing Capacity: " + managedPort.getStoringCapacity());
        System.out.println("Port Landing Ability: " + managedPort.getLandingAbility());
    }

    public void updatePortInfo() {
        System.out.println("PORT UPDATE WIZARD");
        System.out.print("Enter port ID to update: ");
        String portIdToUpdate = scanner.nextLine();

        List<Port> portsList = fetchPortsFromDatabase();

        Port portToUpdate = null;
        for (Port port : portsList) {
            if (port.getPortId().equals(portIdToUpdate)) {
                portToUpdate = port;
                break;
            }
        }

        if (portToUpdate != null) {
            System.out.print("Enter new port name (leave blank to keep unchanged): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                portToUpdate.setName(name);
            }

            System.out.print("Enter new port latitude (leave blank to keep unchanged): ");
            String latitudeStr = scanner.nextLine();
            if (!latitudeStr.isEmpty()) {
                double latitude = Double.parseDouble(latitudeStr);
                portToUpdate.setLatitude(latitude);
            }

            System.out.print("Enter new port longitude (leave blank to keep unchanged): ");
            String longitudeStr = scanner.nextLine();
            if (!longitudeStr.isEmpty()) {
                double longitude = Double.parseDouble(longitudeStr);
                portToUpdate.setLongitude(longitude);
            }

            System.out.print("Enter new port storing capacity (leave blank to keep unchanged): ");
            String capacityStr = scanner.nextLine();
            if (!capacityStr.isEmpty()) {
                double storingCapacity = Double.parseDouble(capacityStr);
                portToUpdate.setStoringCapacity(storingCapacity);
            }

            System.out.print("Enter new port landing ability (True/False, leave blank to keep unchanged): ");
            String landingAbilityStr = scanner.nextLine();
            if (!landingAbilityStr.isEmpty()) {
                boolean landingAbility = Boolean.parseBoolean(landingAbilityStr);
                portToUpdate.setLandingAbility(landingAbility);
            }

            dbHandler.writeObjects(PORT_FILE_PATH, portsList.toArray(new Port[0]));
            System.out.println("Port with ID " + portIdToUpdate + " updated successfully.");
        } else {
            System.out.println("No port found with the given ID.");
        }
    }
}
