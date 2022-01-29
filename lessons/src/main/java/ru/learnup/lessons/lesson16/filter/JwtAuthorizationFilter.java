package ru.learnup.lessons.lesson16.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.learnup.lessons.lesson16.jwt.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    private JwtService jwtService;

    @Autowired
    public JwtAuthorizationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().contains("/api/auth")){
            String token = getToken(request);
            if(token != null){
                jwtService.verify(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    public static String getToken(HttpServletRequest request){
        final String authorization = request.getHeader(AUTHORIZATION);
        if(authorization != null && authorization.startsWith(TOKEN_PREFIX)){
            return authorization.substring(TOKEN_PREFIX.length());
        }else {
            return null;
        }
    }
}
