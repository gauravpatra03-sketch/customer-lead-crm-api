package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.ReportSummaryDTO;
import com.crm.customerlead.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Reports management APIs")
public class ReportsController {

    private final ReportService reportService;

    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/summary")
    @Operation(summary = "Get report summary", description = "Retrieve report summary data")
    public ResponseEntity<ApiResponse<ReportSummaryDTO>> getReportSummary() {
        ReportSummaryDTO summary = reportService.getReportSummary();
        return ResponseEntity.ok(ApiResponse.success(summary));
    }
}
