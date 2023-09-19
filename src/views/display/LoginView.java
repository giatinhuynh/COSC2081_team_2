package views.display;

import models.user.PortManager;
import models.user.SystemAdmin;
import models.user.User;
import services.admin.UserServicesAdmin;
import views.flow.AdminFlow;
import views.flow.PortManagerFlow;

import java.util.Scanner;

/**
 * Represents the login view of the application.
 * This class is responsible for capturing user input for login and directing the user to the appropriate view based on their role.
 */
public class LoginView {

    // Scanner instance to capture user input.
    private final Scanner scanner = new Scanner(System.in);

    // Controller to handle user-related operations.
    private final UserServicesAdmin userController = new UserServicesAdmin();

    // Flow for admin users.
    private final AdminFlow adminFlow = new AdminFlow();

    // Flow for port manager users. Lazy initialization is used for this instance.
    private PortManagerFlow portManagerFlow;

    // Static display instance to show static messages.
    private final StaticDisplay staticDisplay = new StaticDisplay();

    /**
     * Lazy initialization for the PortManagerFlow instance.
     *
     * @return The initialized PortManagerFlow instance.
     */
    private PortManagerFlow getPortManagerFlow() {
        if (portManagerFlow == null) {
            portManagerFlow = new PortManagerFlow();
        }
        return portManagerFlow;
    }

    /**
     * Displays the login view, captures user input, and directs the user to the appropriate view based on their role.
     */
    public void displayLoginView() {
        System.out.println("=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Validate the user's credentials.
        User loggedInUser = userController.loginValidation(username, password);

        if (loggedInUser != null) {
            // Set the current logged-in user.
            utils.CurrentUser.setUser(loggedInUser);

            // Direct the user to the appropriate view based on their role.
            if (loggedInUser instanceof SystemAdmin) {
                staticDisplay.loginSuccessful();
                System.out.println("Welcome, System Admin!");
                adminFlow.displayAdminMenu();
            } else if (loggedInUser instanceof PortManager) {
                staticDisplay.loginSuccessful();
                System.out.println("Welcome, Port Manager!");
                getPortManagerFlow().PortManagerMenu();  // Use the getter method for lazy initialization
            }
        } else {
            // Display login failure messages.
            staticDisplay.loginFailed();
            staticDisplay.thankYou();
        }
    }
}
