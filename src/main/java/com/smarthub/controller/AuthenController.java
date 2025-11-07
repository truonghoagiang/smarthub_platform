package com.smarthub.controller;

import com.smarthub.domain.dto.request.LoginRequest;
import com.smarthub.domain.dto.request.RefrestTokenRequest;
import com.smarthub.domain.dto.request.RegisterRequest;
import com.smarthub.domain.dto.response.AuthenResponse;
import com.smarthub.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenController.class);

    private final AuthService authService;

    public AuthenController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenResponse> register(@RequestBody RegisterRequest request) {
        logger.info("Register request received for email: {}", request.getEmail());
        AuthenResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenResponse> login(@RequestBody LoginRequest request) {
        logger.info("Login request received for username: {}", request.getUsername());
        AuthenResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenResponse> refreshToken(@RequestBody RefrestTokenRequest request) {
        logger.info("Refresh token request received");
        AuthenResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }
}
