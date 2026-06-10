package com.hms.hospital_management_system.service.impl;

import com.hms.hospital_management_system.dto.DoctorRequestDto;
import com.hms.hospital_management_system.dto.DoctorResponseDto;
import com.hms.hospital_management_system.entity.Doctor;
import com.hms.hospital_management_system.repository.DoctorRepository;
import com.hms.hospital_management_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto requestDto) {

        if (doctorRepository.existsByEmail(
                requestDto.getEmail())) {

            throw new RuntimeException(
                    "Doctor email already exists"
            );
        }

      Doctor doctor=new Doctor();
        doctor.setDoctorCode(
                "DOC" + System.currentTimeMillis()
        );

        doctor.setFullName(requestDto.getFullName());
        doctor.setEmail(requestDto.getEmail());
        doctor.setPhoneNumber(requestDto.getPhoneNumber());
        doctor.setSpecialization(requestDto.getSpecialization());
        doctor.setExperienceYears(requestDto.getExperienceYears());



        Doctor savedDoctor = doctorRepository.save(doctor);

        return mapToResponseDto(savedDoctor) ;
    }

    @Override
    public List<DoctorResponseDto> getAllDoctors() {
        return List.of();
    }

    @Override
    public DoctorResponseDto getDoctorById(Long id) {
        return null;
    }

    @Override
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteDoctor(Long id) {

    }

    @Override
    public List<DoctorResponseDto> getDoctorBySpecialization(String specialization) {
        return List.of();
    }


    private DoctorResponseDto mapToResponseDto(
            Doctor doctor) {

        return DoctorResponseDto.builder()
                .id(doctor.getId())
                .doctorCode(doctor.getDoctorCode())
                .fullName(doctor.getFullName())
                .email(doctor.getEmail())
                .phoneNumber(doctor.getPhoneNumber())
                .specialization(doctor.getSpecialization())
                .experienceYears(
                        doctor.getExperienceYears()
                )
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
}
