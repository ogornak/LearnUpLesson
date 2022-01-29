package ru.learnup.lessons.lesson16.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.learnup.lessons.lesson16.jwt.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public JwtAuthFilter(JwtService jwtService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        final String username = request.getHeader("username");
        final String password = request.getHeader("password");

        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password, null)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws ServletException, IOException {
        final UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        final String accessToken = jwtService.generateAccessToken(userDetails);
        final String refreshToken = jwtService.generateRefreshToken(userDetails);
        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
        super.successfulAuthentication(request, response, filterChain, authentication);
    }
}
