package views.flow;

import services.*;
import views.BaseView;

public class AdminFlow extends BaseView {

    private final PortServices adminPortServices = new PortServices();
    private final VehicleController adminVehicleController = new VehicleController();
    private final ContainerServices adminContainerServices = new ContainerServices();
    private final UserServices adminUserServices = new UserServices();
    private final TripController adminTripController = new TripController();

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
            case 1 -> adminPortServices.create();
            case 2 -> adminPortServices.update();
            case 3 -> adminPortServices.delete();
            case 4 -> adminPortServices.displayOne();
            case 5 -> adminPortServices.displayAll();
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
            case 1 -> adminContainerServices.create();
            case 2 -> adminContainerServices.update();
            case 3 -> adminContainerServices.delete();
            case 4 -> adminContainerServices.displayOne();
            case 5 -> adminContainerServices.displayAll();
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
            case 1 -> adminVehicleController.create();
            case 2 -> adminVehicleController.update();
            case 3 -> adminVehicleController.delete();
            case 4 -> adminVehicleController.displayOne();
            case 5 -> adminVehicleController.displayAll();
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
            case 1 -> adminUserServices.create();
            case 2 -> adminUserServices.update();
            case 3 -> adminUserServices.delete();
            case 4 -> adminUserServices.displayOne();
            case 5 -> adminUserServices.displayAll();
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
            case 1 -> adminTripController.create();
            case 2 -> adminTripController.update();
            case 3 -> adminTripController.delete();
            case 4 -> adminTripController.displayOne();
            case 5 -> adminTripController.displayAll();
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

