package com.cvut.fit.biopj.portniagin.semestralka.player;

public class User {
    private int userID;
    private String password;
    private boolean loginStatus;

    public User(){}

    public User(int userID) {
        this.userID = userID;
    }

    public User(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
