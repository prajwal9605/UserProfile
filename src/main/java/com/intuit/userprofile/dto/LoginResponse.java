package com.intuit.userprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    String userId;

    String token;
}
