package views.container;

import models.container.*;
import controllers.ContainerController;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.io.IOException;

public class ContainerUI {
    private final ContainerController containerController = new ContainerController();
    private final Scanner scanner = new Scanner(System.in);

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
            try {
                containerController.create(newContainer);
                System.out.println("Container created successfully!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error saving container: " + e.getMessage());
            }
        } else {
            System.out.println("Container creation failed!");
        }
    }

    public void updateExistingContainer() {
        System.out.print("Enter the ID of the container you want to update: ");
        String containerId = scanner.nextLine();

        try {
            Optional<Container> containerOptional = containerController.read(containerId);
            if (containerOptional.isPresent()) {
                Container container = containerOptional.get();

                System.out.println("Updating container with ID: " + containerId);

                System.out.print("Enter new weight for container (Press -1 to keep unchanged): ");
                double newWeight = scanner.nextDouble();
                scanner.nextLine();  // Clear the buffer
                if (newWeight != -1) {
                    container.setWeight(newWeight);  // Assuming setWeight method exists in the Container class
                }

                containerController.update(container);
                System.out.println("Container updated successfully!");
            } else {
                System.out.println("Container not found!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error updating container: " + e.getMessage());
        }
    }

    public void deleteContainer() {
        System.out.print("Enter the ID of the container you want to delete: ");
        String containerId = scanner.nextLine();

        try {
            containerController.delete(containerId);
            System.out.println("Container deleted successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deleting container: " + e.getMessage());
        }
    }

    public void searchContainer() {
        System.out.print("Enter the ID of the container you want to search for: ");
        String containerId = scanner.nextLine();

        try {
            Optional<Container> containerOptional = containerController.read(containerId);
            if (containerOptional.isPresent()) {
                Container container = containerOptional.get();

                System.out.println("---------------------------------------------------------");
                System.out.printf("| %15s | %10s | %20s |\n", "Container ID", "Weight", "Container Type");
                System.out.println("---------------------------------------------------------");
                System.out.printf("| %15s | %10.2f | %20s |\n", container.getContainerId(), container.getWeight(), container.getClass().getSimpleName());
                System.out.println("---------------------------------------------------------");
            } else {
                System.out.println("Container not found!");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error retrieving container: " + e.getMessage());
        }
    }

    public void viewAllContainers() {
        try {
            List<Container> allContainers = containerController.readAll();

            System.out.println("---------------------------------------------------------");
            System.out.printf("| %15s | %10s | %20s |\n", "Container ID", "Weight", "Container Type");
            System.out.println("---------------------------------------------------------");

            for (Container container : allContainers) {
                System.out.printf("| %15s | %10.2f | %20s |\n", container.getContainerId(), container.getWeight(), container.getClass().getSimpleName());
            }

            System.out.println("---------------------------------------------------------");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error retrieving containers: " + e.getMessage());
        }
    }
}
