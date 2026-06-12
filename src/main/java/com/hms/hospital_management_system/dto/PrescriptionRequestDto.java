package com.hms.hospital_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrescriptionRequestDto {

    @NotBlank(message = "Diagnosis is Required")
    private String diagnosis;
    @NotBlank(message = "medicines are Required")
    private String medicines;
    @NotBlank(message = "Dosage is Required")
    private  String Dosage;
    @NotBlank(message = "instructions are Required")
    private String instructions;

    @NotNull(message = "Patient Id is required")
    private Long patientId;

}
