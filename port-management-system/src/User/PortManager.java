package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Container.*;
import Trip.Trip;

public class PortManager extends User implements IPortManager {

    private String portId;
    private List<Container> containerList = new ArrayList<>();
    private List<Trip> tripList = new ArrayList<>();

    public PortManager(String username, String password, String portId) {
        super(username, password);
        this.portId = portId;
        loadContainers();
    }

    private void loadContainers() {
        try {
            File file = new File("Database/containers.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                // Assuming file has id, type, weight, fuelConsumptionForShip, fuelConsumptionForTruck for each container
                double weight = Double.parseDouble(parts[2]);
                double fuelConsumptionForShip = Double.parseDouble(parts[3]);
                double fuelConsumptionForTruck = Double.parseDouble(parts[4]);
                switch (parts[1]) {
                    case "DryStorage":
                        containerList.add(new DryStorage(parts[0], weight));
                        break;
                    case "OpenTop":
                        containerList.add(new OpenTop(parts[0], weight));
                        break;
                    case "OpenSide":
                        containerList.add(new OpenSide(parts[0], weight));
                        break;
                    case "Refrigerated":
                        containerList.add(new Refrigerated(parts[0], weight));
                        break;
                    case "Liquid":
                        containerList.add(new Liquid(parts[0], weight));
                        break;
                    default:
                        System.out.println("Unknown container type: " + parts[1]);
                        break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    private void saveContainers() {
        try {
            FileWriter writer = new FileWriter("Database/containers.txt");
            for (Container container : containerList) {
                writer.write(container.getId() + "," + container.getClass().getSimpleName() + "," + container.getWeight() + "," + container.getFuelConsumptionPerKmForShip() + "," + container.getFuelConsumptionPerKmForTruck() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    @Override
    public void createContainer(Container container) {
        containerList.add(container);
        saveContainers();
    }

    @Override
    public Container readContainer(String id) {
        for (Container container : containerList) {
            if (container.getId().equals(id)) {
                return container;
            }
        }
        return null;
    }

    @Override
    public void updateContainer(Container container) {
        for (int i = 0; i < containerList.size(); i++) {
            if (containerList.get(i).getId().equals(container.getId())) {
                containerList.set(i, container);
                break;
            }
        }
        saveContainers();
    }

    @Override
    public void deleteContainer(String id) {
        containerList.removeIf(container -> container.getId().equals(id));
        saveContainers();
    }

    @Override
    public List<Trip> listTrips() {
        return tripList;
    }
}
