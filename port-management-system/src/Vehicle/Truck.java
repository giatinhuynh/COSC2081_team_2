package Vehicle;

import Port.IPort;
import java.util.Scanner;
import Container.Container;

public class Truck extends Vehicle {
    private boolean isTanker;
    private boolean isReefer;

    public Truck(String id, String name, double carryingCapacity, double fuelCapacity, boolean isTanker, boolean isReefer) {
        super(id, name, carryingCapacity, fuelCapacity);
        this.isTanker = false;
        this.isReefer = false;

        // Ask for user input for truck type
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of truck: (1 for basic, 2 for reefer, 3 for tanker)");
        int truckType = scanner.nextInt();
        while (truckType != 1 && truckType != 2 && truckType != 3) {
            System.out.println("Invalid input, please enter again: (1 for basic, 2 for reefer, 3 for tanker)");
            truckType = scanner.nextInt();
        }
        if (truckType == 2) {
            this.isReefer = true;
        } else if (truckType == 3) {
            this.isTanker = true;
        }
    }

    // Truck can only load containers that are allowed to be loaded on trucks
    public boolean canLoadContainer(Container container) {
        // Use checkPort to check if the port is a landing port
        if (!checkPort(this.getCurrentPort())) {
            return false;
        }

        /* A basic truck can only carry dry storage, open top, and open side containers, while to carry
        refrigerated containers we need to use reefer trucks, and to carry liquid containers we need to use
        tanker trucks. */
        if (!isReefer && !isTanker) { // Use getType from container
            return container.getType().equals("Dry Storage") || container.getType().equals("Open Top") || container.getType().equals("Open Side");
        } else if (isReefer) {
            return container.getType().equals("Refrigerated");
        } else {
            return container.getType().equals("Liquid");
        }
        return false;
    }

    // Helper method to check if the port is a landing port
    private boolean checkPort(IPort currentPort) {
        // Only the ports that are marked “landing” can utilize trucks for carrying
        if (currentPort.isLanding()) { // Use isLanding from port
            return true;
        }
        return false;
    }
}
