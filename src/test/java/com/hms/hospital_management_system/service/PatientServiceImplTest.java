package com.hms.hospital_management_system.service;

import com.hms.hospital_management_system.dto.PatientResponseDto;
import com.hms.hospital_management_system.entity.Patient;
import com.hms.hospital_management_system.exception.ResourceNotFoundException;
import com.hms.hospital_management_system.repository.PatientRepository;
import com.hms.hospital_management_system.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void shouldReturnPatientWhenPatientExists() {
        Patient patient = new Patient();

        patient.setId(1L);
        patient.setPatientCode("PAT001");
        patient.setFirstName("Rahul");
        patient.setLastName("Sharma");
        patient.setEmail("rahul@gmail.com");

        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));

        PatientResponseDto response =
                patientService.getPatientById(1L);

        assertEquals(1L, response.getId());
        assertEquals("PAT001", response.getPatientCode());
        assertEquals("Rahul", response.getFirstName());
    }


    @Test
    void shouldThrowExceptionWhenPatientNotFound() {
        when(patientRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> patientService.getPatientById(1L)
        );
    }
}