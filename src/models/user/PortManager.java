package models.user;

import models.port.Port;


public class PortManager extends User {
    private final Port managedPort;

    public PortManager(String username, String password, Port managedPort) {
        super(username, password, String.valueOf(managedPort));
        this.managedPort = managedPort;
    }

    public PortManager() {
        super();
        this.managedPort = null;
    }

    public Port getManagedPort() {
        return managedPort;
    }

}
