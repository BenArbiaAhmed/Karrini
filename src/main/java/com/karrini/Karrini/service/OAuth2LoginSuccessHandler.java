package com.karrini.Karrini.service;

import com.karrini.Karrini.model.AuthProvider;
import com.karrini.Karrini.model.User;
import com.karrini.Karrini.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public OAuth2LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            String email = oauth2User.getAttribute("email");

            userRepository.findByEmail(email).ifPresentOrElse(
                    user -> {
                        user.setUsername(oauth2User.getAttribute("name"));
                        userRepository.save(user);
                    },
                    () -> {
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setProvider(AuthProvider.GOOGLE);
                        newUser.setUsername(oauth2User.getAttribute("name"));
                        newUser.setFirstName(oauth2User.getAttribute("given_name"));
                        newUser.setLastName(oauth2User.getAttribute("family_name"));
                        userRepository.save(newUser);
                    }
            );
        }

        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl("/");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}