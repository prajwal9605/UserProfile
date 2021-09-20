package com.intuit.userprofile.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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

    public static boolean validateToken(String token, String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withSubject(userId).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt != null;
        } catch (JWTVerificationException var6) {
            return false;
        }
    }
}
