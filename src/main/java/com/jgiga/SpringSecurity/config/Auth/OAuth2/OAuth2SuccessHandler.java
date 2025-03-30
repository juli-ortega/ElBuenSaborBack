package com.jgiga.SpringSecurity.config.Auth.OAuth2;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jgiga.SpringSecurity.config.Auth.JWT.JWTService;
import com.jgiga.SpringSecurity.config.Auth.Roles.Roles;
import com.jgiga.SpringSecurity.models.Users;
import com.jgiga.SpringSecurity.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        // 1. Extraer información del usuario OAuth2
        String email = oauthUser.getAttribute("email");

        // 2. Verificar si el usuario ya existe en tu base de datos
        Users user = userRepository.findByUsername(email)
                .orElseGet(() -> {
                    // 3. Si no existe, crearlo (similar a tu método register)
                    Users newUser = new Users();
                    newUser.setUsername(email);
                    newUser.setPassword(encoder.encode(UUID.randomUUID().toString())); // Contraseña aleatoria
                    newUser.setRoles(Roles.USER);
                    return userRepository.save(newUser);
                });

        // 4. Generar token JWT (como haces en tu método register)
        String token = jwtService.generateToken(user.getUsername());

        // 5. Devolver el token al cliente
        response.sendRedirect("/oauth2/success?token=" + token);
    }

}