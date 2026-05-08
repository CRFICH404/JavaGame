package com.cvut.fit.biopj.portniagin.semestralka;

import java.util.Date;

public class User {
    private String userID;
    private String name;
    private boolean loginStatus;
    private Date registrationDate;
    public User(String userID, String name) {
        this.userID = userID;
        this.name = name;
    }
}
