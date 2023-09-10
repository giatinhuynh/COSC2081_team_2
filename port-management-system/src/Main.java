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
  Acknowledgement: chat.openai.com, stackoverflow.com, geeksforgeeks.org, javatpoint.com, tutorialspoint.com, oracle.com, w3schools.com, github.com, codejava.net, baeldung.com, mkyong.com, javacodegeeks.com, journaldev.com
*/

import views.display.StaticDisplay;
import views.display.LoginView;

public class Main {

    // Create static objects
    private static final StaticDisplay staticDisplay = new StaticDisplay();
    private static final LoginView loginView = new LoginView();

    public static void main(String[] args) {
        // Display the assessment information
        staticDisplay.displayAssessmentInfo();

        // Display the login view
        loginView.displayLoginView();
    }
}
