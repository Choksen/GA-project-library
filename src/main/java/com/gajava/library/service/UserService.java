package com.gajava.library.service;

import com.gajava.library.model.User;

public interface UserService {
    User save(User user);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

}
