package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.PrescriptionRequestDto;
import com.hms.hospital_management_system.dto.PrescriptionResponseDto;
import com.hms.hospital_management_system.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
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

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDto>> getAllPrescription(){

        return ResponseEntity.ok(prescriptionService.getAllPrescription());

    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDto> getPrescriptionById(@PathVariable Long id){

        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }


    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDto> updatePrescription(
                  @PathVariable Long id,
                  @Valid @RequestBody PrescriptionRequestDto requestDto
                                                  )
    {

        return  ResponseEntity.ok(prescriptionService.updatePrescription(id,requestDto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id){

        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok("Prescription deleted!!!");
    }


    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PrescriptionResponseDto>>
    getPrescriptionsByPatientId(
            @PathVariable Long patientId){

        return ResponseEntity.ok(
                prescriptionService
                        .getPrescriptionsByPatientId(
                                patientId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PrescriptionResponseDto>>
    getPrescriptionsByDoctorId(
            @PathVariable Long doctorId){

        return ResponseEntity.ok(
                prescriptionService
                        .getPrescriptionsByDoctorId(
                                doctorId));
    }
}
