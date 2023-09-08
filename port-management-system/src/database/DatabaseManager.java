package database;

import models.port.Port;
import models.container.*;
import models.vehicle.*;
import models.user.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseManager {

    // Constants for file paths
    private static final String PORTS_FILE = "./database/data/ports.dat";
    private static final String CONTAINERS_FILE = "./database/data/containers.dat";
    private static final String TRIPS_FILE = "./database/data/trips.dat";
    private static final String PORTMANAGERS_FILE = "./database/data/users.dat";
    private static final String VEHICLES_FILE = "./database/data/vehicles.dat";

    // Initialization - Check if files exist, if not create them
    static {
        initializeFile(PORTS_FILE);
        initializeFile(CONTAINERS_FILE);
        initializeFile(TRIPS_FILE);
        initializeFile(PORTMANAGERS_FILE);
        initializeFile(VEHICLES_FILE);
    }

    private static void initializeFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error initializing file: " + fileName);
                e.printStackTrace();
            }
        }
    }

    // General CRUD methods (taking Port as an example)
    public void savePort(Port port) {
        List<Port> ports = getAllPorts();
        ports.add(port);
        writeToFile(PORTS_FILE, ports);
    }

    public List<Port> getAllPorts() {
        return (List<Port>) readFromFile(PORTS_FILE);
    }

    public Port getPortById(String id) {
        List<Port> ports = getAllPorts();
        for (Port port : ports) {
            if (Objects.equals(port.getPortId(), id)) {
                return port;
            }
        }
        return null;
    }

    public void updatePort(Port updatedPort) {
        List<Port> ports = getAllPorts();
        int index = -1;
        for (int i = 0; i < ports.size(); i++) {
            if (Objects.equals(ports.get(i).getPortId(), updatedPort.getPortId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            ports.set(index, updatedPort);
            writeToFile(PORTS_FILE, ports);
        }
    }

    public void deletePort(String id) {
        List<Port> ports = getAllPorts();
        ports.removeIf(port -> Objects.equals(port.getPortId(), id));
        writeToFile(PORTS_FILE, ports);
    }

    // General CRUD methods (taking Container as an example)
    public void saveContainer(Container container) {
        List<Container> containers = getAllContainers();
        containers.add(container);
        writeToFile(CONTAINERS_FILE, containers);
    }

    public List<Container> getAllContainers() {
        return (List<Container>) readFromFile(CONTAINERS_FILE);
    }

    public Container getContainerById(String id) {
        List<Container> containers = getAllContainers();
        for (Container container : containers) {
            if (Objects.equals(container.getContainerId(), id)) {
                return container;
            }
        }
        return null;
    }

    public void updateContainer(Container updatedContainer) {
        List<Container> containers = getAllContainers();
        int index = -1;
        for (int i = 0; i < containers.size(); i++) {
            if (Objects.equals(containers.get(i).getContainerId(), updatedContainer.getContainerId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            containers.set(index, updatedContainer);
            writeToFile(CONTAINERS_FILE, containers);
        }
    }

    public void deleteContainer(String id) {
        List<Container> containers = getAllContainers();
        containers.removeIf(container -> Objects.equals(container.getContainerId(), id));
        writeToFile(CONTAINERS_FILE, containers);
    }

    // CRUD methods for Vehicles
    public void saveVehicle(Vehicle vehicle) {
        List<Vehicle> vehicles = getAllVehicles();
        vehicles.add(vehicle);
        writeToFile(VEHICLES_FILE, vehicles);
    }

    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) readFromFile(VEHICLES_FILE);
    }

    public Vehicle getVehicleById(String id) {
        List<Vehicle> vehicles = getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getVehicleId(), id)) {
                return vehicle;
            }
        }
        return null;
    }

    public void updateVehicle(Vehicle updatedVehicle) {
        List<Vehicle> vehicles = getAllVehicles();
        int index = -1;
        for (int i = 0; i < vehicles.size(); i++) {
            if (Objects.equals(vehicles.get(i).getVehicleId(), updatedVehicle.getVehicleId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            vehicles.set(index, updatedVehicle);
            writeToFile(VEHICLES_FILE, vehicles);
        }
    }

    public void deleteVehicle(String id) {
        List<Vehicle> vehicles = getAllVehicles();
        vehicles.removeIf(vehicle -> Objects.equals(vehicle.getVehicleId(), id));
        writeToFile(VEHICLES_FILE, vehicles);
    }

    // Port Managers
    // CREATE: Save PortManager
    public void savePortManager(PortManager portManager) {
        List<PortManager> portManagers = getAllPortManagers();
        portManagers.add(portManager);
        writeToFile(PORTMANAGERS_FILE, portManagers);
    }

    // RETRIEVE: Retrieve all PortManagers
    public List<PortManager> getAllPortManagers() {
        return (List<PortManager>) readFromFile(PORTMANAGERS_FILE);
    }

    // RETRIEVE: Get PortManager by Username
    public PortManager getPortManagerByUsername(String username) {
        List<PortManager> portManagers = getAllPortManagers();
        for (PortManager manager : portManagers) {
            if (Objects.equals(manager.getUsername(), username)) {
                return manager;
            }
        }
        return null;
    }

    // RETRIEVE: Get PortManager by Managed Port
    public PortManager getPortManagerByManagedPort(Port port) {
        List<PortManager> portManagers = getAllPortManagers();
        for (PortManager manager : portManagers) {
            if (manager.getManagedPort().equals(port)) {
                return manager;
            }
        }
        return null;
    }

    // UPDATE: Update PortManager
    public void updatePortManager(PortManager updatedManager) {
        List<PortManager> portManagers = getAllPortManagers();
        int index = -1;
        for (int i = 0; i < portManagers.size(); i++) {
            if (Objects.equals(portManagers.get(i).getUsername(), updatedManager.getUsername())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            portManagers.set(index, updatedManager);
            writeToFile(PORTMANAGERS_FILE, portManagers);
        }
    }

    // DELETE: Delete PortManager
    public void deletePortManager(String username) {
        List<PortManager> portManagers = getAllPortManagers();
        portManagers.removeIf(manager -> Objects.equals(manager.getUsername(), username));
        writeToFile(PORTMANAGERS_FILE, portManagers);
    }


    // General methods for reading/writing using serialization
    private void writeToFile(String fileName, Object obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    private Object readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + fileName);
            e.printStackTrace();
        }
        return new ArrayList<>(); // Returning empty list as a fallback
    }

    // ... Similarly, implement CRUD methods for other models (Container, Vehicle, etc.)

}
