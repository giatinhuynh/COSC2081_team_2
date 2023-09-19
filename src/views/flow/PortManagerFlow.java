package views.flow;

import views.BaseView;
import services.manager.*;

public class PortManagerFlow extends BaseView {

    private final PortServicesManager portServices = new PortServicesManager();
    private final ContainerServicesManager containerServices = new ContainerServicesManager();

    public void PortManagerMenu() {
        displayMessage("=== PORT MANAGER MENU ===");
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
            case 3 -> portServices.viewPortInfo();
            case 4 -> portServices.updatePortInfo();
            case 0 -> logoutView();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                PortManagerMenu();
            }
        }
    }

    public void PManagerContainerMenu() {
        displayMessage("=== PORT MANAGER CONTAINER MENU ===");
        displayMessage("1. Load Container");
        displayMessage("2. Unload Container");
        displayMessage("===================================");
        displayMessage("3. Add Container");
        displayMessage("4. Update Container");
        displayMessage("5. Delete Container");
        displayMessage("6. Search Container");
        displayMessage("7. View All Containers");
        displayMessage("0. Back");

        int choice = promptForInput("Enter you choice: ");
        switch (choice) {
            case 1 -> containerServices.loadContainerFlow();
            case 2 -> containerServices.unloadContainerFlow();
            case 3 -> containerServices.createNewContainer();
            case 4 -> containerServices.updateContainer();
            case 5 -> containerServices.deleteContainer();
            case 6 -> containerServices.findContainer();
            case 7 -> containerServices.displayAllContainers();
            case 0 -> PortManagerMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                PManagerContainerMenu();
            }
        }
    }

    public void PManagerVehicleMenu() {
        displayMessage("=== PORT MANAGER VEHICLE MENU ===");
        displayMessage("1. Deploy Vehicle (Create Trip)");
        displayMessage("2. Refuel Vehicle");
        displayMessage("=================================");
        displayMessage("3. Add Vehicle");
        displayMessage("4. Update Vehicle");
        displayMessage("5. Delete Vehicle");
        displayMessage("6. Search Vehicle");
        displayMessage("7. View All Vehicles");
        displayMessage("0. Back");

        int choice = promptForInput("Enter you choice: ");
        switch (choice) {
            case 0 -> PortManagerMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                PManagerVehicleMenu();
            }
        }
    }
}

