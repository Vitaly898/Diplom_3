package POJO;

public class User {
    private String name;
    private String email;
    private String password;

    //User for registration
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    //User for login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    //User default
    public User() {
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}
