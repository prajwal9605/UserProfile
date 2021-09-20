package com.intuit.userprofile.model;

import com.intuit.userprofile.dto.AddressDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author prajwal.kulkarni on 18/09/21
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserAddress {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    String userId;

    String addressType;

    String line_1;

    String line_2;

    String city;

    String state;

    String pincode;

    String country;

    public UserAddress(AddressDto addressDto) {
        this.addressType = addressDto.getAddressType();
        this.line_1 = addressDto.getLine1();
        this.line_2 = addressDto.getLine2();
        this.city = addressDto.getCity();
        this.state = addressDto.getState();
        this.pincode = addressDto.getPincode();
        this.country = addressDto.getCountry();
    }

}
