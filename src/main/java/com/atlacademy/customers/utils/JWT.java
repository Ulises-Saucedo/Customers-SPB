package com.atlacademy.customers.utils;

import com.atlacademy.customers.entities.User;
import com.atlacademy.customers.repositories.UserRepository;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class JWT {

    private static final Dotenv dotenv = Dotenv.configure().load();

    private static final String SECRET_KEY = dotenv.get("SECRET_KEY");

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    @Autowired
    UserRepository userRepository;

    public static String createToken(User user) {
        String jwtToken = com.auth0.jwt.JWT.create()
                .withIssuer("ATLAcademy")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(getExpiresAt())
                .sign(algorithm);

        return jwtToken;
    }

    public static String getUserByToken(String token) {
        JWTVerifier jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
                .withIssuer("ATLAcademy")
                .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        String userId = decodedJWT.getClaim("userId").toString();

        return userId;
    }

    private static Date getExpiresAt() {
        return new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 14)); // 14 Días
    }
}
