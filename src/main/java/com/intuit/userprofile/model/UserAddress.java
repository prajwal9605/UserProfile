package com.intuit.userprofile.model;

import lombok.Getter;
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
public class UserAddress {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    String userId;

    String adressType;

    String line1;

    String line2;

    String city;

    String state;

    String pincode;

    String country;

}
