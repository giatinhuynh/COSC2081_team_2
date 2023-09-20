package interfaces.manager;

import java.text.ParseException;

public interface ManagerVehicleInterface extends ManagerInterface {
    public void refuelVehicle();
    public void deployVehicle() throws ParseException;
}
