package com.tnt.android.hw5_android_database.common;

/**
 * Created by USER on 27.6.2016 г..
 */
public class User {

    String username;
    String password;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
