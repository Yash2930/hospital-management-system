package com.hms.hospital_management_system.service;

import com.hms.hospital_management_system.dto.auth.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

}
