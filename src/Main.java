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

import views.display.StaticDisplay;
import views.display.LoginView;

/**
 * This is the main class responsible for running the application.
 * It initializes the static display and login view, and then displays them in sequence.
 */
public class Main {

    // Static display instance to show static information like assessment details.
    private static final StaticDisplay staticDisplay = new StaticDisplay();

    // Login view instance to handle user login.
    private static final LoginView loginView = new LoginView();

    /**
     * The main method which serves as the entry point for the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Display a screen break for better visual separation.
        staticDisplay.screenBreak();

        // Display the assessment information.
        staticDisplay.displayAssessmentInfo();

        // Another screen break for visual separation.
        staticDisplay.screenBreak();

        // Display the login view to the user.
        loginView.displayLoginView();
    }
}

