package com.gajava.library.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * request for update record when reader return book
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RequestUpdateReaderDto {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    @NotNull(message = "Number phone cannot be null")
    @NotBlank(message = "Number phone cannot be blank")
    private String phone;

    @NotNull(message = "Rating cannot be null")
    private Integer rating;
}
