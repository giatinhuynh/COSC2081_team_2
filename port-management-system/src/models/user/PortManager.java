package models.user;

import models.port.Port;

public class PortManager extends User {
    private Port managedPort;

    public PortManager(String username, String password, Port managedPort) {
        super(username, password, "PortManager");
        this.managedPort = managedPort;
    }

    public Port getManagedPort() {
        return managedPort;
    }

    // Additional methods specific to port manager
    // e.g., addContainerToPort(), removeContainerFromPort(), etc.
}
