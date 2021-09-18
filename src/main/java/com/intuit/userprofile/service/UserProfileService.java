package com.intuit.userprofile.service;

import com.intuit.userprofile.dto.ProfileRequestDto;
import org.springframework.http.ResponseEntity;

/**
 * @author prajwal.kulkarni on 18/09/21
 */
public interface UserProfileService {

    ResponseEntity<Object> createProfile(ProfileRequestDto profileRequestDto);
}
