package ru.learnup.lessons.lesson16.controller.rest.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.learnup.lessons.lesson16.filter.JwtAuthorizationFilter;
import ru.learnup.lessons.lesson16.jwt.JwtService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthController(JwtService jwtService, UserDetailsService userDetailsService){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/api/tokenRefresh")
    public void tokenRefresh(HttpServletRequest request, HttpServletResponse response){
        String token = JwtAuthorizationFilter.getToken(request);

        if(!jwtService.verifyRefresh(token)){
            return;
        }

        final String username = jwtService.getUsernameFromRefreshToken(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(userDetails != null) {
            jwtService.invalidateRefreshToken(token);
            final String refreshToken = jwtService.generateRefreshToken(userDetails);
            final String accessToken = jwtService.generateAccessToken(userDetails);
            response.setHeader("access_token", accessToken);
            response.setHeader("refresh_token", refreshToken);
        }

    }
}
