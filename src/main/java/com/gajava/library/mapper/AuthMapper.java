package com.gajava.library.mapper;

import com.gajava.library.controller.dto.request.RegistrationRequest;
import com.gajava.library.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User fromDto(RegistrationRequest registrationRequest);

}
