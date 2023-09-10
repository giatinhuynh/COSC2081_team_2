package models.user;

public class SystemAdmin extends User {


    public SystemAdmin(String username, String password) {
        super("admin", "admin", null);
    }
}
