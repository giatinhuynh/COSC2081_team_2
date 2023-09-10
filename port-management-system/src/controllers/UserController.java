package controllers;

import models.user.User;
import database.DatabaseHandler;
import interfaces.ICRUD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class UserController implements ICRUD<User> {

    private static final String USER_FILE_PATH = "database/data/users.dat";

    @Override
    public void create(User user) throws IOException, ClassNotFoundException {
        ArrayList<User> users = readAll();
        users.add(user);
        DatabaseHandler.writeToFile(USER_FILE_PATH, users);
    }

    @Override
    public Optional<User> read(String username) throws IOException, ClassNotFoundException {
        ArrayList<User> users = DatabaseHandler.readFromFile(USER_FILE_PATH);
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    @Override
    public ArrayList<User> readAll() throws IOException, ClassNotFoundException {
        return DatabaseHandler.readFromFile(USER_FILE_PATH);
    }

    @Override
    public void update(User user) throws IOException, ClassNotFoundException {
        ArrayList<User> users = readAll();
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            users.set(index, user);
            DatabaseHandler.writeToFile(USER_FILE_PATH, users);
        }
    }

    @Override
    public void delete(String username) throws IOException, ClassNotFoundException {
        ArrayList<User> users = readAll();
        users.removeIf(u -> u.getUsername().equals(username));
        DatabaseHandler.writeToFile(USER_FILE_PATH, users);
    }
}
