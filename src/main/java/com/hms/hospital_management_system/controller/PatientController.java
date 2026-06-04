package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.PatientRequestDto;
import com.hms.hospital_management_system.dto.PatientResponseDto;
import com.hms.hospital_management_system.exception.InvalidSortFieldException;
import com.hms.hospital_management_system.exception.ResourceNotFoundException;
import com.hms.hospital_management_system.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Tag(
        name = "Patient Management APIs",
        description = "APIs for managing hospital patients"
)
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Create a new patient")
    @PostMapping
    public PatientResponseDto createPatient(@Valid @RequestBody PatientRequestDto requestDto) {

        return patientService.createPatient(requestDto);
    }

    @Operation(summary = "Get all patients with pagination and sorting")
    @GetMapping
    public Page<PatientResponseDto> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        List<String> allowedFields = List.of(
                "id",
                "firstName",
                "lastName",
                "createdAt"
        );


        if (!allowedFields.contains(sortBy)) {
            throw new InvalidSortFieldException("Invalid sort field");
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size,sort);

        return patientService.getAllPatients(pageable);
    }

    @Operation(summary = "Get patient by ID")
    @GetMapping("/{id}")
   public PatientResponseDto getPatientById(@PathVariable Long id){

    return patientService.getPatientById(id);
   }

   @Operation(summary = "Update patient details")
   @PutMapping("/{id}")
   public PatientResponseDto updatePatient(@PathVariable Long id, @
                          Valid @RequestBody PatientRequestDto patientRequestDto){

        return patientService.updatePatient(id,patientRequestDto);
   }

   @Operation(summary = "Delete patient")
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public String pateintDeleteById(@PathVariable Long id){
     patientService.deletePatient(id);
        return "Data Deleted";
   }


    @Operation(summary = "Search patients by keyword")
    @GetMapping("/search")
    public List<PatientResponseDto> searchPatients(
            @RequestParam String keyword
    ) {

        return patientService.searchPatients(keyword);
    }

}



