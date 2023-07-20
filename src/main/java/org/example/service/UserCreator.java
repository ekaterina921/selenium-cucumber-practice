package org.example.service;

import org.example.models.User;

public abstract class UserCreator {
    public abstract User withCredentialsFromProperty();

    public abstract void removeUser();
}
