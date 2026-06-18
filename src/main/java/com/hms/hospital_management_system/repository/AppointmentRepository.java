package com.hms.hospital_management_system.repository;

import com.hms.hospital_management_system.entity.Appointment;
import com.hms.hospital_management_system.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    long countByAppointmentDate(LocalDate appointmentDate);

    long countByStatus(AppointmentStatus status);



}
