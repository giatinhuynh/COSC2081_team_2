package views.display;

import java.util.Scanner;
import controllers.UserController;
import models.user.PortManager;
import models.user.SystemAdmin;
import models.user.User;
import views.flow.AdminFlow;
import views.flow.PortManagerFlow;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);
    private final UserController userController = new UserController();
    private final AdminFlow adminFlow = new AdminFlow();
    private final PortManagerFlow portManagerFlow = new PortManagerFlow();
    private final StaticDisplay staticDisplay = new StaticDisplay();

    public void displayLoginView() {
        System.out.println("=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loggedInUser = userController.loginValidation(username, password);

        if (loggedInUser != null) {
            utils.CurrentUser.setUser(loggedInUser);  // Set the logged in user

            if (loggedInUser instanceof SystemAdmin) {
                staticDisplay.loginSuccessful();
                System.out.println("Welcome, System Admin!");
                adminFlow.displayAdminMenu();
            } else if (loggedInUser instanceof PortManager) {
                staticDisplay.loginSuccessful();
                System.out.println("Welcome, Port Manager!");
                portManagerFlow.PortManagerMenu();
            }
        } else {
            staticDisplay.loginFailed();
            staticDisplay.thankYou();
        }
    }
}
