package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.DoctorRequestDto;
import com.hms.hospital_management_system.dto.DoctorResponseDto;
import com.hms.hospital_management_system.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto requestDto){

        DoctorResponseDto doctor = doctorService.createDoctor(requestDto);

        return new  ResponseEntity<DoctorResponseDto>(doctor, HttpStatus.CREATED);
    }


}
