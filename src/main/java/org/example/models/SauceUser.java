package org.example.models;

import lombok.Data;

@Data
public class SauceUser implements User{
    private String username;
    private String password;
    public SauceUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
