package services.statistics;

import models.container.Container;
import services.admin.ContainerServicesAdmin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContainerStatistics extends Statistics {

    private final ContainerServicesAdmin containerController = new ContainerServicesAdmin();
    public int totalNumberOfContainers() {
        return containerController.fetchContainersFromDatabase().size();
    }

    public void containerStatus() {
        int total = totalNumberOfContainers();
        int loaded = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerStatus().equals("Loaded"))
                .toList().size();
        int awaiting = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerStatus().equals("Awaiting loading"))
                .toList().size();
        int inTransit = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerStatus().equals("In transit"))
                .toList().size();
        int unoccupied = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerStatus().equals("Unoccupied"))
                .toList().size();
        System.out.println("Total number of containers: " + total);
        System.out.println("Loaded: " + loaded);
        System.out.println("Awaiting loading: " + awaiting);
        System.out.println("In transit: " + inTransit);
        System.out.println("Unoccupied: " + unoccupied);
    }

    public void containerType() {
        int total = totalNumberOfContainers();
        int refrigerated = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerType().equals("Refrigerated"))
                .toList().size();
        int liquid = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerType().equals("Liquid"))
                .toList().size();
        int dry = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerType().equals("Dry Storage"))
                .toList().size();
        int openTop = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerType().equals("Open Top"))
                .toList().size();
        int openSide = containerController.fetchContainersFromDatabase().stream()
                .filter(container -> container.getContainerType().equals("Open Side"))
                .toList().size();
        System.out.println("Total number of containers: " + total);
        System.out.println("Refrigerated: " + refrigerated);
        System.out.println("Liquid: " + liquid);
        System.out.println("Dry: " + dry);
        System.out.println("Open Top: " + openTop);
        System.out.println("Open Side: " + openSide);
    }

    public void containerPerPort() {
        List<Container> allContainers = containerController.fetchContainersFromDatabase();
        int total = allContainers.size();

        // Use a map to store the count of containers for each port
        Map<String, Long> containerCountPerPort = allContainers.stream()
                .collect(Collectors.groupingBy(container -> container.getCurrentPort().getPortId(), Collectors.counting()));

        System.out.println("Total number of containers: " + total);

        // Print the count for each port
        containerCountPerPort.forEach((portId, count) -> {
            System.out.println(portId + ": " + count);
        });
    }
}
