package Vehicle;

import Port.IPort;
import Container.Container;

// IVehicle interface
public interface IVehicle {
    boolean loadContainer(Container container);

    boolean unloadContainer(String id);

    boolean canMoveTo(IPort port);

    void moveTo(IPort port);

    void refuel();

    void refuel(double amount);
}