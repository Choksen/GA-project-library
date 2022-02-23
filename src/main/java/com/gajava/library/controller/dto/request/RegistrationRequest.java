package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * request registration
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RegistrationRequest {
    @NotNull(message = "Login cannot be null")
    @NotBlank(message = "Login cannot be blank")
    private String login;
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8,max = 20)
    private String password;
}
