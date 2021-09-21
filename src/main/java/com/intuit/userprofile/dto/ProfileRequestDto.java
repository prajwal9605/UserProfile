package com.intuit.userprofile.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Getter
@Setter
public class ProfileRequestDto {

    protected String userId;

    @Email
    protected String email;

    protected String website;

    @NotEmpty
    protected String companyName;

    protected String legalName;

    @NotEmpty
    protected String panNumber;

    protected String ein;

    @NotBlank
    protected String password;

    protected List<AddressDto> addressList;

}
