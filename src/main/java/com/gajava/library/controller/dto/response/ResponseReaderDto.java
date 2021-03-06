package com.gajava.library.controller.dto.response;

import com.gajava.library.controller.dto.BookDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Response Reader Dto
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ResponseReaderDto {
    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email Name cannot be blank")
    @Email
    private String email;

    @NotNull(message = "Number phone cannot be null")
    @NotBlank(message = "Number phone cannot be blank")
    private String phone;

    @NotNull(message = "Number reader cannot be null")
    private Long numberReader;

    @NotNull(message = "Rating cannot be null")
    private Integer rating;

    private List<BookDto> bookDtoList;
}
