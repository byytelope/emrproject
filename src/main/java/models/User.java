package models;

public class User {
    private String uid;
    private String name;
    private String email;
    private String passwordHash;
    private boolean isPatient;

    public User(String uid, String name, String email, String passwordHash, boolean isPatient) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isPatient = isPatient;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setIsPatient(boolean isPatient) {
        this.isPatient = isPatient;
    }

    public String getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getpasswordHash() {
        return this.passwordHash;
    }

    public boolean isPatient() {
        return this.isPatient;
    }
}
