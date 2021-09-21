package com.intuit.userprofile.config.aspect;

import com.intuit.userprofile.model.User;
import com.intuit.userprofile.repo.UserRepo;
import com.intuit.userprofile.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author prajwal.kulkarni on 20/09/21
 */

@Aspect
@Order(1)
@Component
@Slf4j
public class TokenVerificationAspect {

    @Autowired
    UserRepo userRepo;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void beanAnnotatedWithRestController() {
    }

    @Around(value = "beanAnnotatedWithRestController()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        if (request.getRequestURI().startsWith("/auth/login")
                || (request.getRequestURI().startsWith("/user/profile") && request.getMethod().equals(RequestMethod.POST.name()))) {
            return proceedingJoinPoint.proceed();
        } else {
            if (validateUser(request)) {
                return proceedingJoinPoint.proceed();

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }


    private boolean validateUser(HttpServletRequest servletRequest) {
        try {
            String securityToken = servletRequest.getHeader("token");
            String userId = servletRequest.getHeader("User-Key");

            User user = userRepo.getById(userId);

            boolean isReqValid = securityToken != null && JWTUtils.validateToken(securityToken, userId);

            if (isReqValid) {
                return setSecurityContext(user);
            }

        } catch (Exception e) {
            log.error("Could not validate the user. Returning false. " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean setSecurityContext(User user) {

        if (user == null)
            return false;

        // we have authorized the user
        // set auth in security context
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), null);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
        return true;
    }

}
