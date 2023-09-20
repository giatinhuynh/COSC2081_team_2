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
        clearScreen(); // Clear the screen for a fresh view. This method needs to be implemented.
        displayHeader("ADMIN MENU");
        displayMessage("1. Manage Ports");
        displayMessage("2. Manage Containers");
        displayMessage("3. Manage Vehicles");
        displayMessage("4. Manage Users");
        displayMessage("5. Manage Trips");
        displayMessage("6. Statistics");
        displayMessage("0. Logout");
        displayHorizontalLine();
        int choice = promptForInput("Enter your choice (0-6): ");
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
        clearScreen();
        displayHeader("ADMIN PORTS MENU");
        displayMessage("1. Add Port");
        displayMessage("2. Update Port");
        displayMessage("3. Delete Port");
        displayMessage("4. Search Port");
        displayMessage("5. View All Ports");
        displayMessage("0. Back");
        displayHorizontalLine();
        int choice = promptForInput("Enter your choice (0-5): ");
        switch (choice) {
            case 1 -> {
                adminPortController.createNewPort();
                backToMenu();
                displayAdminPortsMenu();
            }
            case 2 -> {
                adminPortController.updatePort();
                backToMenu();
                displayAdminPortsMenu();
            }
            case 3 -> {
                adminPortController.deletePort();
                backToMenu();
                displayAdminPortsMenu();
            }
            case 4 -> {
                adminPortController.findPort();
                backToMenu();
                displayAdminPortsMenu();
            }
            case 5 -> {
                adminPortController.displayAllPorts();
                backToMenu();
                displayAdminPortsMenu();
            }
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                backToMenu();
                displayAdminPortsMenu();
            }
        }
    }

    public void displayAdminContainersMenu() {
        clearScreen();
        displayHeader("ADMIN CONTAINERS MENU");
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
                backToMenu();
                displayAdminContainersMenu();
            }
            case 2 -> {
                adminContainerController.updateContainer();
                backToMenu();
                displayAdminContainersMenu();
            }
            case 3 -> {
                adminContainerController.deleteContainer();
                backToMenu();
                displayAdminContainersMenu();
            }
            case 4 -> {
                adminContainerController.findContainer();
                backToMenu();
                displayAdminContainersMenu();
            }
            case 5 -> {
                adminContainerController.displayAllContainers();
                backToMenu();
                displayAdminContainersMenu();
            }
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                backToMenu();
                displayAdminContainersMenu();
            }
        }

    }

    public void displayAdminVehiclesMenu() {
        clearScreen();
        displayHeader("ADMIN VEHICLES MENU");
        displayMessage("1. Add Vehicle");
        displayMessage("2. Update Vehicle");
        displayMessage("3. Delete Vehicle");
        displayMessage("4. Search Vehicle");
        displayMessage("5. View All Vehicles");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                adminVehicleController.createNewVehicle();
                backToMenu();
                displayAdminVehiclesMenu();
            }
            case 2 -> {
                adminVehicleController.updateVehicle();
                backToMenu();
                displayAdminVehiclesMenu();
            }
            case 3 -> {
                adminVehicleController.deleteVehicle();
                backToMenu();
                displayAdminVehiclesMenu();
            }
            case 4 -> {
                adminVehicleController.findVehicle();
                backToMenu();
                displayAdminVehiclesMenu();
            }
            case 5 -> {
                adminVehicleController.displayAllVehicles();
                backToMenu();
                displayAdminVehiclesMenu();
            }
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                backToMenu();
                displayAdminVehiclesMenu();
            }
        }
    }

    public void displayAdminUsersMenu() {
        clearScreen();
        displayHeader("ADMIN USERS MENU");
        displayMessage("1. Add Port Manager");
        displayMessage("2. Update Port Manager");
        displayMessage("3. Delete Port Manager");
        displayMessage("4. Search Port Manager");
        displayMessage("5. View All Port Managers");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                adminUserController.createNewUser();
                backToMenu();
                displayAdminUsersMenu();
            }
            case 2 -> {
                adminUserController.updateUser();
                backToMenu();
                displayAdminUsersMenu();
            }
            case 3 -> {
                adminUserController.deleteUser();
                backToMenu();
                displayAdminUsersMenu();
            }
            case 4 -> {
                adminUserController.findUser();
                backToMenu();
                displayAdminUsersMenu();
            }
            case 5 -> {
                adminUserController.displayAllUsers();
                backToMenu();
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
        clearScreen();
        displayHeader("ADMIN TRIPS MENU");
        displayMessage("1. Add Trip");
        displayMessage("2. Update Trip");
        displayMessage("3. Delete Trip");
        displayMessage("4. Search Trip");
        displayMessage("5. View All Trips");
        displayMessage("0. Back");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                adminTripController.createTrip();
                backToMenu();
                displayAdminTripsMenu();
            }
            case 2 -> {
                adminTripController.updateTrip();
                backToMenu();
                displayAdminTripsMenu();
            }
            case 3 -> {
                adminTripController.deleteTrip();
                backToMenu();
                displayAdminTripsMenu();
            }
            case 4 -> {
                adminTripController.findTrip();
                backToMenu();
                displayAdminTripsMenu();
            }
            case 5 -> {
                adminTripController.displayAllTrips();
                backToMenu();
                displayAdminTripsMenu();
            }
            case 0 -> displayAdminMenu();
            default -> {
                displayMessage("Invalid choice. Please try again");
                backToMenu();
                displayAdminTripsMenu();
            }
        }
    }

    public void displayAdminStatisticsMenu () {
        clearScreen();
        displayHeader("ADMIN STATISTICS MENU");
        displayMessage("1. View Port Statistics");
        displayMessage("2. View Container Statistics");
        displayMessage("3. View Vehicle Statistics");
        displayMessage("4. View Trip Statistics");
        displayMessage("0. Back");
    }
}

