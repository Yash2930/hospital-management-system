package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.repository.PatientRepository;
import com.hms.hospital_management_system.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl {

    private final PrescriptionRepository prescriptionRepository;

     private final PatientRepository patientRepository;

}
