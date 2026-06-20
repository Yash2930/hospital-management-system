package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.DoctorRequestDto;
import com.hms.hospital_management_system.dto.DoctorResponseDto;
import com.hms.hospital_management_system.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@Tag(
        name = "Doctor Management APIs",
        description = "APIs for managing hospital doctors"
)
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @Operation(summary = "Create a new Doctor")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto requestDto){

        DoctorResponseDto doctor = doctorService.createDoctor(requestDto);

        return new  ResponseEntity<DoctorResponseDto>(doctor, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all doctors")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping()
    public  ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){

        List<DoctorResponseDto> allDoctors = doctorService.getAllDoctors();

        return new ResponseEntity<>(allDoctors,HttpStatus.OK);
    }

    @Operation(summary = "Get  doctor by ID")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){

        DoctorResponseDto doctorById = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorById);

    }

    @Operation(summary = "Update doctor details")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDto requestDto
            ){

        DoctorResponseDto doctorResponseDto = doctorService.updateDoctor(id, requestDto);
        return new ResponseEntity<>(doctorResponseDto,HttpStatus.OK);
    }

    @Operation(summary = "Delete doctor")
    @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Data Deleted Successfully",HttpStatus.OK);
    }


    @Operation(summary = "Get doctor by specialization")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<DoctorResponseDto>> getDoctorBySpecialization(@PathVariable String specialization){

        List<DoctorResponseDto> doctorBySpecialization = doctorService.getDoctorBySpecialization(specialization);

        return new  ResponseEntity<>(doctorBySpecialization,HttpStatus.OK);
    }

}
