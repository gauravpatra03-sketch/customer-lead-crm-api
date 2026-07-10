package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.service.ReminderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@Tag(name = "Reminders", description = "Reminders management APIs")
public class RemindersController {

    private final ReminderService reminderService;

    public RemindersController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @GetMapping("/today")
    @Operation(summary = "Get today's reminders", description = "Retrieve all reminders for today")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getTodayReminders() {
        List<FollowUpDTO> reminders = reminderService.getTodayReminders();
        return ResponseEntity.ok(ApiResponse.success(reminders));
    }

    @GetMapping("/upcoming")
    @Operation(summary = "Get upcoming reminders", description = "Retrieve all upcoming reminders")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getUpcomingReminders() {
        List<FollowUpDTO> reminders = reminderService.getUpcomingReminders();
        return ResponseEntity.ok(ApiResponse.success(reminders));
    }

    @GetMapping("/overdue")
    @Operation(summary = "Get overdue reminders", description = "Retrieve all overdue reminders")
    public ResponseEntity<ApiResponse<List<FollowUpDTO>>> getOverdueReminders() {
        List<FollowUpDTO> reminders = reminderService.getOverdueReminders();
        return ResponseEntity.ok(ApiResponse.success(reminders));
    }
}
