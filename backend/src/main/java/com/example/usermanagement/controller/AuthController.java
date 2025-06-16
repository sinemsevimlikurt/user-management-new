package com.example.usermanagement.controller;

import com.example.usermanagement.dto.JwtResponse;
import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.MessageResponse;
import com.example.usermanagement.dto.SignupRequest;
import com.example.usermanagement.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/auth", "/auth"})
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("========== LOGIN ATTEMPT ==========");
            logger.info("Login attempt for user: {}", loginRequest.getName());
            logger.info("Request headers: {}", SecurityContextHolder.getContext());
            logger.info("Login request details - Username: {}, Password length: {}", 
                        loginRequest.getName(), 
                        loginRequest.getPassword() != null ? loginRequest.getPassword().length() : 0);

            boolean userExists = authService.existsByName(loginRequest.getName());
            logger.info("User '{}' exists in database: {}", loginRequest.getName(), userExists);

            JwtResponse jwtResponse = authService.authenticateUser(loginRequest);

            logger.info("Login successful for user: {}", loginRequest.getName());
            logger.info("Generated JWT token length: {}", jwtResponse.getToken().length());

            return ResponseEntity.ok(jwtResponse);
        } catch (Exception e) {
            logger.error("========== LOGIN FAILED ==========");
            logger.error("Login failed for user: {}, Error: {}", loginRequest.getName(), e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (authService.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        authService.registerUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}