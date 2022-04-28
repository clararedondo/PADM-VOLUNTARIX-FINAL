package voluntarix.screens;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    public static final String NAME = "name";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIl = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String TAGS = "tags";
    public static final String LOCATION = "location";
    public static final String DESCRIPTION = "description";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString(NAME, user.name);
        spEditor.putString(LAST_NAME, user.lastName);
        spEditor.putString(EMAIl, user.email);
        spEditor.putString(USERNAME, user.username);
        spEditor.putString(PASSWORD, user.password);
        spEditor.putString(LOCATION, user.location);
        spEditor.putString(DESCRIPTION, user.description);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String name = userLocalDatabase.getString(NAME, "");
        String lastName = userLocalDatabase.getString(LAST_NAME, "");
        String email = userLocalDatabase.getString(EMAIl, "");
        String username = userLocalDatabase.getString(USERNAME, "");
        String password = userLocalDatabase.getString(PASSWORD, "");
        String location = userLocalDatabase.getString(LOCATION, "");
        String description = userLocalDatabase.getString(DESCRIPTION, "");

        User storedUser = new User(name, lastName, email, username, password, location, description);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        return userLocalDatabase.getBoolean("loggedIn", false);
    }

    public void updateUserData(String key, String value) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }


}
