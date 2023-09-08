package controllers;

import database.DatabaseManager;
import models.container.*;

import java.util.List;
import java.util.Scanner;

public class ContainerController extends BaseController {

    private DatabaseManager dbManager;
    private Scanner scanner;

    // Methods for CRUD operations, etc.
    public void createNewContainer() {
        System.out.println("Creating a new container...");

        System.out.print("Enter container ID: ");
        String containerId = scanner.nextLine();

        System.out.print("Enter container weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        System.out.println("Choose container type: ");
        System.out.println("1. Dry Storage");
        System.out.println("2. Liquid");
        System.out.println("3. Open Side");
        System.out.println("4. Open Top");
        System.out.println("5. Refrigerated");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Container newContainer = null;
        switch (choice) {
            case 1 -> newContainer = new DryStorage(containerId, weight);
            case 2 -> newContainer = new Liquid(containerId, weight);
            case 3 -> newContainer = new OpenSide(containerId, weight);
            case 4 -> newContainer = new OpenTop(containerId, weight);
            case 5 -> newContainer = new Refrigerated(containerId, weight);
            default -> System.out.println("Invalid container type selected!");
        }

        if (newContainer != null) {
            dbManager.saveContainer(newContainer);
            System.out.println("Container created successfully!");
        } else {
            System.out.println("Invalid container type selected!");
        }
    }

    public void updateExistingContainer() {
        System.out.print("Enter the ID of the container you want to update: ");
        String containerId = scanner.nextLine();

        Container container = dbManager.getContainerById(containerId);

        if (container == null) {
            System.out.println("Container not found!");
            return;
        }

        System.out.println("Updating container with ID: " + containerId);

        System.out.print("Enter new weight for container (Press -1 to keep unchanged): ");
        double newWeight = scanner.nextDouble();
        scanner.nextLine();  // Clear the buffer
        if (newWeight != -1) {
            container.setWeight(newWeight);  // setWeight method exists in the Container class
        }

        dbManager.updateContainer(container);
        System.out.println("Container updated successfully!");
    }


    public void deleteContainer() {
        System.out.print("Enter the ID of the container you want to delete: ");
        String containerId = scanner.nextLine();

        Container container = dbManager.getContainerById(containerId);
        if (container == null) {
            System.out.println("Container not found!");
            return;
        }

        dbManager.deleteContainer(containerId);
        System.out.println("Container deleted successfully!");
    }

    public void searchContainer() {
        System.out.print("Enter the ID of the container you want to search for: ");
        String containerId = scanner.nextLine();

        Container container = dbManager.getContainerById(containerId);
        if (container == null) {
            System.out.println("Container not found!");
            return;
        }

        // Display container details in tabular format
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %15s | %10s | %20s |\n", "Container ID", "Weight", "Container Type");
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %15s | %10.2f | %20s |\n", container.getContainerId(), container.getWeight(), container.getClass().getSimpleName());
        System.out.println("---------------------------------------------------------");
    }

    public void viewAllContainers() {
        List<Container> allContainers = dbManager.getAllContainers();  // Assuming getAllContainers method exists in DatabaseManager

        System.out.println("---------------------------------------------------------");
        System.out.printf("| %15s | %10s | %20s |\n", "Container ID", "Weight", "Container Type");
        System.out.println("---------------------------------------------------------");

        for (Container container : allContainers) {
            System.out.printf("| %15s | %10.2f | %20s |\n", container.getContainerId(), container.getWeight(), container.getClass().getSimpleName());
        }

        System.out.println("---------------------------------------------------------");
    }
}
