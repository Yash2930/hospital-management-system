package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.auth.LoginRequest;
import com.hms.hospital_management_system.dto.auth.LoginResponse;
import com.hms.hospital_management_system.dto.auth.RegisterRequest;
import com.hms.hospital_management_system.entity.User;
import com.hms.hospital_management_system.exception.InvalidCredentialsException;
import com.hms.hospital_management_system.repository.UserRepository;
import com.hms.hospital_management_system.security.JwtService;
import com.hms.hospital_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));

        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );

        return new LoginResponse(token);
    }

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}
