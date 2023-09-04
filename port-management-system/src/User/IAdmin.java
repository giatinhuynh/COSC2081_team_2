package User;

public interface IAdmin extends IUser {
    void createPort(IPort port);
    IPort readPort(String id);
    void updatePort(IPort port);
    void deletePort(String id);
    void createVehicle(IVehicle vehicle);
    IVehicle readVehicle(String id);
    void updateVehicle(IVehicle vehicle);
    void deleteVehicle(String id);
    void createContainer(Container container);
    Container readContainer(String id);
    void updateContainer(Container container);
    void deleteContainer(String id);
    void addPortManager(PortManager portManager, IPort port);
    void removePortManager(PortManager portManager);
}
