package models.user;

public class SystemAdmin extends User {


    public SystemAdmin(String username, String password) {
        super(username, password, "SystemAdmin");
    }

    // Additional methods specific to system admin
    // e.g., viewAllPorts(), viewAllTrips(), etc.

    public SystemAdmin() {
    }

    @Override
    public boolean verifyLogin(String username, String inputPassword) {
        return username.equals("admin") && inputPassword.equals("admin");
    }
}
