package services.statistics;

import interfaces.statistics.VehicleStatInterface;
import models.vehicle.Ship;
import models.vehicle.Vehicle;
import services.admin.VehicleServicesAdmin;

import java.util.List;

public class VehicleStatistics extends Statistics implements VehicleStatInterface {

    private final VehicleServicesAdmin vehicleController = new VehicleServicesAdmin();

    public void displayTotalNumberOfVehicles() {
        int total = vehicleController.fetchVehiclesFromDatabase().size();

        System.out.println("=================================");
        System.out.println("   TOTAL NUMBER OF VEHICLES    ");
        System.out.println("=================================");
        System.out.println("             " + total + "             ");
        System.out.println("=================================");
    }

    public void vehicleStatus() {
        int total = vehicleController.fetchVehiclesFromDatabase().size();
        String[] statuses = {"In port", "In transit", "Awaiting loading", "Awaiting unloading"};
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7s |\n", "Status", "Count");
        System.out.println("-------------------------------");
        for (String status : statuses) {
            long count = vehicleController.fetchVehiclesFromDatabase().stream()
                    .filter(vehicle -> vehicle.getVehicleStatus().equals(status))
                    .count();
            System.out.printf("| %-20s | %-7d |\n", status, count);
        }
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7d |\n", "Total", total);
        System.out.println("-------------------------------");
    }

    public void vehicleType() {
        int total = vehicleController.fetchVehiclesFromDatabase().size();
        List<Vehicle> vehicles = vehicleController.fetchVehiclesFromDatabase();
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7s |\n", "Type", "Count");
        System.out.println("-------------------------------");
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Ship) {
                long count = vehicles.stream()
                        .filter(v -> v instanceof Ship)
                        .count();
                System.out.printf("| %-20s | %-7d |\n", "Ship", count);
                break;
            } else {
                long count = vehicles.stream()
                        .filter(v -> !(v instanceof Ship))
                        .count();
                System.out.printf("| %-20s | %-7d |\n", "Truck", count);
                break;
            }
        }
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7d |\n", "Total", total);
        System.out.println("-------------------------------");
    }

    public void vehiclePerPort() {
        int total = vehicleController.fetchVehiclesFromDatabase().size();
        List<Vehicle> vehicles = vehicleController.fetchVehiclesFromDatabase();
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7s |\n", "Port", "Count");
        System.out.println("-------------------------------");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCurrentPort() != null) {
                long count = vehicles.stream()
                        .filter(v -> v.getCurrentPort() != null)
                        .count();
                System.out.printf("| %-20s | %-7d |\n", vehicle.getCurrentPort().getName(), count);
                break;
            }
        }
        System.out.println("-------------------------------");
        System.out.printf("| %-20s | %-7d |\n", "Total", total);
        System.out.println("-------------------------------");
    }
}
