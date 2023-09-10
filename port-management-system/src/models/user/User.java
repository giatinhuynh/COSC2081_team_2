package models.user;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String username;
    private String password;
    private String role;  // e.g., "SystemAdmin", "PortManager"

    public User() {

    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;  // For simplicity; in a real system, you'd hash and salt this
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public abstract boolean verifyLogin(String username, String inputPassword);

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // You might add methods to change the password, reset password, etc.
}

