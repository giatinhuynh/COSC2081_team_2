package models.user;

import database.DatabaseManager;

import java.util.Scanner;

public class SystemAdmin extends User {


    public SystemAdmin(String username, String password) {
        super(username, password, "SystemAdmin");
    }

    // Additional methods specific to system admin
    // e.g., viewAllPorts(), viewAllTrips(), etc.

    public SystemAdmin() {
        this("admin", "admin");
        DatabaseManager dbManager = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);
    }
}
