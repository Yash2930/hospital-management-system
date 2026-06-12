package com.hms.hospital_management_system.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class PrescriptionResponseDto {

    private Long id;

    private String prescriptionCode;

    private Long patientId;

    private String patientName;

    private String diagnosis;

    private String medicines;

    private  String Dosage;

    private String instructions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
