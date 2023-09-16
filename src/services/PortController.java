/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2023B
  Assessment: Group Assignment
  Group: Team Hi
  Members:
  Phan Nhat Minh - s3978598
  Huynh Duc Gia Tin - s3818078
  Nguyen Viet Ha - s3978128
  Vu Minh Ha - s3978681
  Created  date: 02/09/2023
  Acknowledgement: chat.openai.com, stackoverflow.com, geeksforgeeks.org, javatpoint.com, tutorialspoint.com, oracle.com, w3schools.com, github.com, codejava.net, baeldung.com, mkyong.com, javacodegeeks.com, journaldev.com
*/

package services;

import database.DatabaseHandler;
import models.port.Port;
import utils.Constants;

import java.util.*;

public class PortController extends BaseController {

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


    @Override
    public void create() {
        System.out.println("PORT CREATE WIZARD");
        System.out.print("Enter port ID: ");
        String portId = scanner.nextLine();
        System.out.print("Enter port name: ");
        String name = scanner.nextLine();
        System.out.print("Enter port latitude: ");
        double latitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter port longitude: ");
        double longitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter port storing capacity: ");
        double storingCapacity = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter port landing ability (True/False): ");
        boolean landingAbility = scanner.nextBoolean();
        scanner.nextLine();  // To consume any leftover newline

        Port newPort = new Port(portId, name, latitude, longitude, storingCapacity, landingAbility);

        List<Port> portsList = fetchPortsFromDatabase();

        portsList.add(newPort);

        dbHandler.writeObjects(PORT_FILE_PATH, portsList.toArray(new Port[0]));
    }

    @Override
    public void displayOne() {
        System.out.println("DISPLAY PORT INFO");
        System.out.print("Enter port ID: ");
        String portIdToDisplay = scanner.nextLine();

        List<Port> portsList = fetchPortsFromDatabase();

        Port portToDisplay = null;
        for (Port port : portsList) {
            if (port.getPortId().equals(portIdToDisplay)) {
                portToDisplay = port;
                break;
            }
        }

        if (portToDisplay != null) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-40s | %-10s | %-10s | %-20s | %-15s |\n", "Port ID", "Name", "Latitude", "Longitude", "Storing Capacity", "Landing Ability");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-40s | %-10.4f | %-10.4f | %,-20.2f | %-15b |\n",
                    portToDisplay.getPortId(), portToDisplay.getName(), portToDisplay.getLatitude(),
                    portToDisplay.getLongitude(), portToDisplay.getStoringCapacity(), portToDisplay.getLandingAbility());
            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No port found with the given ID.");
        }
    }

    @Override
    public void displayAll() {
        System.out.println("DISPLAY ALL PORTS INFO");

        List<Port> portsList = fetchPortsFromDatabase();

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-40s | %-10s | %-10s | %-20s | %-15s |\n", "Port ID", "Name", "Latitude", "Longitude", "Storing Capacity", "Landing Ability");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Port port : portsList) {
            System.out.printf("| %-10s | %-40s | %-10.4f | %-10.4f | %,-20.2f | %-15b |\n",
                    port.getPortId(), port.getName(), port.getLatitude(), port.getLongitude(),
                    port.getStoringCapacity(), port.getLandingAbility());
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    @Override
    public void update() {
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

    @Override
    public void delete() {
        System.out.println("PORT DELETE WIZARD");
        System.out.print("Enter port ID to delete: ");
        String portIdToDelete = scanner.nextLine();

        List<Port> portsList = fetchPortsFromDatabase();

        boolean isDeleted = false;
        Iterator<Port> iterator = portsList.iterator();
        while (iterator.hasNext()) {
            Port port = iterator.next();
            if (port.getPortId().equals(portIdToDelete)) {
                iterator.remove();
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            dbHandler.writeObjects(PORT_FILE_PATH, portsList.toArray(new Port[0]));
            System.out.println("Port with ID " + portIdToDelete + " deleted successfully.");
        } else {
            System.out.println("No port found with the given ID.");
        }
    }

    /**
     * Fetches a port based on its ID.
     *
     * @param portId ID of the port to be fetched.
     * @return Port object if found, null otherwise.
     */
    public Port getPortById(String portId) {
        List<Port> portsList = fetchPortsFromDatabase();
        for (Port port : portsList) {
            if (port.getPortId().equals(portId)) {
                return port;
            }
        }
        return null;
    }
}
