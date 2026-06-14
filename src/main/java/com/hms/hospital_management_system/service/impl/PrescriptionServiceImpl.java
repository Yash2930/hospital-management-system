package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.PrescriptionRequestDto;
import com.hms.hospital_management_system.dto.PrescriptionResponseDto;
import com.hms.hospital_management_system.entity.Patient;
import com.hms.hospital_management_system.entity.Prescription;
import com.hms.hospital_management_system.exception.ResourceNotFoundException;
import com.hms.hospital_management_system.repository.PatientRepository;
import com.hms.hospital_management_system.repository.PrescriptionRepository;
import com.hms.hospital_management_system.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

     private final PatientRepository patientRepository;


    @Override
    public PrescriptionResponseDto createPrescription(
            PrescriptionRequestDto requestDto) {

        Patient patient = patientRepository
                .findById(requestDto.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found"));

        Prescription prescription =
                new Prescription();

        prescription.setPrescriptionCode(
                "PRS" + System.currentTimeMillis());

        prescription.setDiagnosis(
                requestDto.getDiagnosis());

        prescription.setMedicines(
                requestDto.getMedicines());

        prescription.setDosage(
                requestDto.getDosage());

        prescription.setInstructions(
                requestDto.getInstructions());

        prescription.setPatient(patient);

        Prescription savedPrescription =
                prescriptionRepository.save(
                        prescription);

        return mapToResponseDto(
                savedPrescription);
    }

    private PrescriptionResponseDto mapToResponseDto(
            Prescription prescription){

        return PrescriptionResponseDto.builder()
                .id(prescription.getId())
                .prescriptionCode(
                        prescription.getPrescriptionCode())
                .patientId(
                        prescription.getPatient().getId())
                .patientName(
                        prescription.getPatient().getFirstName())
                .diagnosis(
                        prescription.getDiagnosis())
                .medicines(
                        prescription.getMedicines())
                .dosage(
                        prescription.getDosage())
                .instructions(
                        prescription.getInstructions())
                .createdAt(
                        prescription.getCreatedAt())
                .updatedAt(
                        prescription.getUpdatedAt())
                .build();
    }

}
