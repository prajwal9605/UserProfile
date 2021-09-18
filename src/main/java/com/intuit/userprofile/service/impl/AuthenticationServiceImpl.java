package com.intuit.userprofile.service.impl;

import com.intuit.userprofile.constants.ErrorConstants;
import com.intuit.userprofile.dto.LoginResponse;
import com.intuit.userprofile.model.User;
import com.intuit.userprofile.repo.UserRepo;
import com.intuit.userprofile.service.AuthenticationService;
import com.intuit.userprofile.util.JWTUtils;
import com.intuit.userprofile.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<Object> login(String username, String password) {
        /*
        1. Validate the username and password
        2. If login credentials are valid, generate a token and send back to the UI in
        response along with other user details like userId
        */
        User user = userRepo.getByEmail(username);
        if (user == null) {
            return ResponseUtils.ok(null, ErrorConstants.ERROR_USERNAME_INVALID, false);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(password, user.getPassword());

        if (matches) {
            Map<String, Object> dataMap = new HashMap<>();

            String jwtToken = JWTUtils.getJWTToken(user.getId(), "Intuit");
            dataMap.put("userDetails", new LoginResponse(user.getId(), jwtToken));
            return ResponseUtils.ok(dataMap, null, true);
        }
        return ResponseUtils.ok(null, ErrorConstants.ERROR_WRONG_PASSWORD, false);
    }
}
