package dev.rdelgado.budgettracker.api.budgetTrackerAPI.services;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.auth.LoginResponse;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.User;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions.AppException;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories.UserRepository;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.security.UserDetailsImpl;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;


    private final PasswordEncoder encoder;

    public void createUser(String username, String email, String password) {
        if(userRepository.existsByEmail(email)) {
            throw new AppException("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByUsername(username)) {
            throw new AppException("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        // Create user
        User user = User.builder()
                        .username(username)
                        .email(email)
                        .password(encoder.encode(password))
                        .build();

        userRepository.save(user);
    }

    public LoginResponse loginUser(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    public String generateJwtToken(Authentication authentication) {
        return jwtUtils.generateJwtToken(authentication);
    }
}
