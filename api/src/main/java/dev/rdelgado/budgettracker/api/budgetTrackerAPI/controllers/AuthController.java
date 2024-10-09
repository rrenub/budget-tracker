package dev.rdelgado.budgettracker.api.budgetTrackerAPI.controllers;

import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.auth.LoginRequest;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.auth.LoginResponse;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.dtos.auth.SignUpRequest;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.security.UserDetailsImpl;
import dev.rdelgado.budgettracker.api.budgetTrackerAPI.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.createUser(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
        return ResponseEntity.ok().build();
    }
}
