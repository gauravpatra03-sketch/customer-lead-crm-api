package com.crm.customerlead.service.impl;

import com.crm.customerlead.dto.NotesDTO;
import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.Notes;
import com.crm.customerlead.exception.ResourceNotFoundException;
import com.crm.customerlead.mapper.NotesMapper;
import com.crm.customerlead.repository.CustomerLeadRepository;
import com.crm.customerlead.repository.NotesRepository;
import com.crm.customerlead.service.NotesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;
    private final CustomerLeadRepository customerLeadRepository;
    private final NotesMapper notesMapper;

    public NotesServiceImpl(NotesRepository notesRepository, CustomerLeadRepository customerLeadRepository, NotesMapper notesMapper) {
        this.notesRepository = notesRepository;
        this.customerLeadRepository = customerLeadRepository;
        this.notesMapper = notesMapper;
    }

    @Override
    public NotesDTO createNote(NotesDTO notesDTO) {
        CustomerLead lead = customerLeadRepository.findById(notesDTO.getLeadId())
                .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", notesDTO.getLeadId()));
        
        Notes notes = notesMapper.toEntity(notesDTO);
        notes.setLead(lead);
        Notes savedNotes = notesRepository.save(notes);
        return notesMapper.toDTO(savedNotes);
    }

    @Override
    public NotesDTO getNoteById(Long id) {
        Notes notes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes", id));
        return notesMapper.toDTO(notes);
    }

    @Override
    public List<NotesDTO> getNotesByLeadId(Long leadId) {
        List<Notes> notes = notesRepository.findByLeadId(leadId);
        return notes.stream()
                .map(notesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotesDTO> getAllNotes() {
        List<Notes> notes = notesRepository.findAll();
        return notes.stream()
                .map(notesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotesDTO updateNote(Long id, NotesDTO notesDTO) {
        Notes existingNotes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes", id));
        
        if (notesDTO.getLeadId() != null) {
            CustomerLead lead = customerLeadRepository.findById(notesDTO.getLeadId())
                    .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", notesDTO.getLeadId()));
            existingNotes.setLead(lead);
        }
        
        existingNotes.setNote(notesDTO.getNote());
        
        Notes updatedNotes = notesRepository.save(existingNotes);
        return notesMapper.toDTO(updatedNotes);
    }

    @Override
    public void deleteNote(Long id) {
        Notes notes = notesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes", id));
        notesRepository.delete(notes);
    }
}
