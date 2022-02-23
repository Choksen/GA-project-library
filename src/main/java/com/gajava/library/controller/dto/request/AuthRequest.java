package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * authentication request
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AuthRequest {
    @NotNull(message = "Login cannot be null")
    @NotBlank(message = "Login cannot be blank")
    private String login;
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
