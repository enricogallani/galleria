package com.paulinhodelivery.data;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user")
    private String user;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
