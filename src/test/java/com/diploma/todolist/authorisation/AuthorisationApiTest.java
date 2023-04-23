package com.diploma.todolist.authorisation;

import com.diploma.todolist.AbstractApiTest;
import com.diploma.todolist.adaptor.api.controller_advice.ApiErrorResponse;
import com.diploma.todolist.authorisation.wrapper.AuthorisationApi;
import com.diploma.todolist.service.authorisation.dto.AuthorisationInputDTO;
import com.diploma.todolist.service.authorisation.dto.AuthorisationOutputDTO;
import com.diploma.todolist.service.authorisation.dto.RegistrationInputDTO;
import com.diploma.todolist.service.exceptions.ErrorTypes;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class AuthorisationApiTest extends AbstractApiTest {
    private final static AuthorisationApi AUTHORISATION_API = new AuthorisationApi();
    private final static Faker faker = new Faker();

    @Test
    void When_Input_Correct_Data_Then_Get_Success_New_User_Object() {
        final var user = getRegistrationInputDTO();
        AUTHORISATION_API.registration(user)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.SC_OK)
                .body("email", equalTo(user.getEmail()))
                .body("phone", equalTo(user.getPhone()))
                .extract().body().as(AuthorisationOutputDTO.class);
    }

    @Test
    void When_Input_Incorrect_Email_Data_Then_Get_Bad_Request() {
        final var user = getRegistrationInputDTO();
        user.setEmail(UUID.randomUUID().toString());
        AUTHORISATION_API.registration(user)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body().as(ApiErrorResponse.class);
    }

    @Test
    void When_Input_Double_Email_User_Data_Then_Get_Bad_Request() {
        final var user = getRegistrationInputDTO();
        AUTHORISATION_API.registration(user);

        AUTHORISATION_API.registration(user)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body().as(ApiErrorResponse.class);
    }

    @Test
    void When_Input_Correct_Data_Then_Get_Success_Authorisation() {
        final var user = getRegistrationInputDTO();
        AUTHORISATION_API.registration(user);

        AuthorisationInputDTO inputDTO = new AuthorisationInputDTO();
        inputDTO.setEmail(user.getEmail());
        inputDTO.setPassword(user.getPassword());

        AUTHORISATION_API.authorisation(inputDTO)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.SC_OK)
                .body("email", equalTo(user.getEmail()))
                .body("phone", equalTo(user.getPhone()))
                .extract().body().as(AuthorisationOutputDTO.class);
    }

    @Test
    void When_Input_Incorrect_Email_Then_Get_Bad_Request() {
        final var user = getRegistrationInputDTO();

        AuthorisationInputDTO inputDTO = new AuthorisationInputDTO();
        inputDTO.setEmail(user.getEmail());
        inputDTO.setPassword(user.getPassword());

        AUTHORISATION_API.authorisation(inputDTO)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo(ErrorTypes.INCORRECT_LOGIN_OR_PASSWORD.getDescription()))
                .extract().body().as(ApiErrorResponse.class);
    }

    @Test
    void When_Input_Incorrect_Password_Then_Get_Bad_Request() {
        final var user = getRegistrationInputDTO();

        AUTHORISATION_API.registration(user);

        AuthorisationInputDTO inputDTO = new AuthorisationInputDTO();
        inputDTO.setEmail(user.getEmail());
        inputDTO.setPassword(UUID.randomUUID().toString());

        AUTHORISATION_API.authorisation(inputDTO)
                .then()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo(ErrorTypes.INCORRECT_LOGIN_OR_PASSWORD.getDescription()))
                .extract().body().as(ApiErrorResponse.class);
    }

    private RegistrationInputDTO getRegistrationInputDTO() {
        var user = new RegistrationInputDTO();
        user.setEmail(faker.name().firstName() + "@example.com");
        user.setPassword(UUID.randomUUID().toString());
        user.setPhone(faker.phoneNumber().phoneNumber());
        return user;
    }
}
