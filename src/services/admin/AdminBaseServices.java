package services.admin;

import models.container.Container;
import models.port.Port;

import java.io.IOException;

public abstract class AdminBaseServices {

    // Helper methods to display port information in a table format
    public void displayPortTableHeader() {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-40s | %-10s | %-10s | %-20s | %-15s |\n", "Port ID", "Name", "Latitude", "Longitude", "Storing Capacity", "Landing Ability");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void displayContainerTableHeader() {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-20s | %-15s |\n", "Container ID", "Container Type", "Weight");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void displayPortTableRow(Port port) {
        System.out.printf("| %-10s | %-40s | %-10.4f | %-10.4f | %,-20.2f | %-15b |\n",
                port.getPortId(), port.getName(), port.getLatitude(), port.getLongitude(),
                port.getStoringCapacity(), port.getLandingAbility());
    }

    public void displayContainerTableRow(Container container) {
        System.out.printf("| %-15s | %-20s | %-15.2f |\n",
                container.getContainerId(), container.getContainerType(),
                container.getWeight());
    }
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
