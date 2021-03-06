package com.gajava.library.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Response authentication
 */
@Setter
@Getter
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
