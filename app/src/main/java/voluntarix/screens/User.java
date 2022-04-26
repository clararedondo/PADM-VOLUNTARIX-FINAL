package voluntarix.screens;

public class User {

    String name, lastName, email, username, password;

    public User(String name, String lastName, String email, String username, String password){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.name = "";
        this.lastName = "";
        this.email = "";


    }
}
