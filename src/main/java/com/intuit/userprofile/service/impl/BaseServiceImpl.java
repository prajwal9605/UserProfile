package com.intuit.userprofile.service.impl;

import com.intuit.userprofile.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author prajwal.kulkarni on 20/09/21
 */
public class BaseServiceImpl {

    protected User getUser() {
        User user = null;
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (auth instanceof User) {
            user = (User) auth;
        }
        return user;
    }
}
