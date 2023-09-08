package views;

import controllers.*;

public class AdminView extends BaseView {

    private final PortController adminPortController = new PortController();
    private final ContainerController adminContainerController = new ContainerController();
    private final VehicleController adminVehicleController = new VehicleController();
    private final UserController adminUserController = new UserController();

    public void displayAdminMenu() {
        displayMessage("=== ADMIN MENU ===");
        displayMessage("1. Manage Ports");
        displayMessage("2. Manage Containers");
        displayMessage("3. Manage Vehicles");
        displayMessage("4. Manage Users");
        displayMessage("0. Logout");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> displayAdminPortsMenu();
            case 2 -> displayAdminContainersMenu();
            case 3 -> displayAdminVehiclesMenu();
            case 4 -> displayAdminUsersMenu();
            case 0 -> logoutView();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                displayAdminMenu();
            }
        }
    }

    public void displayAdminPortsMenu() {
        displayMessage("=== ADMIN PORTS MENU ===");
        displayMessage("1. Add Port");
        displayMessage("2. Update Port");
        displayMessage("3. Delete Port");
        displayMessage("4. Search Port");
        displayMessage("5. View All Ports");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> adminPortController.createNewPort();
            case 2 -> adminPortController.updateExistingPort();
            case 3 -> adminPortController.deletePort();
            case 4 -> adminPortController.searchPort();
            case 5 -> adminPortController.viewAllPorts();
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                displayAdminPortsMenu();
            }
        }
    }

    public void displayAdminContainersMenu() {
        displayMessage("=== ADMIN CONTAINERS MENU ===");
        displayMessage("1. Add Container");
        displayMessage("2. Update Container");
        displayMessage("3. Delete Container");
        displayMessage("4. Search Container");
        displayMessage("5. View All Containers");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> adminContainerController.createNewContainer();
            case 2 -> adminContainerController.updateExistingContainer();
            case 3 -> adminContainerController.deleteContainer();
            case 4 -> adminContainerController.searchContainer();
            case 5 -> adminContainerController.viewAllContainers();
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                displayAdminContainersMenu();
            }
        }

    }

    public void displayAdminVehiclesMenu() {
        displayMessage("=== ADMIN VEHICLES MENU ===");
        displayMessage("1. Add Vehicle");
        displayMessage("2. Update Vehicle");
        displayMessage("3. Delete Vehicle");
        displayMessage("4. Search Vehicle");
        displayMessage("5. View All Vehicles");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> adminVehicleController.createNewVehicle();
            case 2 -> adminVehicleController.updateExistingVehicle();
            case 3 -> adminVehicleController.deleteVehicle();
            case 4 -> adminVehicleController.searchVehicle();
            case 5 -> adminVehicleController.viewAllVehicles();
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                displayAdminVehiclesMenu();
            }
        }
    }

    public void displayAdminUsersMenu() {
        displayMessage("=== ADMIN PORT MANAGERS MENU ===");
        displayMessage("1. Add Port Manager");
        displayMessage("2. Update Port Manager");
        displayMessage("3. Delete Port Manager");
        displayMessage("4. Search Port Manager");
        displayMessage("5. View All Port Managers");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> adminUserController.createNewManager();
            case 2 -> adminUserController.updateExistingManager();
            case 3 -> adminUserController.deleteManager();
            case 4 -> adminUserController.searchManager();
            case 5 -> adminUserController.viewAllManagers();
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again");
                displayAdminUsersMenu();
            }
        }
    }

}

