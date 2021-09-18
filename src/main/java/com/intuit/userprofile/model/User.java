package com.intuit.userprofile.model;

import com.intuit.userprofile.dto.ProfileRequestDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    String companyName;

    String legalName;

    String email;

    String website;

    String panNumber;

    String ein;

    // encrypted password
    String password;

    public void copyUserDetails(ProfileRequestDto profileRequestDto) {
        this.companyName = profileRequestDto.getCompanyName();
        this.legalName = profileRequestDto.getLegalName();
        this.email = profileRequestDto.getEmail();
        this.website = profileRequestDto.getWebsite();
        this.panNumber = profileRequestDto.getPanNumber();
        this.ein = profileRequestDto.getEin();
        this.password = new BCryptPasswordEncoder().encode(profileRequestDto.getPassword());
    }
}
