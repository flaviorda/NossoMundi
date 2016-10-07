package com.example.natan.nossomundi;

/**
 * Created by natan on 9/15/2016.
 */
public class User {
    private int id;
    private String name;
    private String password;

    public User() {

    }

    public User( String name, String password ) {

        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String url) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name + " - " + password + " cm";
    }
}
