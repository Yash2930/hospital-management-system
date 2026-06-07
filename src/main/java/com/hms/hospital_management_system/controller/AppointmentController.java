package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.AppointmentRequestDto;
import com.hms.hospital_management_system.dto.AppointmentResponseDto;
import com.hms.hospital_management_system.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment( @Valid @RequestBody AppointmentRequestDto requestDto){

        AppointmentResponseDto appointment = appointmentService.createAppointment(requestDto);

     return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>>  getAllAppointments(){

          return new ResponseEntity<>(appointmentService.getAllAppointments(),HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id){

       return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id,
                                              @Valid @RequestBody AppointmentRequestDto requestDto){

        return ResponseEntity.ok(appointmentService.updateAppointment(id,requestDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){


        appointmentService.deleteAppointment(id);

        return  ResponseEntity.ok("Appointment deleted successfully");
    }


}
