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

package services.admin;

import database.DatabaseHandler;
import interfaces.CRUD.PortCRUD;
import models.port.Port;
import utils.Constants;
import exceptions.InputValidation;

import java.util.*;

public class PortServicesAdmin extends AdminBaseServices implements PortCRUD {

    private final Scanner scanner = new Scanner(System.in);
    private final String PORT_FILE_PATH = Constants.PORT_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private final InputValidation inputValidation = new InputValidation();


    // Modularized method to fetch ports from the database
    public List<Port> fetchPortsFromDatabase() {
        try {
            Port[] portsArray = (Port[]) dbHandler.readObjects(PORT_FILE_PATH);
            return new ArrayList<>(Arrays.asList(portsArray));
        } catch (Exception e) {  // Catching a generic exception as a placeholder.
            System.out.println("Error reading ports or no ports exist.");
            return new ArrayList<>();
        }
    }

    // Modularized method to write ports to the database
    private void writePortsToDatabase(List<Port> portsList) {
        dbHandler.writeObjects(PORT_FILE_PATH, portsList.toArray(new Port[0]));
    }

    // Modularized method to find a port by its ID
    private Optional<Port> findPortById(String portId) {
        return fetchPortsFromDatabase().stream()
                .filter(port -> port.getPortId().equals(portId))
                .findFirst();
    }

    @Override
    public void createNewPort() {
        clearScreen();  // Optional: Clear the console for a clean UI. Implementation depends on the OS.

        // Header
        System.out.println("======================================");
        System.out.println("           PORT CREATE WIZARD         ");
        System.out.println("======================================");
        System.out.println();  // Blank line for spacing

        String portId = inputValidation.idValidation("P-");
        System.out.println();  // Blank line for spacing

        String name = inputValidation.getString("Enter port name: ");
        System.out.println();  // Blank line for spacing

        double latitude = inputValidation.getDouble("Enter port latitude (e.g., 52.5200): ");
        System.out.println();  // Blank line for spacing

        double longitude = inputValidation.getDouble("Enter port longitude (e.g., 13.4050): ");
        System.out.println();  // Blank line for spacing

        double storingCapacity = inputValidation.getDouble("Enter port storing capacity (in tons): ");
        System.out.println();  // Blank line for spacing

        boolean landingAbility = inputValidation.getBoolean("Does the port have landing ability? (True/False): ");
        System.out.println();  // Blank line for spacing

        Port newPort = new Port(portId, name, latitude, longitude, storingCapacity, landingAbility);

        List<Port> portsList = fetchPortsFromDatabase();
        portsList.add(newPort);
        writePortsToDatabase(portsList);

        // Confirmation message
        System.out.println("======================================");
        System.out.println("   Port successfully created!         ");
        System.out.println("======================================");
    }

    @Override
    public void findPort() {
        clearScreen();
        System.out.println("======================================");
        System.out.println("          DISPLAY PORT INFO           ");
        System.out.println("======================================");
        System.out.println();

        String portIdToDisplay = inputValidation.idValidation("P-");
        System.out.println();

        Optional<Port> optionalPort = findPortById(portIdToDisplay);

        if (optionalPort.isPresent()) {
            Port portToDisplay = optionalPort.get();
            displayPortTableHeader();
            displayPortTableRow(portToDisplay);
            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No port found with the given ID.");
        }
    }

    @Override
    public void displayAllPorts() {
        clearScreen();
        System.out.println("======================================");
        System.out.println("        DISPLAY ALL PORTS INFO        ");
        System.out.println("======================================");
        System.out.println();

        List<Port> portsList = fetchPortsFromDatabase();
        displayPortTableHeader();
        for (Port port : portsList) {
            displayPortTableRow(port);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    @Override
    public void updatePort() {
        clearScreen();
        System.out.println("======================================");
        System.out.println("          PORT UPDATE WIZARD          ");
        System.out.println("======================================");
        System.out.println();

        String portIdToUpdate = inputValidation.idValidation("P-");
        System.out.println();

        List<Port> portsList = fetchPortsFromDatabase();

        Optional<Port> portToUpdateOpt = findPortById(portIdToUpdate);

        if (portToUpdateOpt.isPresent()) {
            Port portToUpdate = portToUpdateOpt.get();

            System.out.println("Enter new port name (leave blank to keep unchanged): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                portToUpdate.setName(name);
            }

            System.out.println("Enter new port latitude (leave blank to keep unchanged): ");
            String latitudeStr = scanner.nextLine();
            if (!latitudeStr.isEmpty()) {
                double latitude = Double.parseDouble(latitudeStr);
                portToUpdate.setLatitude(latitude);
            }

            System.out.println("Enter new port longitude (leave blank to keep unchanged): ");
            String longitudeStr = scanner.nextLine();
            if (!longitudeStr.isEmpty()) {
                double longitude = Double.parseDouble(longitudeStr);
                portToUpdate.setLongitude(longitude);
            }

            System.out.println("Enter new port storing capacity (leave blank to keep unchanged): ");
            String capacityStr = scanner.nextLine();
            if (!capacityStr.isEmpty()) {
                double storingCapacity = Double.parseDouble(capacityStr);
                portToUpdate.setStoringCapacity(storingCapacity);
            }

            System.out.println("Enter new port landing ability (True/False, leave blank to keep unchanged): ");
            String landingAbilityStr = scanner.nextLine();
            if (!landingAbilityStr.isEmpty()) {
                boolean landingAbility = Boolean.parseBoolean(landingAbilityStr);
                portToUpdate.setLandingAbility(landingAbility);
            }

            writePortsToDatabase(portsList);
            System.out.println("Port with ID " + portIdToUpdate + " updated successfully.");
        } else {
            System.out.println("No port found with the given ID.");
        }
    }

    @Override
    public void deletePort() {
        clearScreen();
        System.out.println("======================================");
        System.out.println("          PORT DELETE WIZARD          ");
        System.out.println("======================================");
        System.out.println();

        String portIdToDelete = inputValidation.idValidation("P-");
        System.out.println();

        List<Port> portsList = fetchPortsFromDatabase();
        boolean isDeleted = portsList.removeIf(port -> port.getPortId().equals(portIdToDelete));
        if (isDeleted) {
            writePortsToDatabase(portsList);
            System.out.println("Port with ID " + portIdToDelete + " deleted successfully.");
        } else {
            System.out.println("No port found with the given ID.");
        }
    }


    public Port getPortById(String portId) {
        return findPortById(portId).orElse(null);
    }
}
