package com.diploma.todolist.service.authorisation.impl;

import com.diploma.todolist.adaptor.persistence.UserRepository;
import com.diploma.todolist.service.authorisation.AuthorisationService;
import com.diploma.todolist.service.authorisation.dto.AuthorisationOutputDTO;
import com.diploma.todolist.service.authorisation.dto.RegistrationInputDTO;
import com.diploma.todolist.service.authorisation.mapper.UserMapper;
import com.diploma.todolist.service.exceptions.error_code.bad_request.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorisationServiceImpl implements AuthorisationService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public AuthorisationOutputDTO registrationNewUser(RegistrationInputDTO registrationInputDTO) {
        final var email = registrationInputDTO.getEmail();
        if (repository.findByEmail(email).isPresent()) {
            log.error("User on this email already exists. Email: {}. Input data: {}", email, registrationInputDTO);
            throw new UserAlreadyExistException();
        }
        final var user = mapper.registrationDtoToUser(registrationInputDTO);
        repository.save(user);
        return mapper.toAuthDto(user);
    }
}
