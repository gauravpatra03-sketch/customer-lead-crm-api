package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.DashboardStatsDTO;
import com.crm.customerlead.service.CustomerLeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Dashboard management APIs")
public class DashboardController {

    private final CustomerLeadService customerLeadService;

    public DashboardController(CustomerLeadService customerLeadService) {
        this.customerLeadService = customerLeadService;
    }

    @GetMapping("/stats")
    @Operation(summary = "Get dashboard statistics", description = "Retrieve dashboard statistics")
    public ResponseEntity<ApiResponse<DashboardStatsDTO>> getDashboardStats() {
        DashboardStatsDTO stats = customerLeadService.getDashboardStats();
        return ResponseEntity.ok(ApiResponse.success(stats));
    }
}
