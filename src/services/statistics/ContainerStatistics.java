package services.statistics;

import interfaces.statistics.ContainerStatInterface;
import models.container.Container;
import services.admin.ContainerServicesAdmin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContainerStatistics extends Statistics implements ContainerStatInterface {

    private final ContainerServicesAdmin containerController = new ContainerServicesAdmin();

    public void displayTotalNumberOfContainers() {
        int total = containerController.fetchContainersFromDatabase().size();

        System.out.println("=================================");
        System.out.println("   TOTAL NUMBER OF CONTAINERS    ");
        System.out.println("=================================");
        System.out.println("             " + total + "             ");
        System.out.println("=================================");
    }

    public void containerStatus() {
        List<Container> allContainers = containerController.fetchContainersFromDatabase();
        int total = allContainers.size();

        String[] statuses = {"Loaded", "Awaiting loading", "In transit", "Unoccupied"};
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7s |\n", "Status", "Count");
        System.out.println("-------------------------------");
        for (String status : statuses) {
            long count = allContainers.stream()
                    .filter(container -> container.getContainerStatus().equals(status))
                    .count();
            System.out.printf("| %-20s | %-7d |\n", status, count);
        }
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7d |\n", "Total", total);
        System.out.println("-------------------------------");
    }

    public void containerType() {
        List<Container> allContainers = containerController.fetchContainersFromDatabase();
        int total = allContainers.size();

        String[] types = {"Refrigerated", "Liquid", "Dry Storage", "Open Top", "Open Side"};
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7s |\n", "Type", "Count");
        System.out.println("-------------------------------");
        for (String type : types) {
            long count = allContainers.stream()
                    .filter(container -> container.getContainerType().equals(type))
                    .count();
            System.out.printf("| %-20s | %-7d |\n", type, count);
        }
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7d |\n", "Total", total);
        System.out.println("-------------------------------");
    }

    public void containerPerPort() {
        List<Container> allContainers = containerController.fetchContainersFromDatabase();
        int total = allContainers.size();

        Map<String, Long> containerCountPerPort = allContainers.stream()
                .collect(Collectors.groupingBy(container -> container.getCurrentPort().getPortId(), Collectors.counting()));

        System.out.println("-----------------------");
        System.out.printf("| %-10s | %-7s |\n", "Port ID", "Count");
        System.out.println("-----------------------");
        containerCountPerPort.forEach((portId, count) -> {
            System.out.printf("| %-10s | %-7d |\n", portId, count);
        });
        System.out.println("-----------------------");
        System.out.printf("| %-10s | %-7d |\n", "Total", total);
        System.out.println("-----------------------");
    }
}
