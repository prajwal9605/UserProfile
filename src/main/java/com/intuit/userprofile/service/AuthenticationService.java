package com.intuit.userprofile.service;

import org.springframework.http.ResponseEntity;

/**
 * @author prajwal.kulkarni on 18/09/21
 */
public interface AuthenticationService {

    ResponseEntity<Object> login(String username, String password);

}
