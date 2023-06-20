package utils;

import models.User;

public final class UserSession {
    private static volatile UserSession instance;
    private User user;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            synchronized (UserSession.class) {
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }

        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void clearInstance() {
        user = null;
    }
}
