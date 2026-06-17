package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.DashboardStatsDto;
import com.hms.hospital_management_system.repository.AppointmentRepository;
import com.hms.hospital_management_system.repository.DoctorRepository;
import com.hms.hospital_management_system.repository.PatientRepository;
import com.hms.hospital_management_system.repository.PrescriptionRepository;
import com.hms.hospital_management_system.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    private final AppointmentRepository appointmentRepository;

    private final PrescriptionRepository prescriptionRepository;


    @Override
    public DashboardStatsDto getDashboardStats() {

        return DashboardStatsDto.builder()
                .totalPatients(patientRepository.count())
                .totalDoctors(doctorRepository.count())
                .totalAppointments(appointmentRepository.count())
                .totalPrescriptions(prescriptionRepository.count())
                .build();
    }
}
