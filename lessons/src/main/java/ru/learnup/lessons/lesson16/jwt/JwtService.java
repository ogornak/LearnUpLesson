package ru.learnup.lessons.lesson16.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtService {
    private static long ACCESS_TOKEN_TIME = 60000;
    private static long REFRESH_TOKEN_TIME = 3600000 * 10;

    private Map<String, String> refresh = new HashMap<>();
    private Algorithm jwtAlgorithm;

    public JwtService(@Value("${auth.jwt.secret}") String secret){
        jwtAlgorithm = Algorithm.HMAC256(secret);
    }

    public String generateAccessToken(UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return JWT.create()
                .withIssuer("vtb.ru")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME + 1000))
                .withSubject(userDetails.getUsername())
                .withClaim("roles", roles)
                .sign(jwtAlgorithm);
    }
    public String generateRefreshToken(UserDetails userDetails){
        String token = JWT.create()
                .withIssuer("vtb.ru")
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME + 1000))
                .withSubject(userDetails.getUsername())
                .sign(jwtAlgorithm);

        refresh.put(userDetails.getUsername(), token);
        return token;
    }

    public boolean verifyRefresh(String token) {
        JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
        final DecodedJWT decodedJWT = verifier.verify(token);
        final String username = decodedJWT.getSubject();
        final String tokenFromDB = refresh.get(username);
        return token.equals(tokenFromDB);
    }

    public void invalidateRefreshToken(String username){
        refresh.remove(username);
    }

    public boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
            final DecodedJWT decodedJWT = verifier.verify(token);
            final String username = decodedJWT.getSubject();
            final List<String> rolesList = decodedJWT.getClaim("roles").asList(String.class);
            final List<GrantedAuthority> roles = rolesList.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            var authentication = new UsernamePasswordAuthenticationToken(username, null, roles);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String getUsernameFromRefreshToken(String token){
        try {
            JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
            final DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
