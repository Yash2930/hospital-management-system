package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.auth.LoginRequest;
import com.hms.hospital_management_system.dto.auth.LoginResponse;
import com.hms.hospital_management_system.dto.auth.RegisterRequest;
import com.hms.hospital_management_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }



}
