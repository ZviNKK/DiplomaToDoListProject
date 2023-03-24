package com.diploma.todolist.service.authorisation;

import com.diploma.todolist.service.authorisation.dto.AuthorisationInputDTO;
import com.diploma.todolist.service.authorisation.dto.AuthorisationOutputDTO;
import com.diploma.todolist.service.authorisation.dto.RegistrationInputDTO;

public interface AuthorisationService {
    AuthorisationOutputDTO registrationNewUser(RegistrationInputDTO registrationInputDTO);

    AuthorisationOutputDTO authorisation(AuthorisationInputDTO authorisationInputDTO);
}
