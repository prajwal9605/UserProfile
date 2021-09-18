package com.intuit.userprofile.controller;

import com.intuit.userprofile.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.FormParam;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> login(@FormParam(value = "username") @NotEmpty String username,
                                        @FormParam(value = "password") @NotEmpty String password) {
        return authenticationService.login(username, password);
    }
}
