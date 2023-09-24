package views.flow;

import views.BaseView;
import services.manager.*;
import utils.UiUtils;
import views.statistics.ManagerStatistics;

import java.text.ParseException;

public class PortManagerFlow extends BaseView {

    private final PortServicesManager portServices = new PortServicesManager();
    private final ContainerServicesManager containerServices = new ContainerServicesManager();
    private final VehicleServicesManager vehicleServices = new VehicleServicesManager();
    private final ManagerStatistics statistics = new ManagerStatistics();
    private final UiUtils uiUtils = new UiUtils();

    public void PortManagerMenu() throws ParseException {
        uiUtils.clearScreen();
        System.out.println("Welcome, Port Manager!");
        displayMenuHeader("PORT MANAGER MENU");
        displayMessage("1. Manage Containers");
        displayMessage("2. Manage Vehicles");
        displayMessage("3. View Port Details");
        displayMessage("4. Update Port Details");
        displayMessage("5. View Statistics");
        displayMessage("0. Logout");

        int choice = promptForInput("Enter you choice: ");
        switch (choice) {
            case 1 -> PManagerContainerMenu();
            case 2 -> PManagerVehicleMenu();
            case 3 -> {
                portServices.viewPortInfo();
                backToMenu();
                PortManagerMenu();
            }
            case 4 -> {
                portServices.updatePortInfo();
                backToMenu();
                PortManagerMenu();
            }
            case 5 -> PManagerStatisticsMenu();
            case 0 -> logoutView();
            default -> {
                uiUtils.printFailedMessage("Invalid choice. Please try again.");
                PortManagerMenu();
            }
        }
    }

    public void PManagerContainerMenu() throws ParseException {
        uiUtils.clearScreen();
        displayMessage("=== PORT MANAGER CONTAINER MENU ===");
        displayMessage("1. Load Container");
        displayMessage("2. Unload Container");
        displayMessage("===================================");
        displayMessage("3. Add Existing Container");
        displayMessage("4. Add New Container");
        displayMessage("5. Update Container");
        displayMessage("6. Delete Container");
        displayMessage("7. Search Container");
        displayMessage("8. View All Containers");
        displayMessage("0. Back");

        int choice = promptForInput("Enter you choice: ");
        switch (choice) {
            case 1 -> {
                containerServices.loadContainerFlow();
                backToMenu();
                PManagerContainerMenu();
            }
            case 2 -> {
                containerServices.unloadContainerFlow();
                backToMenu();
                PManagerContainerMenu();
            }
            case 3 -> {
                containerServices.addExistingContainer();
                backToMenu();
                PManagerContainerMenu();
            }
            case 4 -> {
                containerServices.createNewContainer();
                backToMenu();
                PManagerContainerMenu();
            }
            case 5 -> {
                containerServices.updateContainer();
                backToMenu();
                PManagerContainerMenu();
            }
            case 6 -> {
                containerServices.deleteContainer();
                backToMenu();
                PManagerContainerMenu();
            }
            case 7 -> {
                containerServices.findContainer();
                backToMenu();
                PManagerContainerMenu();

            }
            case 8 -> {
                containerServices.displayAllContainers();
                backToMenu();
                PManagerContainerMenu();
            }
            case 0 -> PortManagerMenu();
            default -> {
                uiUtils.printFailedMessage("Invalid choice. Please try again.");
                PManagerContainerMenu();
            }
        }
    }

    public void PManagerVehicleMenu() throws ParseException {
        uiUtils.clearScreen();
        displayMessage("=== PORT MANAGER VEHICLE MENU ===");
        displayMessage("1. Deploy Vehicle (Create Trip)");
        displayMessage("2. Refuel Vehicle");
        displayMessage("=================================");
        displayMessage("3. Add Existing Vehicle");
        displayMessage("4. Create New Vehicle");
        displayMessage("5. Update Vehicle");
        displayMessage("6. Delete Vehicle");
        displayMessage("7. Search Vehicle");
        displayMessage("8. View All Vehicles");
        displayMessage("0. Back");

        int choice = promptForInput("Enter you choice: ");
        switch (choice) {
            case 1 -> {
                vehicleServices.deployVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 2 -> {
                vehicleServices.refuelVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 3 -> {
                vehicleServices.addExistingVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 4 -> {
                vehicleServices.createNewVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 5 -> {
                vehicleServices.updateVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 6 -> {
                vehicleServices.deleteVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 7 -> {
                vehicleServices.findVehicle();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 8 -> {
                vehicleServices.displayAllVehicles();
                backToMenu();
                PManagerVehicleMenu();
            }
            case 0 -> PortManagerMenu();
            default -> {
                uiUtils.printFailedMessage("Invalid choice. Please try again.");
                PManagerVehicleMenu();
            }
        }
    }

    public void PManagerStatisticsMenu() throws ParseException {
        uiUtils.clearScreen();
        displayMessage("=== PORT MANAGER STATISTICS MENU ===");
        displayMessage("1. Daily Fuel Usage");
        displayMessage("2. Weight of each type of container");
        displayMessage("3. List ships in a port");
        displayMessage("4. List trips in a day");
        displayMessage("0. Back");

        int choice = promptForInput("Enter you choice: ");
        switch (choice) {
            case 1 -> {
                statistics.displayFuelUsedInDay();
                backToMenu();
                PManagerStatisticsMenu();
            }
            case 2 -> {
                statistics.displayWeightOfContainerByType();
                backToMenu();
                PManagerStatisticsMenu();
            }
            case 3 -> {
                statistics.displayAllShipsInPort();
                backToMenu();
                PManagerStatisticsMenu();
            }
            case 4 -> {
                statistics.displayAllTripsInDuration();
                backToMenu();
                PManagerStatisticsMenu();
            }
            case 0 -> PortManagerMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                PManagerStatisticsMenu();
            }
        }
    }
}

