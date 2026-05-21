package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.PatientRequestDto;
import com.hms.hospital_management_system.dto.PatientResponseDto;
import com.hms.hospital_management_system.entity.Patient;
import com.hms.hospital_management_system.repository.PatientRepository;
import com.hms.hospital_management_system.service.PatientService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto requestDto) {

        Patient patient = Patient.builder()
                .patientCode("PAT" + System.currentTimeMillis())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .gender(requestDto.getGender())
                .dateOfBirth(requestDto.getDateOfBirth())
                .mobileNumber(requestDto.getMobileNumber())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .bloodGroup(requestDto.getBloodGroup())
                .disease(requestDto.getDisease())
                .allergies(requestDto.getAllergies())
                .build();

        Patient savedPatient = patientRepository.save(patient);

        PatientResponseDto responseDto = new PatientResponseDto();

        responseDto.setId(savedPatient.getId());
        responseDto.setPatientCode(savedPatient.getPatientCode());
        responseDto.setFirstName(savedPatient.getFirstName());
        responseDto.setLastName(savedPatient.getLastName());
        responseDto.setGender(savedPatient.getGender());
        responseDto.setDateOfBirth(savedPatient.getDateOfBirth());
        responseDto.setMobileNumber(savedPatient.getMobileNumber());
        responseDto.setEmail(savedPatient.getEmail());
        responseDto.setAddress(savedPatient.getAddress());
        responseDto.setBloodGroup(savedPatient.getBloodGroup());
        responseDto.setDisease(savedPatient.getDisease());
        responseDto.setAllergies(savedPatient.getAllergies());
        responseDto.setCreatedAt(savedPatient.getCreatedAt());

        return responseDto;

    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        return List.of();
    }

    @Override
    public PatientResponseDto getPatientById(Long id) {
        return null;
    }

    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto requestDto) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }
}
