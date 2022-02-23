package com.gajava.library.service;

import com.gajava.library.exception.EntityNotFoundException;
import com.gajava.library.exception.InvalidArgumentsException;
import com.gajava.library.model.Role;
import com.gajava.library.model.User;
import com.gajava.library.repository.RoleRepository;
import com.gajava.library.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private UserServiceImpl userService;

    private final User user = new User();
    private final Role userRole = new Role();
    private String login;
    private String password;

    @Before
    public void setUp() {
        login = "Max";
        password = "cool";

        userRole.setName("ROLE_USER");
        user.setLogin(login);
        user.setPassword(password);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void save_userIsNull_throwException() {
        userService.save(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void save_returnRoleIsNull_throwException() {
        when(roleRepository.findRoleByName("ROLE_USER")).thenReturn(null);
        userService.save(user);
    }

    @Test
    public void save() {
        when(roleRepository.findRoleByName("ROLE_USER")).thenReturn(userRole);
        when(userRepository.save(user)).thenReturn(user);
        userService.save(user);
    }

    @Test
    public void findByLogin() {
        when(userRepository.findUserByLogin(login)).thenReturn(user);
        userService.findByLogin(login);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByLoginAndPassword_noMatches_throwException() {
        when(userRepository.findUserByLogin(login)).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);
        userService.findByLoginAndPassword(login,password);
    }

    /*@Test
    public void findByLoginAndPassword() {
        when(userRepository.findUserByLogin(login)).thenReturn(user);
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
        userService.findByLoginAndPassword(login,password);
    }*/
}