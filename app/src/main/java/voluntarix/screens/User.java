package voluntarix.screens;

import java.io.Serializable;

public class User implements Serializable {

    String name, lastName, email, username, password, tags, location, description;

    public User(String name, String lastName, String email, String username, String password, String tags, String location, String description){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.tags = tags;
        this.location = location;
        this.description = description;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.name = "";
        this.lastName = "";
        this.email = "";


    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTags() {
        return tags;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
