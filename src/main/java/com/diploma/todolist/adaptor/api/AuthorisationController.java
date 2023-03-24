package com.diploma.todolist.adaptor.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/authorisation")
public class AuthorisationController {

    @PostMapping("registration")
    public String registration(){
        return "success";
    }

    @PostMapping("login")
    public Boolean authorisation(){
        return true;
    }
}
