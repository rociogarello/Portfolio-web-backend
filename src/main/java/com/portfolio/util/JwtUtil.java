package com.portfolio.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtil {

    public static final int MINUTES = 15;

    public static String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(asMinutes())
                .sign(algorithm);
    }

    public static String decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }

    private static Date asMinutes() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * MINUTES);
    }


}
