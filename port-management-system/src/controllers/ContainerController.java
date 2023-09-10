package controllers;

import database.DatabaseHandler;
import interfaces.ICRUD;
import models.container.Container;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ContainerController implements ICRUD<Container> {


    private final String CONTAINER_FILE_PATH = "database/data/containers.dat";

    @Override
    public void create(Container container) throws IOException, ClassNotFoundException {
        ArrayList<Container> containers = readAll();
        containers.add(container);
        DatabaseHandler.writeToFile(CONTAINER_FILE_PATH, containers);
    }

    @Override
    public Optional<Container> read(String containerId) throws IOException, ClassNotFoundException {
        ArrayList<Container> containers = readAll();
        for (Container container : containers) {
            if (container.getContainerId().equals(containerId)) {
                return Optional.of(container);
            }
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Container> readAll() throws IOException, ClassNotFoundException {
        return DatabaseHandler.readFromFile(CONTAINER_FILE_PATH);
    }

    @Override
    public void update(Container updatedContainer) throws IOException, ClassNotFoundException {
        ArrayList<Container> containers = readAll();
        for (int i = 0; i < containers.size(); i++) {
            if (containers.get(i).getContainerId().equals(updatedContainer.getContainerId())) {
                containers.set(i, updatedContainer);
                break;
            }
        }
        DatabaseHandler.writeToFile(CONTAINER_FILE_PATH, containers);
    }

    @Override
    public void delete(String containerId) throws IOException, ClassNotFoundException {
        ArrayList<Container> containers = readAll();
        containers.removeIf(container -> container.getContainerId().equals(containerId));
        DatabaseHandler.writeToFile(CONTAINER_FILE_PATH, containers);
    }
}
