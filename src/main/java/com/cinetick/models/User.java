package com.cinetick.models;

public class User {
    public int id;
    public String name, email, password, profilePic, otpCode;
    public boolean isVerified;

    public User(String name, String email, String password, String profilePic) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
    }
}