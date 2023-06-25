package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.models.User;

@Log4j
public abstract class UserCreator {
    static ThreadLocal<User> local = new ThreadLocal<>();
    public abstract User withCredentialsFromProperty();

    public static void removeUser() {
        if (local.get() != null) {
            log.debug("User removed.");
            local.remove();
        }
    }


}
