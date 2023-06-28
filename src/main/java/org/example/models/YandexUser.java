package org.example.models;

import lombok.Data;

@Data
public class YandexUser implements User{
    private String username;
    private String password;
    public YandexUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
