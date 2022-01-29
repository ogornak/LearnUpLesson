package ru.learnup.lessons.lesson16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.learnup.lessons.lesson16.domain.User;
import ru.learnup.lessons.lesson16.filter.JwtAuthFilter;
import ru.learnup.lessons.lesson16.filter.JwtAuthorizationFilter;
import ru.learnup.lessons.lesson16.jwt.JwtService;

import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtService jwtService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(jwtService, authenticationManager());
        jwtAuthFilter.setFilterProcessesUrl("/api/auth");

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/shop","/api/auth","/api/tokenRefresh").permitAll()
                .antMatchers("/api/v1/shop/basket").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                //.httpBasic();
                .formLogin()
                .loginProcessingUrl("/api/auth")
                .and()
                .addFilter(jwtAuthFilter)
                .addFilterBefore(new JwtAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService(PasswordEncoder encoder){
        return login -> {
            if("user".equals(login)){
                return new User(
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")),
                        encoder.encode("user"),
                        "user");
            }
            else {
                throw new UsernameNotFoundException("User not exists");
            }
        };
    }
}
