package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.auth.LoginRequest;
import com.hms.hospital_management_system.dto.auth.LoginResponse;
import com.hms.hospital_management_system.dto.auth.RegisterRequest;
import com.hms.hospital_management_system.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Authentication APIs",
        description = "APIs for user registration, authentication, and JWT token generation."
)
public class AuthController {


    private final AuthService authService;

    AuthController(AuthService authService){
        this.authService=authService;
    }

    @Operation(
            summary = "Register a New User",
            description = "Creates a new user account in the Hospital Management System."
    )
    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    @Operation(
            summary = "Authenticate User",
            description = "Authenticates a user and returns a JWT access token."
    )
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }



}
