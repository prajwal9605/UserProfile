package com.intuit.userprofile.controller;

import com.intuit.userprofile.dto.ProfileRequestDto;
import com.intuit.userprofile.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@RestController
@RequestMapping(value = "/user")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> createProfile(@RequestBody @Valid ProfileRequestDto profileRequestDto) {
        return userProfileService.createProfile(profileRequestDto);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Object> updateProfile(@RequestBody @Valid ProfileRequestDto profileRequestDto) {
        return userProfileService.updateProfile(profileRequestDto);
    }
}
