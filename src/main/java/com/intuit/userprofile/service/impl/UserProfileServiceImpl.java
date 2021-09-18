package com.intuit.userprofile.service.impl;

import com.intuit.userprofile.constants.ErrorConstants;
import com.intuit.userprofile.dto.ProfileRequestDto;
import com.intuit.userprofile.model.User;
import com.intuit.userprofile.repo.UserRepo;
import com.intuit.userprofile.service.UserProfileService;
import com.intuit.userprofile.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Component
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<Object> createProfile(ProfileRequestDto profileRequestDto) {
        if (userRepo.getByEmail(profileRequestDto.getEmail()) != null) {
            return ResponseUtils.ok(null, ErrorConstants.ERROR_USER_EXISTS, false);
        }

        User user = new User();
        user.copyUserDetails(profileRequestDto);
        userRepo.save(user);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "Profile Created Successfully");
        return ResponseUtils.ok(dataMap, null, true);
    }
}
