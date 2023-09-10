package models.user;

import models.port.Port;

public class PortManager extends User {
    private Port managedPort;


    public PortManager(String username, String password, Port managedPort) {
        super(username, password, "PortManager");
        this.managedPort = managedPort;
    }

    public PortManager() {

    }

    public Port getManagedPort() {
        return managedPort;
    }

    @Override
    public boolean verifyLogin(String username, String inputPassword) {
        if (username.equals(this.getUsername())) {
            return inputPassword.equals(this.getPassword());
        } else {
            return false;
        }
    }


    // Additional methods specific to port manager
    // e.g., addContainerToPort(), removeContainerFromPort(), etc.
}
