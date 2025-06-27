package com.karrini.Karrini.security;

import com.karrini.Karrini.repository.UserRepository;
import com.karrini.Karrini.service.OAuth2LoginSuccessHandler;
import com.karrini.Karrini.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    public SecurityConfig(OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) {
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup", "/css/**", "/js/**", "/img/**", "/lib/**", "/scss/**").permitAll()
                        .requestMatchers("/", "/about", "/404", "/contact", "/team", "/testimonial", "/category/**", "/course/**", "/forgotPassword/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/courses").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
                        .requestMatchers("/mycourses").hasAnyRole("LEARNER", "INSTRUCTOR")
                        .requestMatchers(HttpMethod.GET, "/learn/course/**").hasRole("LEARNER")
                        .requestMatchers("/courses/**").hasRole("INSTRUCTOR")
                        .requestMatchers(HttpMethod.POST, "/courses").hasRole("INSTRUCTOR")
                        .requestMatchers(HttpMethod.POST, "/enroll", "/unenroll/**").hasRole("LEARNER")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .successHandler(oAuth2LoginSuccessHandler)
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}