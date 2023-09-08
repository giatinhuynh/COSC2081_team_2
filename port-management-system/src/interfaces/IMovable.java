package interfaces;

import models.port.Port;

public interface IMovable {
    boolean canMoveTo(Port port);
    void moveTo(Port port);
}
