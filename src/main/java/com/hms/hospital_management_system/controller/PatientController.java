package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.PatientRequestDto;
import com.hms.hospital_management_system.dto.PatientResponseDto;
import com.hms.hospital_management_system.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public PatientResponseDto createPatient(@Valid @RequestBody PatientRequestDto requestDto) {

        return patientService.createPatient(requestDto);
    }

    @GetMapping
    public List<PatientResponseDto> getAllPatients(){

     return  patientService.getAllPatients();
    }

    @GetMapping("/{id}")
   public PatientResponseDto getPatientById(@PathVariable Long id){

    return patientService.getPatientById(id);
   }

   @PutMapping("/{id}")
   public PatientResponseDto updatePatient(@PathVariable Long id, @
                          Valid @RequestBody PatientRequestDto patientRequestDto){

        return patientService.updatePatient(id,patientRequestDto);
   }

   @DeleteMapping("/{id}")
   public String pateintDeleteById(@PathVariable Long id){
     patientService.deletePatient(id);
        return "Data Deleted";
   }

}



