package com.intuit.userprofile.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.util.Date;

/**
 * @author prajwal.kulkarni on 18/09/21
 */
public class JWTUtils {

    private static final String secret = "SecretKey@Intuit123";

    public static String getJWTToken(String userId, String issuer) {
        String token;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create().withIssuer(issuer).withSubject(userId).withIssuedAt(new Date())
                    .withExpiresAt(DateUtil.addSeconds(new Date(), 3600)).sign(algorithm);
            return token;
        } catch (JWTCreationException var5) {
            return null;
        }
    }
}
