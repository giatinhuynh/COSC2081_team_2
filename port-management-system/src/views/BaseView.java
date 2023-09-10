package views;

import java.util.Scanner;

public abstract class BaseView {
    protected Scanner scanner = new Scanner(System.in);

    protected void displayMessage(String message) {
        System.out.println(message);
    }

    protected int promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    protected void logoutView() {
        System.out.println("Logging out...");
        System.out.println("Thank you for using our system!");
    }

    // Add other utility methods as necessary
}
