package views;

public class LoginView extends BaseView {

    public void showLoginScreen() {
        displayMessage("=== LOGIN ===");
        String username = promptForInput("Username: ");
        String password = promptForInput("Password: ");
        // Pass these values to the UserController for authentication
    }

    public void displayLoginFailed() {
        displayMessage("Login failed. Please try again.");
    }

    public void displayLoginSuccess() {
        displayMessage("Login successful!");
    }

    // ... other login-related views and methods
}

