package com.pas.rest_pas.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.util.ArrayList;
import java.util.Date;


@ApplicationScoped
public class JWTMechanism {

    private static final long JWS_TIMEOUT_MS = 30 * 60 * 1000;
    private static final Algorithm algorithm = Algorithm.RSA256(Keys.getPublicKey(), Keys.getPrivateKey());
    private static final String ISSUER = "PAS_2022";

    public static String generateJWT(CredentialValidationResult credentialValidationResult) {

        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("groups", new ArrayList<>(credentialValidationResult.getCallerGroups()))
                .withClaim("userid", credentialValidationResult.getCallerUniqueId())
                .withSubject(credentialValidationResult.getCallerPrincipal().getName())
                .withExpiresAt(new Date(new Date().getTime() + JWS_TIMEOUT_MS))
                .withIssuedAt(new Date(new Date().getTime()))
                .withJWTId("a-123")
                .withAudience("s6BhdRkqt3")
                .withClaim("upn", credentialValidationResult.getCallerPrincipal().getName())
                .sign(algorithm);
    }

    public static DecodedJWT validateJWT(String jwtSerializedToken) throws JWTVerificationException {

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();


        try {
            return verifier.verify(jwtSerializedToken);
        } catch (JWTVerificationException e) {
            throw e;
        }
    }
}

