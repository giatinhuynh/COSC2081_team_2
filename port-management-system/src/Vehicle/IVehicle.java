package Vehicle;

import Port.IPort;
import Container.Container;

// IVehicle interface
public interface IVehicle {
    void loadContainer(Container container);

    void unloadContainer(Container container);

    void moveTo(IPort port);

    void refuel();

    boolean canMoveTo(IPort port);
}
