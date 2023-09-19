package views.flow;

import services.admin.*;
import views.BaseView;

public class AdminFlow extends BaseView {

    private final PortServicesAdmin adminPortController = new PortServicesAdmin();
    private final VehicleServicesAdmin adminVehicleController = new VehicleServicesAdmin();
    private final ContainerServicesAdmin adminContainerController = new ContainerServicesAdmin();
    private final UserServicesAdmin adminUserController = new UserServicesAdmin();
    private final TripServicesAdmin adminTripController = new TripServicesAdmin();

    public void displayAdminMenu() {
        displayMessage("=== ADMIN MENU ===");
        displayMessage("1. Manage Ports");
        displayMessage("2. Manage Containers");
        displayMessage("3. Manage Vehicles");
        displayMessage("4. Manage Users");
        displayMessage("5. Manage Trips");
        displayMessage("6. Statistics");
        displayMessage("0. Logout");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> displayAdminPortsMenu();
            case 2 -> displayAdminContainersMenu();
            case 3 -> displayAdminVehiclesMenu();
            case 4 -> displayAdminUsersMenu();
            case 5 -> displayAdminTripsMenu();
            case 6 -> displayAdminStatisticsMenu();
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
            case 1 -> {
                adminPortController.createNewPort();
                displayAdminPortsMenu();
            }
            case 2 -> {
                adminPortController.updatePort();
                displayAdminPortsMenu();
            }
            case 3 -> {
                adminPortController.deletePort();
                displayAdminPortsMenu();
            }
            case 4 -> {
                adminPortController.findPort();
                displayAdminPortsMenu();
            }
            case 5 -> {
                adminPortController.displayAllPorts();
                displayAdminPortsMenu();
            }
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
            case 1 -> {
                adminContainerController.createNewContainer();
                displayAdminContainersMenu();
            }
            case 2 -> {
                adminContainerController.updateContainer();
                displayAdminContainersMenu();
            }
            case 3 -> {
                adminContainerController.deleteContainer();
                displayAdminContainersMenu();
            }
            case 4 -> {
                adminContainerController.findContainer();
                displayAdminContainersMenu();
            }
            case 5 -> {
                adminContainerController.displayAllContainers();
                displayAdminContainersMenu();
            }
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
            case 1 -> {
                adminVehicleController.create();
                displayAdminVehiclesMenu();
            }
            case 2 -> {
                adminVehicleController.update();
                displayAdminVehiclesMenu();
            }
            case 3 -> {
                adminVehicleController.delete();
                displayAdminVehiclesMenu();
            }
            case 4 -> {
                adminVehicleController.displayOne();
                displayAdminVehiclesMenu();
            }
            case 5 -> {
                adminVehicleController.displayAll();
                displayAdminVehiclesMenu();
            }
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
            case 1 -> {
                adminUserController.create();
                displayAdminUsersMenu();
            }
            case 2 -> {
                adminUserController.update();
                displayAdminUsersMenu();
            }
            case 3 -> {
                adminUserController.delete();
                displayAdminUsersMenu();
            }
            case 4 -> {
                adminUserController.displayOne();
                displayAdminUsersMenu();
            }
            case 5 -> {
                adminUserController.displayAll();
                displayAdminUsersMenu();
            }
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again");
                displayAdminUsersMenu();
            }
        }
    }

    public void displayAdminTripsMenu() {
        displayMessage("=== ADMIN TRIPS MENU ===");
        displayMessage("1. Add Trip");
        displayMessage("2. Update Trip");
        displayMessage("3. Delete Trip");
        displayMessage("4. Search Trip");
        displayMessage("5. View All Trips");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                adminTripController.create();
                displayAdminTripsMenu();
            }
            case 2 -> {
                adminTripController.update();
                displayAdminTripsMenu();
            }
            case 3 -> {
                adminTripController.delete();
                displayAdminTripsMenu();
            }
            case 4 -> {
                adminTripController.displayOne();
                displayAdminTripsMenu();
            }
            case 5 -> {
                adminTripController.displayAll();
                displayAdminTripsMenu();
            }
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again");
                displayAdminTripsMenu();
            }
        }
    }

    public void displayAdminStatisticsMenu () {

    }
}

