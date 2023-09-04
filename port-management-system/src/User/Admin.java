package User;

public class Admin extends User implements IAdmin {
    /**
     * Constructor for the Admin class.
     * @param username The username for the admin user.
     * @param password The password for the admin user.
     */
    public Admin(String username, String password) {
        super(username, password, "ADMIN", null);
    }

    @Override
    public void createPort(IPort port) {
        // Add logic to create a port.
    }

    @Override
    public IPort readPort(String id) {
        // Add logic to read a port based on its ID.
        return null;
    }

    @Override
    public void updatePort(IPort port) {
        // Add logic to update a port.
    }

    @Override
    public void deletePort(String id) {
        // Add logic to delete a port based on its ID.
    }

    @Override
    public void createVehicle(IVehicle vehicle) {
        // Add logic to create a vehicle.
    }

    @Override
    public IVehicle readVehicle(String id) {
        // Add logic to read a vehicle based on its ID.
        return null;
    }

    @Override
    public void updateVehicle(IVehicle vehicle) {
        // Add logic to update a vehicle.
    }

    @Override
    public void deleteVehicle(String id) {
        // Add logic to delete a vehicle based on its ID.
    }

    @Override
    public void createContainer(Container container) {
        // Add logic to create a container.
    }

    @Override
    public Container readContainer(String id) {
        // Add logic to read a container based on its ID.
        return null;
    }

    @Override
    public void updateContainer(Container container) {
        // Add logic to update a container.
    }

    @Override
    public void deleteContainer(String id) {
        // Add logic to delete a container based on its ID.
    }

    @Override
    public void addPortManager(PortManager portManager, IPort port) {
        // Add logic to assign a port manager to a port.
    }

    @Override
    public void removePortManager(PortManager portManager) {
        // Add logic to remove a port manager.
    }
}

