package services.manager;

import interfaces.manager.ManagerInterface;
import models.container.Container;
import models.port.Port;
import models.user.PortManager;
import models.vehicle.Vehicle;
import services.PortController;
import utils.CurrentUser;

public abstract class BaseServices implements ManagerInterface {

    private final PortController portServices = new PortController();
    private final Port managedPort;

    public BaseServices() {
        if (CurrentUser.getUser() instanceof PortManager) {
            this.managedPort = ((PortManager) CurrentUser.getUser()).getManagedPort();
        } else {
            throw new IllegalStateException("The current user is not a Port Manager");
        }
    }

    Container findContainerById(String id) {
        for (Container container : managedPort.getCurrentContainers()) {
            if (container.getContainerId().equals(id)) {
                return container;
            }
        }
        return null;
    }

    Port findPortById(String id) {
        return portServices.getPortById(id);
    }

    Vehicle findVehicleById(String id) {
        for (Vehicle vehicle : managedPort.getCurrentVehicles()) {
            if (vehicle.getVehicleId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }
}
