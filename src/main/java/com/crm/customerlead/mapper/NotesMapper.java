package com.crm.customerlead.mapper;

import com.crm.customerlead.dto.NotesDTO;
import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.Notes;
import org.springframework.stereotype.Component;

@Component
public class NotesMapper {

    public NotesDTO toDTO(Notes notes) {
        if (notes == null) {
            return null;
        }
        NotesDTO dto = new NotesDTO();
        dto.setId(notes.getId());
        if (notes.getLead() != null) {
            dto.setLeadId(notes.getLead().getId());
        }
        dto.setNote(notes.getNote());
        dto.setCreatedDate(notes.getCreatedDate());
        return dto;
    }

    public Notes toEntity(NotesDTO dto) {
        if (dto == null) {
            return null;
        }
        Notes notes = new Notes();
        notes.setId(dto.getId());
        if (dto.getLeadId() != null) {
            CustomerLead lead = new CustomerLead();
            lead.setId(dto.getLeadId());
            notes.setLead(lead);
        }
        notes.setNote(dto.getNote());
        return notes;
    }
}
