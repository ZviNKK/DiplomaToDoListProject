package com.diploma.todolist.authorisation.wrapper;

import com.diploma.todolist.service.authorisation.dto.AuthorisationInputDTO;
import com.diploma.todolist.service.authorisation.dto.RegistrationInputDTO;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class AuthorisationApi {
    private static final String BASE_PATH = "/api/v1";

    public Response registration(RegistrationInputDTO registrationInputDTO) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(registrationInputDTO)
                .post(BASE_PATH + "/registration");
    }

    public Response authorisation(AuthorisationInputDTO authorisationInputDTO) {
        return given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(authorisationInputDTO)
                .post(BASE_PATH + "/authorisation");
    }
}
