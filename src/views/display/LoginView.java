package views.display;

import java.util.Scanner;
import services.UserServices;
import views.flow.AdminFlow;
import views.flow.PortManagerFlow;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);
    private final UserServices userServices = new UserServices();
    private final AdminFlow adminFlow = new AdminFlow();
    private final PortManagerFlow portManagerFlow = new PortManagerFlow();
    private final StaticDisplay staticDisplay = new StaticDisplay();

    public void displayLoginView() {
        System.out.println("=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (userServices.loginValidation(username, password)) {
            if (userServices.adminValidation(username, password)) {
                staticDisplay.loginSuccessful();
                System.out.println("Welcome, System Admin!");
                adminFlow.displayAdminMenu();
            } else {
                staticDisplay.loginSuccessful();
                userServices.setCurrentUser(username);
                System.out.println("Welcome, Port Manager!");
                portManagerFlow.PortManagerMenu();
            }
        } else {
            staticDisplay.loginFailed();
            staticDisplay.thankYou();
        }
    }
}
