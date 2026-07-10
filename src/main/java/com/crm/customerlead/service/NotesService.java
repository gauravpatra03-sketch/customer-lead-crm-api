package com.crm.customerlead.service;

import com.crm.customerlead.dto.NotesDTO;

import java.util.List;

public interface NotesService {
    NotesDTO createNote(NotesDTO notesDTO);
    NotesDTO getNoteById(Long id);
    List<NotesDTO> getNotesByLeadId(Long leadId);
    List<NotesDTO> getAllNotes();
    NotesDTO updateNote(Long id, NotesDTO notesDTO);
    void deleteNote(Long id);
}
