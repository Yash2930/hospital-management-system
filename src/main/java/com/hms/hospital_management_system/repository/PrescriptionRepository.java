package com.hms.hospital_management_system.repository;

import com.hms.hospital_management_system.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository  extends JpaRepository<Prescription,Long> {


}
