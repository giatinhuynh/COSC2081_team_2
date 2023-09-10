package views;

import models.user.PortManager;
import models.user.SystemAdmin;

/**
 * This class represents the view for the login functionality of the application.
 * Users can enter their username and password to log in as either an administrator or a port manager.
 */
public class LoginView extends BaseView {

    private final AdminView adminFlow = new AdminView(); // Admin view for displaying admin menu
    private final PortManagerView managerFlow = new PortManagerView(); // Port manager view for displaying manager menu
    private final SystemAdmin systemAdmin = new SystemAdmin(); // Instance of the system administrator
    private final PortManager portManager = new PortManager(); // Instance of a port manager

    /**
     * Displays the login screen to the user, prompting for username and password.
     * If the login is successful, it redirects the user to the appropriate menu based on their role.
     * If the login fails, it displays an error message and allows the user to try again.
     */
    public void showLoginScreen() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Verify the login credentials for system admin or port manager
        if (systemAdmin.verifyLogin(username, password) || portManager.verifyLogin(username, password)) {
            if (username.equals("admin") && password.equals("admin")) {
                adminFlow.displayAdminMenu(); // Display the admin menu if admin credentials are used
            } else {
                managerFlow.displayPortManagerMenu(); // Display the port manager menu for other valid logins
            }
        } else {
            displayLoginFailed(); // Display a login failure message and allow the user to try again
            showLoginScreen(); // Recursively call this method for another login attempt
        }
    }

    /**
     * Displays a login failure message to the user.
     */
    public void displayLoginFailed() {
        displayMessage("Login failed. Please try again.");
    }
}
