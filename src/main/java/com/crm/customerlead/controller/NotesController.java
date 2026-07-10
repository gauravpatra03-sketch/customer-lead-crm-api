package com.crm.customerlead.controller;

import com.crm.customerlead.dto.ApiResponse;
import com.crm.customerlead.dto.NotesDTO;
import com.crm.customerlead.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "Notes management APIs")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping
    @Operation(summary = "Create note", description = "Create a new note")
    public ResponseEntity<ApiResponse<NotesDTO>> createNote(@Valid @RequestBody NotesDTO notesDTO) {
        NotesDTO createdNote = notesService.createNote(notesDTO);
        return new ResponseEntity<>(ApiResponse.success("Note created successfully", createdNote), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get note by ID", description = "Retrieve note details by ID")
    public ResponseEntity<ApiResponse<NotesDTO>> getNoteById(@PathVariable Long id) {
        NotesDTO note = notesService.getNoteById(id);
        return ResponseEntity.ok(ApiResponse.success(note));
    }

    @GetMapping("/lead/{leadId}")
    @Operation(summary = "Get notes by lead ID", description = "Retrieve all notes for a specific lead")
    public ResponseEntity<ApiResponse<List<NotesDTO>>> getNotesByLeadId(@PathVariable Long leadId) {
        List<NotesDTO> notes = notesService.getNotesByLeadId(leadId);
        return ResponseEntity.ok(ApiResponse.success(notes));
    }

    @GetMapping
    @Operation(summary = "Get all notes", description = "Retrieve all notes")
    public ResponseEntity<ApiResponse<List<NotesDTO>>> getAllNotes() {
        List<NotesDTO> notes = notesService.getAllNotes();
        return ResponseEntity.ok(ApiResponse.success(notes));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Update note details")
    public ResponseEntity<ApiResponse<NotesDTO>> updateNote(@PathVariable Long id, @Valid @RequestBody NotesDTO notesDTO) {
        NotesDTO updatedNote = notesService.updateNote(id, notesDTO);
        return ResponseEntity.ok(ApiResponse.success("Note updated successfully", updatedNote));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note", description = "Delete note by ID")
    public ResponseEntity<ApiResponse<Void>> deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
        return ResponseEntity.ok(ApiResponse.success("Note deleted successfully", null));
    }
}
