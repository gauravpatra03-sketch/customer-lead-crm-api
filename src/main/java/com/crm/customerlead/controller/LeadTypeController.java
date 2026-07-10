package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.LeadTypeDTO;
import com.crm.customerlead.service.LeadTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lead-types")
@Tag(name = "Lead Types", description = "Lead type management APIs")
public class LeadTypeController {

    private final LeadTypeService leadTypeService;

    public LeadTypeController(LeadTypeService leadTypeService) {
        this.leadTypeService = leadTypeService;
    }

    @PostMapping
    @Operation(summary = "Create lead type", description = "Create a new lead type")
    public ResponseEntity<ApiResponse<LeadTypeDTO>> createLeadType(@Valid @RequestBody LeadTypeDTO leadTypeDTO) {
        LeadTypeDTO createdLeadType = leadTypeService.createLeadType(leadTypeDTO);
        return new ResponseEntity<>(ApiResponse.success("Lead type created successfully", createdLeadType), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get lead type by ID", description = "Retrieve lead type details by ID")
    public ResponseEntity<ApiResponse<LeadTypeDTO>> getLeadTypeById(@PathVariable Long id) {
        LeadTypeDTO leadType = leadTypeService.getLeadTypeById(id);
        return ResponseEntity.ok(ApiResponse.success(leadType));
    }

    @GetMapping
    @Operation(summary = "Get all lead types", description = "Retrieve all lead types")
    public ResponseEntity<ApiResponse<List<LeadTypeDTO>>> getAllLeadTypes() {
        List<LeadTypeDTO> leadTypes = leadTypeService.getAllLeadTypes();
        return ResponseEntity.ok(ApiResponse.success(leadTypes));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update lead type", description = "Update lead type details")
    public ResponseEntity<ApiResponse<LeadTypeDTO>> updateLeadType(@PathVariable Long id, @Valid @RequestBody LeadTypeDTO leadTypeDTO) {
        LeadTypeDTO updatedLeadType = leadTypeService.updateLeadType(id, leadTypeDTO);
        return ResponseEntity.ok(ApiResponse.success("Lead type updated successfully", updatedLeadType));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete lead type", description = "Delete lead type by ID")
    public ResponseEntity<ApiResponse<Void>> deleteLeadType(@PathVariable Long id) {
        leadTypeService.deleteLeadType(id);
        return ResponseEntity.ok(ApiResponse.success("Lead type deleted successfully", null));
    }
}
