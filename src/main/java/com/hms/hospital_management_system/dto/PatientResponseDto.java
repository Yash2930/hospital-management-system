package com.hms.hospital_management_system.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponseDto {
    private Long id;

    private String patientCode;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dateOfBirth;

    private String mobileNumber;

    private String email;

    private String address;

    private String bloodGroup;

    private String disease;

    private String allergies;

    private LocalDateTime createdAt;
}
