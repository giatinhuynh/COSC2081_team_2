package views;

public class PortManagerView extends BaseView {

    public void displayPortManagerMenu() {
        displayMessage("=== PORT MANAGER MENU ===");
        displayMessage("1. Manage Containers");
        displayMessage("2. Manage Vehicles");
        displayMessage("3. Update Port Details");
        displayMessage("4. View Port Details");
        displayMessage("5. View Trips Log");
        displayMessage("0. Logout");

        int choice = promptForInput("Enter your choice: ");
        switch (choice) {
            case 1 -> displayPortManagerContainersMenu();
            case 2 -> displayPortManagerVehiclesMenu();
            case 3 -> displayPortManagerUpdatePortDetailsMenu();
            case 4 -> displayPortManagerViewPortDetailsMenu();
            case 5 -> displayPortManagerViewTripsLogMenu();
            case 0 -> logoutView();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                displayPortManagerMenu();
            }
        }
    }

    public void displayPortManagerContainersMenu() {

    }

    public void displayPortManagerVehiclesMenu() {

    }

    public void displayPortManagerUpdatePortDetailsMenu() {

    }

    public void displayPortManagerViewPortDetailsMenu() {

    }

    public void displayPortManagerViewTripsLogMenu() {

    }
}

