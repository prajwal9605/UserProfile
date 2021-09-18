package com.intuit.userprofile.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Getter
@Setter
public class ProfileRequestDto {

    String email;

    String website;

    String companyName;

    String legalName;

    String panNumber;

    String ein;

    String password;

}
