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
}
