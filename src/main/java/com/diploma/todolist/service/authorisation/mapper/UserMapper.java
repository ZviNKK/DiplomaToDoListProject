package com.diploma.todolist.service.authorisation.mapper;

import com.diploma.todolist.adaptor.persistence.domain.User;
import com.diploma.todolist.service.authorisation.dto.AuthorisationInputDTO;
import com.diploma.todolist.service.authorisation.dto.AuthorisationOutputDTO;
import com.diploma.todolist.service.authorisation.dto.RegistrationInputDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registrationDtoToUser(RegistrationInputDTO registrationInputDTO);

    AuthorisationOutputDTO toAuthDto(User user);

    User authorisationDtoToUser(AuthorisationInputDTO authorisationInputDTO);
}
