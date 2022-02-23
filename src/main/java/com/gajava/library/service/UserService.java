package com.gajava.library.service;

import com.gajava.library.model.User;

/**
 * A service that works with users
 */
public interface UserService {
    /**
     * save user
     *
     * @param user request user
     * @return response user
     */
    User save(User user);

    /**
     * find user by login if exists
     *
     * @param login request login
     * @return response user
     */
    User findByLogin(String login);

    /**
     * find user by Login And Password
     *
     * @param login    request login
     * @param password request password
     * @return response user
     */
    User findByLoginAndPassword(String login, String password);

}
