package com.intuit.userprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class UserProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileApplication.class, args);
    }

}
