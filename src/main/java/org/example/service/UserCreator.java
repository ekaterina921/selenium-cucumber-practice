package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.User;

public abstract class UserCreator {
    public abstract User withCredentialsFromProperty();

    public abstract void removeUser();
}
