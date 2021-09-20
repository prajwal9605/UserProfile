package com.intuit.userprofile.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author prajwal.kulkarni on 20/09/21
 */

@Getter
@Setter
public class AddressDto {

    private String addressType;

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String pincode;

    private String country;

}
