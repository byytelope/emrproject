package models;

public class User extends BaseModel {
    private String nid;
    private String name;
    private String email;
    private String password;
    private boolean isPatient;

    public User(String nid, String name, String email, String password, boolean isPatient) {
        this.nid = nid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isPatient = isPatient;
    }

    public User() {
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public boolean getIsPatient() {
        return this.isPatient;
    }
}
