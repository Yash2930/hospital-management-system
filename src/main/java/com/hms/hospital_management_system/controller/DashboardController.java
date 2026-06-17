package com.hms.hospital_management_system.controller;

import com.hms.hospital_management_system.dto.DashboardStatsDto;
import com.hms.hospital_management_system.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

private final DashboardService dashboardService;

    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/stats")
public ResponseEntity<DashboardStatsDto> getDashboardStats(){

    return ResponseEntity.ok(dashboardService.getDashboardStats());
}


}
