package com.intuit.userprofile.service.impl;

import com.intuit.userprofile.constants.ErrorConstants;
import com.intuit.userprofile.dto.ProfileRequestDto;
import com.intuit.userprofile.model.Product;
import com.intuit.userprofile.model.User;
import com.intuit.userprofile.model.UserAddress;
import com.intuit.userprofile.repo.UserAddressRepo;
import com.intuit.userprofile.repo.UserRepo;
import com.intuit.userprofile.service.UserProfileService;
import com.intuit.userprofile.util.HttpUtils;
import com.intuit.userprofile.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Component
public class UserProfileServiceImpl extends BaseServiceImpl implements UserProfileService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserAddressRepo userAddressRepo;

    @Override
    public ResponseEntity<Object> createProfile(ProfileRequestDto profileRequestDto) {
        if (userRepo.getByEmail(profileRequestDto.getEmail()) != null) {
            return ResponseUtils.ok(null, ErrorConstants.ERROR_USER_EXISTS, false);
        }

        User user = new User();
        saveUserDetails(user, profileRequestDto);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "Profile Created Successfully");
        return ResponseUtils.ok(dataMap, null, true);
    }

    //    @HystrixCommand(fallbackMethod = "updateFallbackMethod")
    @Override
    public ResponseEntity<Object> updateProfile(ProfileRequestDto profileRequestDto) {
        User user = userRepo.getById(getUser().getId());

        List<Product> products = user.getProducts();
        boolean updated = products.parallelStream().allMatch(product -> {
            String baseUrl = product.getBaseUrl();
            try {
                return HttpUtils.buildGenericPost(baseUrl + "user/profile", profileRequestDto, Boolean.class, null);
            } catch (Exception e) {
                return false;
            }
        });

        if (updated) {
            saveUserDetails(user, profileRequestDto);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("status", "Profile Updated Successfully");
            return ResponseUtils.ok(dataMap, null, true);
        }
        return ResponseUtils.ok(null, "Profile was not updated!", false);
    }

    private ResponseEntity<Object> updateFallbackMethod(ProfileRequestDto profileRequestDto) {
        return ResponseUtils.ok(null, "Backend is experiencing a downtime. Please try again after sometime!", false);
    }

    private void saveUserDetails(User user, ProfileRequestDto profileRequestDto) {
        user.copyUserDetails(profileRequestDto);
        userRepo.save(user);

        if (profileRequestDto.getAddressList() != null && !profileRequestDto.getAddressList().isEmpty()) {
            userAddressRepo.saveAll(profileRequestDto.getAddressList().stream().map(UserAddress::new)
                    .peek(userAddress -> userAddress.setUserId(user.getId())).collect(Collectors.toList()));
        }
    }
}
