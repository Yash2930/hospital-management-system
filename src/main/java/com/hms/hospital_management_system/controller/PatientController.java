package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.PatientRequestDto;
import com.hms.hospital_management_system.dto.PatientResponseDto;
import com.hms.hospital_management_system.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

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
    public Page<PatientResponseDto> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size,sort);

        return patientService.getAllPatients(pageable);
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



