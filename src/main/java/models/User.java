package models;

import java.lang.reflect.Field;

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

    public String toCsvString() {
        return '"' + this.nid + "\"," + '"' + this.name + "\"," + '"' + this.email + "\"," + '"' + this.passwordHash
                + "\"," + '"' + this.isPatient
                + "\"\n";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("{");

        Field[] fields = getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append(fields[i].getName()).append("=");

            try {
                Object value = fields[i].get(this);
                if (value instanceof String) {
                    sb.append("\"").append(value).append("\"");
                } else {
                    sb.append(value);
                }
            } catch (IllegalAccessException e) {
                sb.append("N/A");
            }

            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
