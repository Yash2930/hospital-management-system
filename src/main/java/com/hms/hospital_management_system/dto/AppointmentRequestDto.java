package com.hms.hospital_management_system.dto;

import com.hms.hospital_management_system.enums.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequestDto {

    private String patientId;

    private String doctorName;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String remarks;
}
