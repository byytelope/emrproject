package utils;

import models.User;

public class UserSession {
    private static User user;
    private static UserSession instance;

    public static UserSession UserInstance() {
        if (UserSession.instance == null) {
            UserSession.instance = new UserSession();
        }

        return UserSession.instance;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
