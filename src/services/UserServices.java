package services;

import models.port.Port;
import utils.Constants;
import database.DatabaseHandler;
import models.user.User;
import models.user.PortManager;

import java.util.*;

public class UserServices extends BaseController {

    private final Scanner scanner = new Scanner(System.in);
    private final String USER_FILE_PATH = Constants.USER_FILE_PATH;
    private final DatabaseHandler dbHandler = new DatabaseHandler();
    public static User currentUser;

    @Override
    public void create() {
        System.out.println("USER CREATE WIZARD");
        System.out.print("Enter user type (SystemAdmin or PortManager): ");
        String userType = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = null;

        if (userType.equals("SystemAdmin")) {
            System.out.println("Adding another System Admin is prohibited");
            return;
        } else if (userType.equals("PortManager")) {
            // You might also need to specify which port the PortManager manages.
            System.out.print("Enter managed port ID: ");
            String managedPortId = scanner.nextLine();
            // Note: Here, I'm assuming you have a method in PortController to get the port by ID.
            PortServices portServices = new PortServices();
            Port managedPort = portServices.getPortById(managedPortId);
            newUser = new PortManager(username, password, managedPort);
        } else {
            System.out.println("Invalid user type!");
            return;
        }

        List<User> usersList;
        try {
            User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
            usersList = new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            usersList = new ArrayList<>();
        }

        usersList.add(newUser);
        dbHandler.writeObjects(USER_FILE_PATH, usersList.toArray(new User[0]));
        System.out.println(userType + " created successfully!");
    }

    @Override
    public void displayOne() {
        System.out.println("DISPLAY USER INFO");
        System.out.print("Enter username: ");
        String usernameToDisplay = scanner.nextLine();

        List<User> usersList;
        try {
            User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
            usersList = new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            System.out.println("Error reading users or no users exist.");
            return;
        }

        User userToDisplay = null;
        for (User user : usersList) {
            if (user.getUsername().equals(usernameToDisplay)) {
                userToDisplay = user;
                break;
            }
        }

        if (userToDisplay != null) {
            System.out.println("--------------------------------------------");
            System.out.printf("| %-15s | %-25s |\n", "Username", "User Type");
            System.out.println("--------------------------------------------");
            System.out.printf("| %-15s | %-25s |\n", userToDisplay.getUsername(), userToDisplay.getClass().getSimpleName());
            System.out.println("--------------------------------------------");
        } else {
            System.out.println("No user found with the given username.");
        }
    }

    @Override
    public void displayAll() {
        System.out.println("DISPLAY ALL USERS INFO");

        List<User> usersList;
        try {
            User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
            usersList = new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            System.out.println("Error reading users or no users exist.");
            return;
        }

        System.out.println("--------------------------------------------");
        System.out.printf("| %-15s | %-25s |\n", "Username", "User Type");
        System.out.println("--------------------------------------------");
        for (User user : usersList) {
            System.out.printf("| %-15s | %-25s |\n", user.getUsername(), user.getClass().getSimpleName());
        }
        System.out.println("--------------------------------------------");
    }

    @Override
    public void update() {
        System.out.println("USER UPDATE WIZARD");
        System.out.print("Enter username to update: ");
        String usernameToUpdate = scanner.nextLine();

        List<User> usersList;
        try {
            User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
            usersList = new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            System.out.println("Error reading users or no users exist.");
            return;
        }

        User userToUpdate = null;
        for (User user : usersList) {
            if (user.getUsername().equals(usernameToUpdate)) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate != null) {
            System.out.print("Enter new password (leave blank to keep unchanged): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) {
                userToUpdate.setPassword(password);
            }

            dbHandler.writeObjects(USER_FILE_PATH, usersList.toArray(new User[0]));
            System.out.println("User with username " + usernameToUpdate + " updated successfully.");
        } else {
            System.out.println("No user found with the given username.");
        }
    }

    @Override
    public void delete() {
        System.out.println("USER DELETE WIZARD");
        System.out.print("Enter username to delete: ");
        String usernameToDelete = scanner.nextLine();

        List<User> usersList;
        try {
            User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
            usersList = new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            System.out.println("Error reading users or no users exist.");
            return;
        }

        boolean isDeleted = false;
        Iterator<User> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(usernameToDelete)) {
                iterator.remove();
                isDeleted = true;
                break;
            }
        }

        if (isDeleted) {
            dbHandler.writeObjects(USER_FILE_PATH, usersList.toArray(new User[0]));
            System.out.println("User with username " + usernameToDelete + " deleted successfully.");
        } else {
            System.out.println("No user found with the given username.");
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

    public void setCurrentUser(String username) {
        List<User> usersList;
        try {
            User[] usersArray = (User[]) dbHandler.readObjects(USER_FILE_PATH);
            usersList = new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            System.out.println("Error reading users or no users exist.");
            return;
        }

        User userToSet = null;
        for (User user : usersList) {
            if (user.getUsername().equals(username)) {
                userToSet = user;
                break;
            }
        }

        if (userToSet != null) {
            currentUser = userToSet;
        } else {
            System.out.println("No user found with the given username.");
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
