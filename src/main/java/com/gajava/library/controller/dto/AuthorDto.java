package com.gajava.library.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Author Dto
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AuthorDto {
    @NotNull(message = "Full Name cannot be null")
    @NotBlank(message = "Full Name cannot be blank")
    private String fullName;

}
