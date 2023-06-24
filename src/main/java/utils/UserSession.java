package utils;

import models.Patient;
import models.User;

public final class UserSession {
    private static volatile UserSession instance;
    private User user = null;
    private Patient patient = null;

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

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return this.user;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void clearInstance() {
        this.user = null;
        this.patient = null;
    }
}
