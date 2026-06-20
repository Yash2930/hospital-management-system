package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.AppointmentRequestDto;
import com.hms.hospital_management_system.dto.AppointmentResponseDto;
import com.hms.hospital_management_system.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(
        name = "Appointment Management APIs",
        description = "APIs for managing appointments"
)
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment( @Valid @RequestBody AppointmentRequestDto requestDto){

        AppointmentResponseDto appointment = appointmentService.createAppointment(requestDto);

     return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>>  getAllAppointments(){

          return new ResponseEntity<>(appointmentService.getAllAppointments(),HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id){

       return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}")
    public  ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id,
                                              @Valid @RequestBody AppointmentRequestDto requestDto){

        return ResponseEntity.ok(appointmentService.updateAppointment(id,requestDto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){


        appointmentService.deleteAppointment(id);

        return  ResponseEntity.ok("Appointment deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}/complete")
   public ResponseEntity<AppointmentResponseDto> completeAppointment(@PathVariable Long id){

       AppointmentResponseDto appointmentResponseDto = appointmentService.completeAppointment(id);
       return  ResponseEntity.ok(appointmentResponseDto);
   }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}/cancel")
    public  ResponseEntity<AppointmentResponseDto> cancelAppointment(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.cancelAppointment(id));
    }


}
