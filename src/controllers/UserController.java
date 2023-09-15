package controllers;

import models.port.Port;
import models.user.SystemAdmin;
import utils.Constants;
import database.DatabaseHandler;
import models.user.User;
import models.user.PortManager;

import java.util.*;

public class UserController extends BaseController {

    private final Scanner scanner = new Scanner(System.in);
    private final String USER_FILE_PATH = Constants.USER_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();

    @Override
    public void create() {
        System.out.println("PORT MANAGER CREATE WIZARD");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter managed port ID: ");
        String managedPortId = scanner.nextLine();
        PortController portController = new PortController();
        Port managedPort = portController.getPortById(managedPortId);

        PortManager newPortManager = new PortManager(username, password, managedPort);

        List<PortManager> managersList;
        try {
            PortManager[] managersArray = (PortManager[]) dbHandler.readObjects(USER_FILE_PATH);
            managersList = new ArrayList<>(Arrays.asList(managersArray));
        } catch (Exception e) {
            managersList = new ArrayList<>();
        }

        managersList.add(newPortManager);
        dbHandler.writeObjects(USER_FILE_PATH, managersList.toArray(new PortManager[0]));
        System.out.println("PortManager created successfully!");
    }

    @Override
    public void displayOne() {
        System.out.println("DISPLAY PORT MANAGER INFO");
        System.out.print("Enter username: ");
        String usernameToDisplay = scanner.nextLine();

        List<PortManager> managersList;
        try {
            PortManager[] managersArray = (PortManager[]) dbHandler.readObjects(USER_FILE_PATH);
            managersList = new ArrayList<>(Arrays.asList(managersArray));
        } catch (Exception e) {
            System.out.println("Error reading port managers or none exist.");
            return;
        }

        PortManager managerToDisplay = null;
        for (PortManager manager : managersList) {
            if (manager.getUsername().equals(usernameToDisplay)) {
                managerToDisplay = manager;
                break;
            }
        }

        if (managerToDisplay != null) {
            System.out.println("--------------------------------------------");
            System.out.printf("| %-15s | %-25s |\n", "Username", "Managed Port ID");
            System.out.println("--------------------------------------------");
            System.out.printf("| %-15s | %-25s |\n", managerToDisplay.getUsername(), managerToDisplay.getManagedPort().getPortId());
            System.out.println("--------------------------------------------");
        } else {
            System.out.println("No PortManager found with the given username.");
        }
    }

    @Override
    public void displayAll() {
        System.out.println("DISPLAY ALL PORT MANAGERS INFO");

        List<PortManager> managersList;
        try {
            PortManager[] managersArray = (PortManager[]) dbHandler.readObjects(USER_FILE_PATH);
            managersList = new ArrayList<>(Arrays.asList(managersArray));
        } catch (Exception e) {
            System.out.println("Error reading port managers or none exist.");
            return;
        }

        System.out.println("--------------------------------------------");
        System.out.printf("| %-15s | %-25s |\n", "Username", "Managed Port ID");
        System.out.println("--------------------------------------------");
        for (PortManager manager : managersList) {
            System.out.printf("| %-15s | %-25s |\n", manager.getUsername(), manager.getManagedPort().getPortId());
        }
        System.out.println("--------------------------------------------");
    }

    @Override
    public void update() {
        System.out.println("PORT MANAGER UPDATE WIZARD");
        System.out.print("Enter username to update: ");
        String usernameToUpdate = scanner.nextLine();

        List<PortManager> managersList;
        try {
            PortManager[] managersArray = (PortManager[]) dbHandler.readObjects(USER_FILE_PATH);
            managersList = new ArrayList<>(Arrays.asList(managersArray));
        } catch (Exception e) {
            System.out.println("Error reading port managers or none exist.");
            return;
        }

        PortManager managerToUpdate = null;
        for (PortManager manager : managersList) {
            if (manager.getUsername().equals(usernameToUpdate)) {
                managerToUpdate = manager;
                break;
            }
        }

        if (managerToUpdate != null) {
            System.out.print("Enter new password (leave blank to keep unchanged): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) {
                managerToUpdate.setPassword(password);
            }

            dbHandler.writeObjects(USER_FILE_PATH, managersList.toArray(new PortManager[0]));
            System.out.println("PortManager with username " + usernameToUpdate + " updated successfully.");
        } else {
            System.out.println("No PortManager found with the given username.");
        }
    }

    @Override
    public void delete() {
        System.out.println("PORT MANAGER DELETE WIZARD");
        System.out.print("Enter username to delete: ");
        String usernameToDelete = scanner.nextLine();

        List<PortManager> managersList;
        try {
            PortManager[] managersArray = (PortManager[]) dbHandler.readObjects(USER_FILE_PATH);
            managersList = new ArrayList<>(Arrays.asList(managersArray));
        } catch (Exception e) {
            System.out.println("Error reading port managers or none exist.");
            return;
        }

        boolean isDeleted = false;
        Iterator<PortManager> iterator = managersList.iterator();
        while (iterator.hasNext()) {
            PortManager manager = iterator.next();
            if (manager.getUsername().equals(usernameToDelete)) {
                iterator.remove();
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            dbHandler.writeObjects(USER_FILE_PATH, managersList.toArray(new PortManager[0]));
            System.out.println("PortManager with username " + usernameToDelete + " deleted successfully.");
        } else {
            System.out.println("No PortManager found with the given username.");
        }
    }

    public boolean loginValidation(String username, String password) {
        if (adminValidation(username, password)) {
            return true;
        } else {
            List<User> usersList;
            try {
                User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
                usersList = new ArrayList<>(Arrays.asList(usersArray));
            } catch (Exception e) {
                System.out.println("Error reading users or no users exist.");
                return false;
            }

            User userToValidate = null;
            for (User user : usersList) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    userToValidate = user;
                    break;
                }
            }
            return userToValidate != null;
        }
    }

    public boolean adminValidation(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
