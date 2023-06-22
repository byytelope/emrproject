package models;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class User {
    private String nid;
    private String name;
    private String email;
    private String passwordHash;
    private boolean isPatient;

    public User(String nid, String name, String email, String password, boolean isPatient) {
        this.nid = nid;
        this.name = name;
        this.email = email;
        this.isPatient = isPatient;

        String hPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        this.passwordHash = hPassword;
    }

    public void setNid(String nid) {
        this.nid = nid;
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

    public String getNid() {
        return this.nid;
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

    @Override
    public String toString() {
        return '"' + this.nid + "\"," + '"' + this.name + "\"," + '"' + this.email + "\"," + '"' + this.passwordHash
                + "\"," + '"' + this.isPatient
                + "\"\n";
    }
}
