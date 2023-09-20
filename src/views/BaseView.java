/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2023B
  Assessment: Group Assignment
  Group: Team Hi
  Members:
  Phan Nhat Minh - s3978598
  Huynh Duc Gia Tin - s3818078
  Nguyen Viet Ha - s3978128
  Vu Minh Ha - s3978681
  Created  date: 02/09/2023
  Acknowledgement: chat.openai.com, stackoverflow.com, geeksforgeeks.org, javatpoint.com, tutorialspoint.com, oracle.com, w3schools.com, github.com
*/

package views;

import java.io.IOException;
import java.util.Scanner;

public abstract class BaseView {
    protected Scanner scanner = new Scanner(System.in);

    protected void displayHeader(String title) {
        int width = 30;
        int padding = (width - title.length()) / 2;
        displayMessage("=".repeat(width));
        displayMessage("=".repeat(padding) + title + "=".repeat(padding));
        displayMessage("=".repeat(width));
    }

    protected void displayHorizontalLine() {
        displayMessage("-".repeat(30));
    }

    protected void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    protected void displayMessage(String message) {
        System.out.println(message);
    }

    protected int promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    protected void backToMenu() {
        System.out.println("Press anything to go back to menu");
        scanner.nextLine();
        scanner.nextLine();
    }

    protected void logoutView() {
        System.out.println("Logging out...");
        System.out.println("Thank you for using our system!");
    }

    // Add other utility methods as necessary
}
