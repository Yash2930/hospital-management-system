package com.hms.hospital_management_system.service;

import com.hms.hospital_management_system.dto.PrescriptionRequestDto;
import com.hms.hospital_management_system.dto.PrescriptionResponseDto;

public interface PrescriptionService {

    PrescriptionResponseDto createPrescription(
            PrescriptionRequestDto requestDto);

}
