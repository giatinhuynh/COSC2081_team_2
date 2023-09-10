package controllers;

import database.DatabaseHandler;
import interfaces.ICRUD;
import models.port.Port;
import utils.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PortController extends BaseController implements ICRUD {

    private final DatabaseHandler dbHandler;
    private final String PORT_DATA_PATH = Constants.PORTS_FILE_PATH;
    private final Scanner scanner = new Scanner(System.in);

    public PortController() {
        this.dbHandler = new DatabaseHandler();
    }

    @Override
    public void create() {
        System.out.println("Creating a new port wizard");

        System.out.print("Enter port ID: ");
        String portId = scanner.nextLine();

        System.out.print("Enter port name: ");
        String portName = scanner.nextLine();

        System.out.print("Enter port latitude: ");
        double portLatitude = scanner.nextDouble();

        System.out.print("Enter port longitude: ");
        double portLongitude = scanner.nextDouble();

        System.out.print("Enter port storing capacity: ");
        double portStoringCapacity = scanner.nextDouble();

        System.out.print("Enter port landing ability: ");
        boolean portLandingAbility = scanner.nextBoolean();
        // Example: This would usually take an input, e.g., from a GUI or console
        Port newPort = new Port(portId, portName, portLatitude, portLongitude, portStoringCapacity, portLandingAbility);
        try {
            dbHandler.writeToFile(PORT_DATA_PATH, newPort);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void read() {
        try {
            Port readPort = (Port) dbHandler.readFromFile(PORT_DATA_PATH, );
            // Usually, you'd return this object, or process it, or display it
            System.out.println(readPort.getName()); // Just a demonstration
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void updateExistingPort() {
        System.out.print("Enter the ID of the port you want to update: ");
        String portId = scanner.nextLine();

        Port port = dbManager.getPortById(portId);

        if (port == null) {
            System.out.println("Port not found!");
            return;
        }

        System.out.println("Updating port with ID: " + portId);

        System.out.print("Enter new name for port (Leave blank to keep unchanged): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            port.setName(newName);
        }

        System.out.print("Enter new latitude for port (Leave blank to keep unchanged): ");
        String newLatitude = scanner.nextLine();
        if (!newLatitude.isEmpty()) {
            port.setLatitude(Double.parseDouble(newLatitude));
        }

        System.out.print("Enter new longitude for port (Leave blank to keep unchanged): ");
        String newLongitude = scanner.nextLine();
        if (!newLongitude.isEmpty()) {
            port.setLongitude(Double.parseDouble(newLongitude));
        }

        System.out.print("Enter new storing capacity for port (Leave blank to keep unchanged): ");
        String newStoringCapacity = scanner.nextLine();
        if (!newStoringCapacity.isEmpty()) {
            port.setStoringCapacity(Double.parseDouble(newStoringCapacity));
        }

        System.out.print("Enter new landing ability for port (Leave blank to keep unchanged): ");
        String newLandingAbility = scanner.nextLine();
        if (!newLandingAbility.isEmpty()) {
            port.setLandingAbility(Boolean.parseBoolean(newLandingAbility));
        }

        dbManager.updatePort(port);
        System.out.println("Port updated successfully!");
    }

    public void deletePort() {
        System.out.print("Enter the ID of the port you want to delete: ");
        String portId = scanner.nextLine();

        Port port = dbManager.getPortById(portId);
        if (port == null) {
            System.out.println("Port not found!");
            return;
        }

        dbManager.deletePort(portId);
        System.out.println("Port deleted successfully!");
    }

    public void searchPort() {
        System.out.print("Enter the ID of the port you want to search for: ");
        String portId = scanner.nextLine();

        Port port = dbManager.getPortById(portId);
        if (port == null) {
            System.out.println("Port not found!");
            return;
        }

        // Display port details in tabular format
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %10s | %20s |\n", "Port ID", "Port Name");
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %10s | %20s |\n", port.getPortId(), port.getName());
        System.out.println("---------------------------------------------------------");
    }

    public void viewAllPorts() {
        List<Port> allPorts = dbManager.getAllPorts();

        System.out.println("---------------------------------------------------------");
        System.out.printf("| %10s | %20s |\n", "Port ID", "Port Name");
        System.out.println("---------------------------------------------------------");

        for (Port port : allPorts) {
            System.out.printf("| %10s | %20s |\n", port.getPortId(), port.getName());
        }

        System.out.println("---------------------------------------------------------");
    }
}

