package com.hms.hospital_management_system.service;


import com.hms.hospital_management_system.dto.PatientRequestDto;
import com.hms.hospital_management_system.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {

    PatientResponseDto createPatient(PatientRequestDto requestDto);

    List<PatientResponseDto> getAllPatients();

    PatientResponseDto getPatientById(Long id);

    PatientResponseDto updatePatient(Long id, PatientRequestDto requestDto);

    void deletePatient(Long id);

}
