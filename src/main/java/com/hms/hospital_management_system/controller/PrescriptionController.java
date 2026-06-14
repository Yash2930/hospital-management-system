package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.PrescriptionRequestDto;
import com.hms.hospital_management_system.dto.PrescriptionResponseDto;
import com.hms.hospital_management_system.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<PrescriptionResponseDto>
    createPrescription(
            @Valid @RequestBody
            PrescriptionRequestDto requestDto){

        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(
                        prescriptionService
                                .createPrescription(
                                        requestDto));
    }
}
