package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.PatientRequestDto;
import com.hms.hospital_management_system.dto.PatientResponseDto;
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



        return null;
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
