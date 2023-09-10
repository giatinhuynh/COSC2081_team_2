package views.flow;

import views.BaseView;

public class PortManagerFlow extends BaseView {

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
            case 1 -> PManagerLoadContainerMenu();
            case 2 -> PManagerUnloadContainerMenu();
            case 0 -> PortManagerMenu();
            default -> {
                displayMessage("Invalid choice. Please try again.");
                PManagerContainerMenu();
            }
        }
    }

    public void PManagerLoadContainerMenu() {
        displayMessage("=== PORT MANAGER LOAD CONTAINER MENU ===");

    }

    public void PManagerUnloadContainerMenu() {

    }
}

