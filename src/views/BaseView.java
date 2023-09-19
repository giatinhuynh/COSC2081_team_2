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
