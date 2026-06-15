package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.PrescriptionRequestDto;
import com.hms.hospital_management_system.dto.PrescriptionResponseDto;
import com.hms.hospital_management_system.entity.Doctor;
import com.hms.hospital_management_system.entity.Patient;
import com.hms.hospital_management_system.entity.Prescription;
import com.hms.hospital_management_system.exception.ResourceNotFoundException;
import com.hms.hospital_management_system.repository.DoctorRepository;
import com.hms.hospital_management_system.repository.PatientRepository;
import com.hms.hospital_management_system.repository.PrescriptionRepository;
import com.hms.hospital_management_system.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

     private final PatientRepository patientRepository;

     private final DoctorRepository doctorRepository;


    @Override
    public List<PrescriptionResponseDto> getAllPrescription() {

        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptions.stream().
                map(this::mapToResponseDto).toList();
    }

    @Override
    public PrescriptionResponseDto getPrescriptionById(Long id) {

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not Found"));
        return mapToResponseDto(prescription);
    }

    @Override
    public PrescriptionResponseDto updatePrescription(Long id, PrescriptionRequestDto requestDto) {

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not Found"));


        Patient patient = patientRepository
                .findById(requestDto.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found"));


        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        prescription.setDiagnosis(
                requestDto.getDiagnosis());

        prescription.setMedicines(
                requestDto.getMedicines());

        prescription.setDosage(
                requestDto.getDosage());

        prescription.setInstructions(
                requestDto.getInstructions());

        prescription.setPatient(patient);

        prescription.setDoctor(doctor);

        return mapToResponseDto(prescription);
    }

    @Override
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not Found"));
        prescriptionRepository.deleteById(id);
    }

    @Override
    public PrescriptionResponseDto createPrescription(
            PrescriptionRequestDto requestDto) {

        Patient patient = patientRepository
                .findById(requestDto.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found"));


        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

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

        prescription.setDoctor(doctor);

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
                .doctorId(
                        prescription.getDoctor().getId())
                .doctorName(
                        prescription.getDoctor().getFullName())
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
