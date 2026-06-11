package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.DoctorRequestDto;
import com.hms.hospital_management_system.dto.DoctorResponseDto;
import com.hms.hospital_management_system.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping()
    public  ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){

        List<DoctorResponseDto> allDoctors = doctorService.getAllDoctors();

        return new ResponseEntity<>(allDoctors,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){

        DoctorResponseDto doctorById = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorById);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDto requestDto
            ){

        DoctorResponseDto doctorResponseDto = doctorService.updateDoctor(id, requestDto);
        return new ResponseEntity<>(doctorResponseDto,HttpStatus.OK);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Data Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<DoctorResponseDto>> getDoctorBySpecialization(@PathVariable String specialization){

        List<DoctorResponseDto> doctorBySpecialization = doctorService.getDoctorBySpecialization(specialization);

        return new  ResponseEntity<>(doctorBySpecialization,HttpStatus.OK);
    }

}
