package views.display;

/**
 * Represents a static display utility for the application.
 * This class provides methods to display static messages and separators in the console.
 */
public class StaticDisplay {

    /**
     * Displays a separator line in the console for better visual separation.
     */
    public void screenBreak() {
        System.out.println("=============================================");
    }

    /**
     * Displays the assessment information including group assignment details, instructor names, and group members.
     */
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

    /**
     * Displays a message indicating a successful login.
     */
    public void loginSuccessful() {
        System.out.println("Login successful!");
    }

    /**
     * Displays a message indicating a failed login attempt.
     */
    public void loginFailed() {
        System.out.println("Login failed!");
    }

    /**
     * Displays a thank you message to the user.
     */
    public void thankYou() {
        System.out.println("Thank you for using our system!");
    }
}
