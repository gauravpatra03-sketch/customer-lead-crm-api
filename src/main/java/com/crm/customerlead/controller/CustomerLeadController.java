package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.CustomerLeadDTO;
import com.crm.customerlead.dto.DashboardStatsDTO;
import com.crm.customerlead.dto.SearchRequest;
import com.crm.customerlead.service.CustomerLeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@Tag(name = "Customer Leads", description = "Customer lead management APIs")
public class CustomerLeadController {

    private final CustomerLeadService customerLeadService;

    public CustomerLeadController(CustomerLeadService customerLeadService) {
        this.customerLeadService = customerLeadService;
    }

    @PostMapping
    @Operation(summary = "Create lead", description = "Create a new customer lead")
    public ResponseEntity<ApiResponse<CustomerLeadDTO>> createLead(@Valid @RequestBody CustomerLeadDTO leadDTO) {
        CustomerLeadDTO createdLead = customerLeadService.createLead(leadDTO);
        return new ResponseEntity<>(ApiResponse.success("Lead created successfully", createdLead), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get lead by ID", description = "Retrieve lead details by ID")
    public ResponseEntity<ApiResponse<CustomerLeadDTO>> getLeadById(@PathVariable Long id) {
        CustomerLeadDTO lead = customerLeadService.getLeadById(id);
        return ResponseEntity.ok(ApiResponse.success(lead));
    }

    @GetMapping
    @Operation(summary = "Get all leads", description = "Retrieve all customer leads")
    public ResponseEntity<ApiResponse<List<CustomerLeadDTO>>> getAllLeads() {
        List<CustomerLeadDTO> leads = customerLeadService.getAllLeads();
        return ResponseEntity.ok(ApiResponse.success(leads));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update lead", description = "Update lead details")
    public ResponseEntity<ApiResponse<CustomerLeadDTO>> updateLead(@PathVariable Long id, @Valid @RequestBody CustomerLeadDTO leadDTO) {
        CustomerLeadDTO updatedLead = customerLeadService.updateLead(id, leadDTO);
        return ResponseEntity.ok(ApiResponse.success("Lead updated successfully", updatedLead));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete lead", description = "Delete lead by ID")
    public ResponseEntity<ApiResponse<Void>> deleteLead(@PathVariable Long id) {
        customerLeadService.deleteLead(id);
        return ResponseEntity.ok(ApiResponse.success("Lead deleted successfully", null));
    }

    @PostMapping("/search")
    @Operation(summary = "Search leads", description = "Search leads by various criteria")
    public ResponseEntity<ApiResponse<List<CustomerLeadDTO>>> searchLeads(@RequestBody SearchRequest searchRequest) {
        List<CustomerLeadDTO> leads = customerLeadService.searchLeads(searchRequest);
        return ResponseEntity.ok(ApiResponse.success(leads));
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Get dashboard stats", description = "Retrieve dashboard statistics")
    public ResponseEntity<ApiResponse<DashboardStatsDTO>> getDashboardStats() {
        DashboardStatsDTO stats = customerLeadService.getDashboardStats();
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    @GetMapping("/recent")
    @Operation(summary = "Get recent leads", description = "Retrieve recent leads for dashboard")
    public ResponseEntity<ApiResponse<List<CustomerLeadDTO>>> getRecentLeads() {
        List<CustomerLeadDTO> leads = customerLeadService.getRecentLeads();
        return ResponseEntity.ok(ApiResponse.success(leads));
    }

    @GetMapping("/{leadId}/followups")
    @Operation(summary = "Get follow-ups for a lead", description = "Retrieve all follow-ups for a specific lead")
    public ResponseEntity<ApiResponse<List<com.crm.customerlead.dto.FollowUpDTO>>> getFollowUpsByLeadId(@PathVariable Long leadId) {
        List<com.crm.customerlead.dto.FollowUpDTO> followUps = customerLeadService.getFollowUpsByLeadId(leadId);
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }

    @GetMapping("/{leadId}/notes")
    @Operation(summary = "Get notes for a lead", description = "Retrieve all notes for a specific lead")
    public ResponseEntity<ApiResponse<List<com.crm.customerlead.dto.NotesDTO>>> getNotesByLeadId(@PathVariable Long leadId) {
        List<com.crm.customerlead.dto.NotesDTO> notes = customerLeadService.getNotesByLeadId(leadId);
        return ResponseEntity.ok(ApiResponse.success(notes));
    }
}
