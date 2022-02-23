package com.gajava.library.controller;

import com.gajava.library.config.jwt.JwtProvider;
import com.gajava.library.controller.dto.request.AuthRequest;
import com.gajava.library.controller.dto.request.RegistrationRequest;
import com.gajava.library.controller.dto.response.AuthResponse;
import com.gajava.library.mapper.AuthMapper;
import com.gajava.library.model.User;
import com.gajava.library.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userServiceImpl;
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid final RegistrationRequest registrationRequest) {
        final User user = authMapper.fromDto(registrationRequest);
        userServiceImpl.save(user);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody final AuthRequest request) {
        final User user = userServiceImpl.findByLoginAndPassword(request.getLogin(), request.getPassword());
        final String token = jwtProvider.generateToken(user.getLogin());
        return new ResponseEntity<>(new AuthResponse(token),HttpStatus.OK);
    }
}
