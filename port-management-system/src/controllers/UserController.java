package controllers;

import database.DatabaseManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import models.user.*;
import models.port.Port;

public class UserController extends BaseController {

    private DatabaseManager dbManager;
    private Scanner scanner;

    public User loginUser(String username, String password) {
        try {
            // Here, assuming users are stored in a HashMap with username as key for simplicity
            HashMap<String, User> users = DatabaseManager.loadHashMap("users.dat");

            User user = users.get(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
        }
        return null;
    }

    // Create a new PortManager
    public void createNewManager() {
        System.out.print("Enter the username for the new manager: ");
        String username = scanner.nextLine();

        System.out.print("Enter the password for the new manager: ");
        String password = scanner.nextLine();

        // You'll need to create a method or logic to retrieve or create a port for the manager.
        System.out.print("Enter the port managed by the manager: ");
        Port managedPort = new Port(scanner.nextLine());

        PortManager newManager = new PortManager(username, password, managedPort);
        dbManager.savePortManager(newManager);
        System.out.println("New manager created successfully!");
    }

    // Update an existing PortManager
    public void updateExistingManager() {
        System.out.print("Enter the username of the manager you want to update: ");
        String username = scanner.nextLine();

        PortManager manager = dbManager.getPortManagerByUsername(username);

        if (manager == null) {
            System.out.println("Manager not found!");
            return;
        }

        System.out.println("Updating manager with username: " + username);

        System.out.print("Enter new password for manager (Leave blank to keep unchanged): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            // Assuming PortManager class has a setPassword method.
            manager.setPassword(newPassword);
        }

        // Add more fields here if needed, such as updating the managed port.

        dbManager.updatePortManager(manager);
        System.out.println("Manager updated successfully!");
    }

    // Delete a PortManager
    public void deleteManager() {
        System.out.print("Enter the username of the manager you want to delete: ");
        String username = scanner.nextLine();

        dbManager.deletePortManager(username);
        System.out.println("Manager deleted successfully!");
    }

    // Search for a PortManager
    public void searchManager() {
        System.out.print("Enter the username of the manager you want to search for: ");
        String username = scanner.nextLine();

        PortManager manager = dbManager.getPortManagerByUsername(username);
        if (manager == null) {
            System.out.println("Manager not found!");
            return;
        }

        System.out.println("Manager details:");
        System.out.println("Username: " + manager.getUsername());
        System.out.println("Role: " + manager.getRole());
        System.out.println("Managed Port: " + manager.getManagedPort().getName());  // Assuming Port has a getName method
    }

    // View details of all managers
    public void viewAllManagers() {
        List<PortManager> allManagers = dbManager.getAllPortManagers();

        System.out.println("------------------------------------------");
        System.out.printf("| %15s | %15s | %20s |\n", "Username", "Role", "Managed Port");
        System.out.println("------------------------------------------");
        for (PortManager manager : allManagers) {
            System.out.printf("| %15s | %15s | %20s |\n", manager.getUsername(), manager.getRole(), manager.getManagedPort().getName()); // Assuming Port has a getName method
        }
        System.out.println("------------------------------------------");
    }
}

