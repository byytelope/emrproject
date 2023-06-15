package utils;

import models.User;

public final class UserSession {
    private static UserSession instance;
    private User user;

    private UserSession(User user) {
        this.user = user;
    }

    public static UserSession createInstance(User user) {
        if (instance == null) {
            instance = new UserSession(user);
        }

        return instance;
    }

    public User getUser() {
        return user;
    }

    public void clearInstance() {
        user = null;
    }
}
