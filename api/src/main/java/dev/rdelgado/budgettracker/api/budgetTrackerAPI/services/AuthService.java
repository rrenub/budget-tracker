package dev.rdelgado.budgettracker.api.budgetTrackerAPI.services;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.auth.LoginResponse;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.User;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.entities.UserRole;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.enums.UserRoleType;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.exceptions.AppException;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.repositories.RolesRepository;
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

    private final RolesRepository rolesRepository;

    private final PasswordEncoder encoder;

    public void createUser(String username, String email, String password) {
        if(userRepository.existsByEmail(email)) {
            throw new AppException("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByUsername(username)) {
            throw new AppException("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        // Set user role as normal user
        UserRole userRole =  rolesRepository.findByRoleType(UserRoleType.ROLE_USER)
            .orElseThrow(() -> new AppException("Error: Role is not found.", HttpStatus.INTERNAL_SERVER_ERROR));
        Set<UserRole> roles = new HashSet<>(Collections.singletonList(userRole));

        // Create user
        User user = User.builder()
                        .username(username)
                        .email(email)
                        .password(encoder.encode(password))
                        .roles(roles)
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
