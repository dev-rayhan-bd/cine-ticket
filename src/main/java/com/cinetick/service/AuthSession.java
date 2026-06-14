package com.cinetick.service;

import com.cinetick.models.User;

public class AuthSession {
  
    public static User currentUser = null;

    public static void login(User user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}