package utils;

import models.user.User;

public class CurrentUser {
    private static User currentUser;

    public static User getUser() {
        return currentUser;
    }

    public static void setUser(User user) {
        currentUser = user;
    }

    public static void clearUser() {
        currentUser = null;
    }
}
