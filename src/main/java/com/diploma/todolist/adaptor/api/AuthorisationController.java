package com.diploma.todolist.adaptor.api;

import com.diploma.todolist.service.authorisation.AuthorisationService;
import com.diploma.todolist.service.authorisation.dto.AuthorisationInputDTO;
import com.diploma.todolist.service.authorisation.dto.AuthorisationOutputDTO;
import com.diploma.todolist.service.authorisation.dto.RegistrationInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/authorisation")
public class AuthorisationController {
    private final AuthorisationService service;

    @PostMapping("/registration")
    public AuthorisationOutputDTO registration(@RequestBody @Valid RegistrationInputDTO registrationInputDTO) {
        return service.registrationNewUser(registrationInputDTO);
    }

    @PostMapping
    public AuthorisationOutputDTO authorisation(@RequestBody @Valid AuthorisationInputDTO authorisationInputDTO) {
        return service.authorisation(authorisationInputDTO);
    }
}
