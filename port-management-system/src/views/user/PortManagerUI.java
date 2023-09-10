package views.user;

import controllers.UserController;
import models.port.Port;
import models.user.PortManager;

import java.io.IOException;
import java.util.Scanner;

public class PortManagerUI {

    private UserController portManagerController = new UserController();
    private Scanner scanner = new Scanner(System.in);

    // Create a new PortManager
    public void createNewManager() {
        System.out.print("Enter the username for the new manager: ");
        String username = scanner.nextLine();

        System.out.print("Enter the password for the new manager: ");
        String password = scanner.nextLine();

        System.out.print("Enter the port managed by the manager: ");
        Port managedPort = new Port(scanner.nextLine());

        PortManager newManager = new PortManager(username, password, managedPort);
        try {
            portManagerController.create(newManager);
            System.out.println("New manager created successfully!");
        } catch (IOException e) {
            System.out.println("Error while creating the manager: " + e.getMessage());
        }
    }

    // Update an existing PortManager
    public void updateExistingManager() {
        System.out.print("Enter the username of the manager you want to update: ");
        String username = scanner.nextLine();

        try {
            Optional<PortManager> optManager = portManagerController.read(username);
            if (optManager.isPresent()) {
                PortManager manager = optManager.get();

                System.out.println("Updating manager with username: " + username);

                System.out.print("Enter new password for manager (Leave blank to keep unchanged): ");
                String newPassword = scanner.nextLine();
                if (!newPassword.isEmpty()) {
                    manager.setPassword(newPassword);
                }

                portManagerController.update(manager);
                System.out.println("Manager updated successfully!");
            } else {
                System.out.println("Manager not found!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while updating the manager: " + e.getMessage());
        }
    }

    // Delete a PortManager
    public void deleteManager() {
        System.out.print("Enter the username of the manager you want to delete: ");
        String username = scanner.nextLine();

        try {
            portManagerController.delete(username);
            System.out.println("Manager deleted successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while deleting the manager: " + e.getMessage());
        }
    }

    // Search for a PortManager
    public void searchManager() {
        System.out.print("Enter the username of the manager you want to search for: ");
        String username = scanner.nextLine();

        try {
            Optional<PortManager> optManager = portManagerController.read(username);
            if (optManager.isPresent()) {
                PortManager manager = optManager.get();

                System.out.println("Manager details:");
                System.out.println("Username: " + manager.getUsername());
                System.out.println("Role: " + manager.getRole());
                System.out.println("Managed Port: " + manager.getManagedPort().getName());  // Assuming Port has a getName method
            } else {
                System.out.println("Manager not found!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while searching for the manager: " + e.getMessage());
        }
    }

    // View details of all managers
    public void viewAllManagers() {
        try {
            List<PortManager> allManagers = portManagerController.readAll();

            System.out.println("------------------------------------------");
            System.out.printf("| %15s | %15s | %20s |\n", "Username", "Role", "Managed Port");
            System.out.println("------------------------------------------");
            for (PortManager manager : allManagers) {
                System.out.printf("| %15s | %15s | %20s |\n", manager.getUsername(), manager.getRole(), manager.getManagedPort().getName()); // Assuming Port has a getName method
            }
            System.out.println("------------------------------------------");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while retrieving all managers: " + e.getMessage());
        }
    }
}
