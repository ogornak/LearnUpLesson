package ru.learnup.lessons.lesson16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.learnup.lessons.lesson16.filter.JwtAuthFilter;
import ru.learnup.lessons.lesson16.filter.JwtAuthorizationFilter;
import ru.learnup.lessons.lesson16.jwt.JwtService;
import ru.learnup.lessons.lesson16.service.UserService;

@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    private JwtService jwtService;
    private UserService userService;

    @Autowired
    public WebSecurityConfig(JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

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
            UserDetails userDetails = userService.getUserByLogin(login);
            if(userDetails != null){
                return userDetails;
            }
            else {
                throw new UsernameNotFoundException("User not exists");
            }
        };
    }
}
