package com.intuit.userprofile.model;

import com.intuit.userprofile.dto.ProfileRequestDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "user_subscription_info",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;

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
