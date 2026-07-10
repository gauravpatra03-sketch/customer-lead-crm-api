package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.service.FollowUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followups")
@Tag(name = "Follow Ups", description = "Follow up management APIs")
public class FollowUpController {

    private final FollowUpService followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @PostMapping
    @Operation(summary = "Create follow-up", description = "Create a new follow-up")
    public ResponseEntity<ApiResponse<FollowUpDTO>> createFollowUp(@Valid @RequestBody FollowUpDTO followUpDTO) {
        FollowUpDTO createdFollowUp = followUpService.createFollowUp(followUpDTO);
        return new ResponseEntity<>(ApiResponse.success("Follow-up created successfully", createdFollowUp), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get follow-up by ID", description = "Retrieve follow-up details by ID")
    public ResponseEntity<ApiResponse<FollowUpDTO>> getFollowUpById(@PathVariable Long id) {
        FollowUpDTO followUp = followUpService.getFollowUpById(id);
        return ResponseEntity.ok(ApiResponse.success(followUp));
    }

    @GetMapping("/lead/{leadId}")
    @Operation(summary = "Get follow-ups by lead ID", description = "Retrieve all follow-ups for a specific lead")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getFollowUpsByLeadId(@PathVariable Long leadId) {
        List<FollowUpDTO> followUps = followUpService.getFollowUpsByLeadId(leadId);
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }

    @GetMapping
    @Operation(summary = "Get all follow-ups", description = "Retrieve all follow-ups")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getAllFollowUps() {
        List<FollowUpDTO> followUps = followUpService.getAllFollowUps();
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update follow-up", description = "Update follow-up details")
    public ResponseEntity<ApiResponse<FollowUpDTO>> updateFollowUp(@PathVariable Long id, @Valid @RequestBody FollowUpDTO followUpDTO) {
        FollowUpDTO updatedFollowUp = followUpService.updateFollowUp(id, followUpDTO);
        return ResponseEntity.ok(ApiResponse.success("Follow-up updated successfully", updatedFollowUp));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete follow-up", description = "Delete follow-up by ID")
    public ResponseEntity<ApiResponse<Void>> deleteFollowUp(@PathVariable Long id) {
        followUpService.deleteFollowUp(id);
        return ResponseEntity.ok(ApiResponse.success("Follow-up deleted successfully", null));
    }

    @GetMapping("/today")
    @Operation(summary = "Get today's follow-ups", description = "Retrieve all follow-ups scheduled for today")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getTodayFollowUps() {
        List<FollowUpDTO> followUps = followUpService.getTodayFollowUps();
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }

    @GetMapping("/overdue")
    @Operation(summary = "Get overdue follow-ups", description = "Retrieve all overdue follow-ups")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getOverdueFollowUps() {
        List<FollowUpDTO> followUps = followUpService.getOverdueFollowUps();
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }

    @GetMapping("/tomorrow")
    @Operation(summary = "Get tomorrow's follow-ups", description = "Retrieve all follow-ups scheduled for tomorrow")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getTomorrowFollowUps() {
        List<FollowUpDTO> followUps = followUpService.getTomorrowFollowUps();
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }

    @GetMapping("/recent")
    @Operation(summary = "Get recent follow-ups", description = "Retrieve recent follow-ups for dashboard")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getRecentFollowUps() {
        List<FollowUpDTO> followUps = followUpService.getRecentFollowUps();
        return ResponseEntity.ok(ApiResponse.success(followUps));
    }
}
