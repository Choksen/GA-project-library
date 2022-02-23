package com.gajava.library.repository;

import com.gajava.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository working with the user
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find user by login
     *
     * @param Login login
     * @return user
     */
    User findUserByLogin(String Login);
}
