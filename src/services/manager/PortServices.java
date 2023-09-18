package services.manager;

import interfaces.manager.ManagerPortInterface;
import models.port.Port;
import models.user.PortManager;
import utils.CurrentUser;

import java.util.Scanner;

public class PortServices extends BaseServices implements ManagerPortInterface {

    private final Port managedPort;
    private final Scanner scanner = new Scanner(System.in);

    public PortServices() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    
}
