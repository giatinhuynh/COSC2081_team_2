package services.admin;

import exceptions.InputValidation;
import models.port.Port;
import utils.Constants;
import database.DatabaseHandler;
import models.container.*;
import interfaces.CRUD.ContainerCRUD;

import java.util.*;

public class ContainerServicesAdmin extends AdminBaseServices implements ContainerCRUD  {

    private final Scanner scanner = new Scanner(System.in);
    private final String CONTAINER_FILE_PATH = Constants.CONTAINER_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private final InputValidation inputValidation = new InputValidation();

    // Modularized method to fetch containers from the database
    public List<Container> fetchContainersFromDatabase() {
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            return new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {  // Catching a generic exception as a placeholder.
            System.out.println("Error reading containers or no containers exist.");
            return new ArrayList<>();
        }
    }

    // Modularized method to write containers to the database
    private void writeContainersToDatabase(List<Container> containersList) {
        dbHandler.writeObjects(CONTAINER_FILE_PATH, containersList.toArray(new Container[0]));
    }

    // Modularized method to find a container by its ID
    private Optional<Container> findContainerById(String containerId) {
        return fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerId().equals(containerId))
                .findFirst();
    }

    @Override
    public void createNewContainer() {
        System.out.println("CONTAINER CREATE WIZARD");
        System.out.print("Enter container ID: ");
        String containerId = inputValidation.idValidation("C-");
        System.out.print("Enter container weight: ");
        double weight = scanner.nextDouble();
        System.out.println("Select container type:");
        System.out.println("1. Dry storage");
        System.out.println("2. Open Side");
        System.out.println("3. Open Top");
        System.out.println("4. Liquid");
        System.out.println("5. Refrigerated");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // To consume any leftover newline
        Container newContainer = null;
        switch (choice) {
            case 1 -> newContainer = new DryStorage(containerId, weight);
            case 2 -> newContainer = new OpenSide(containerId, weight);
            case 3 -> newContainer = new OpenTop(containerId, weight);
            case 4 -> newContainer = new Liquid(containerId, weight);
            case 5 -> newContainer = new Refrigerated(containerId, weight);
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        List<Container> containerList = fetchContainersFromDatabase();
        containerList.add(newContainer);
        writeContainersToDatabase(containerList);
    }

    @Override
    public void findContainer() {
        System.out.println("DISPLAY CONTAINER INFO");
        System.out.print("Enter container ID: ");
        String containerIdToDisplay = inputValidation.idValidation("C-");
        List<Container> containerList = fetchContainersFromDatabase();
        Optional<Container> optionalContainer = findContainerById(containerIdToDisplay);
        if (optionalContainer.isPresent()) {
            Container containerToDisplay = optionalContainer.get();
            displayContainerTableHeader();
            displayContainerTableRow(containerToDisplay);
            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No container found with the given ID.");
        }
    }

    @Override
    public void displayAllContainers() {
        System.out.println("DISPLAY ALL CONTAINERS INFO");

        List<Container> containerList = fetchContainersFromDatabase();
        displayContainerTableHeader();
        for (Container container : containerList) {
            displayContainerTableRow(container);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    @Override
    public void updateContainer() {
        System.out.println("CONTAINER UPDATE WIZARD");
        System.out.print("Enter container ID to update: ");
        String containerIdToUpdate = inputValidation.idValidation("C-");

        List<Container> containerList = fetchContainersFromDatabase();

        Container containerToUpdate = null;
        int indexToUpdate = -1;  // new variable to keep track of the index in the list
        for (int i = 0; i < containerList.size(); i++) {
            if (containerList.get(i).getContainerId().equals(containerIdToUpdate)) {
                containerToUpdate = containerList.get(i);
                indexToUpdate = i;
                break;
            }
        }

        if (containerToUpdate != null) {
            System.out.print("Enter new container weight (leave blank to keep unchanged): ");
            String weightStr = scanner.nextLine();
            double newWeight = !weightStr.isEmpty() ? Double.parseDouble(weightStr) : containerToUpdate.getWeight();

            System.out.println("Select new container type (or leave blank to keep unchanged):");
            System.out.println("1. Dry storage");
            System.out.println("2. Open Side");
            System.out.println("3. Open Top");
            System.out.println("4. Liquid");
            System.out.println("5. Refrigerated storage");
            System.out.print("Enter your choice: ");
            String typeChoiceStr = scanner.nextLine();

            if (!typeChoiceStr.isEmpty()) {
                int typeChoice = Integer.parseInt(typeChoiceStr);
                switch (typeChoice) {
                    case 1 -> containerToUpdate = new DryStorage(containerIdToUpdate, newWeight);
                    case 2 -> containerToUpdate = new OpenSide(containerIdToUpdate, newWeight);
                    case 3 -> containerToUpdate = new OpenTop(containerIdToUpdate, newWeight);
                    case 4 -> containerToUpdate = new Liquid(containerIdToUpdate, newWeight);
                    case 5 -> containerToUpdate = new Refrigerated(containerIdToUpdate, newWeight);
                    default -> {
                        System.out.println("Invalid choice.");
                        return;
                    }
                }
                containerList.set(indexToUpdate, containerToUpdate);  // Replace the old container with the updated one
            } else if (!weightStr.isEmpty()) {
                containerToUpdate.setWeight(newWeight);
            }

            dbHandler.writeObjects(CONTAINER_FILE_PATH, containerList.toArray(new Container[0]));
            System.out.println("Container with ID " + containerIdToUpdate + " updated successfully.");
        } else {
            System.out.println("No container found with the given ID.");
        }
    }


    @Override
    public void deleteContainer() {
        System.out.println("CONTAINER DELETE WIZARD");
        System.out.print("Enter container ID to delete: ");
        String containerIdToDelete = scanner.nextLine();

        List<Container> containerList;
        try {
            Container[] containersArray = (Container[]) dbHandler.readObjects(CONTAINER_FILE_PATH);
            containerList = new ArrayList<>(Arrays.asList(containersArray));
        } catch (Exception e) {
            System.out.println("Error reading containers or no containers exist.");
            return;
        }

        boolean isDeleted = false;
        Iterator<Container> iterator = containerList.iterator();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            if (container.getContainerId().equals(containerIdToDelete)) {
                iterator.remove();
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            dbHandler.writeObjects(CONTAINER_FILE_PATH, containerList.toArray(new Container[0]));
            System.out.println("Container with ID " + containerIdToDelete + " deleted successfully.");
        } else {
            System.out.println("No container found with the given ID.");
        }
    }

    private Container getContainerById(List<Container> containers, String containerId) {
        for (Container container : containers) {
            if (Objects.equals(container.getContainerId(), containerId)) {
                return container;
            }
        }
        return null;
    }
}
