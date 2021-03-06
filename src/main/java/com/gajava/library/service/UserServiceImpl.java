package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.exception.SaveEntityException;
import com.gajava.library.model.Role;
import com.gajava.library.model.User;
import com.gajava.library.repository.RoleRepository;
import com.gajava.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User save(final User user) {
        if (Objects.isNull(user)) {
            throw new InvalidArgumentsException("User cannot be null");
        }
        log.info("Try to save user");
        final Role userRole = Optional.ofNullable(roleRepository.findRoleByName("ROLE_USER")).orElseThrow(
                () -> new EntityNotFoundException("Role", "ROLE_USER"));
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userCreated;
        try {
            userCreated = Optional.of(userRepository.save(user)).orElseThrow(
                    () -> new SaveEntityException("User"));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentsException("Such an user already exists");
        }
        log.info("User " + user.getLogin() + " was create");
        return userCreated;
    }

    public User findByLogin(final String login) {
        if (Objects.isNull(login)) {
            throw new InvalidArgumentsException("Login cannot be null");
        }
        log.info("Trying to find a user by login: " + login);
        return Optional.ofNullable(userRepository.findUserByLogin(login)).orElseThrow(
                () -> new EntityNotFoundException("User", "(login) " + login));
    }

    public User findByLoginAndPassword(final String login, final String password) {
        if (Objects.isNull(login) || Objects.isNull(password)) {
            throw new InvalidArgumentsException("Login or password cannot be null");
        }
        log.info("Trying to find a user with login: " + login + " and verification his password");
        final User user = findByLogin(login);
        if (passwordEncoder.matches(password, user.getPassword())) {
            log.info("Find a user with login: " + login + " and verification his password was successful");
            return user;
        } else {
            log.info("Find a user with login: " + login + " and check his password verification was failed");
            throw new EntityNotFoundException("user with this login and password");
        }
    }
}
