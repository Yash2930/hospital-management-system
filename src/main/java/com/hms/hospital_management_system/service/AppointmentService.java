package com.hms.hospital_management_system.service;

import com.hms.hospital_management_system.dto.AppointmentRequestDto;
import com.hms.hospital_management_system.dto.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDto createAppointment(AppointmentRequestDto requestDto);

    List<AppointmentResponseDto> getAllAppointments();

}
