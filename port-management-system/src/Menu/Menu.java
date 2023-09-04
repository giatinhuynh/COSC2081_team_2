package Menu;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    public static void onboardScreen() {
        System.out.println("==========================================");
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group: Team Hi");
        System.out.println("s3962053, Huynh Duc Gia Tin");
        System.out.println("s3978598, Phan Nhat Minh");
        System.out.println("s3978128, Nguyen Viet Ha");
        System.out.println("s3978681, Vu Minh Ha");
        System.out.println("==========================================");
    }

    public static String[] loginScreen() {
        try {
            System.out.println("==========================================");
            System.out.println("      CONTAINER PORT MANAGEMENT SYSTEM    ");
            System.out.println("==========================================");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            scanner.close();  // Closing the scanner

            return new String[]{username, password};
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter text for both username and password.");
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }
}
