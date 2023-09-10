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

package views.display;

public class StaticDisplay {
    public void screenBreak() {
        System.out.println("=============================================");
    }

    public void displayAssessmentInfo() {
        System.out.println("COSC2081 GROUP ASSIGNMENT ");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM ");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group: Team Hi");
        System.out.println("Members: ");
        System.out.println("Phan Nhat Minh - s3978598");
        System.out.println("Huynh Duc Gia Tin - s3818078");
        System.out.println("Nguyen Viet Ha - s3978128");
        System.out.println("Vu Minh Ha - s3978681");
    }

    public void loginSuccessful() {
        System.out.println("Login successful!");
    }

    public void loginFailed() {
        System.out.println("Login failed!");
    }

    public void thankYou() {
        System.out.println("Thank you for using our system!");
    }
}
