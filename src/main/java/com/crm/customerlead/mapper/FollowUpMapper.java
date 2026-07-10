package com.crm.customerlead.mapper;

import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.FollowUp;
import org.springframework.stereotype.Component;

@Component
public class FollowUpMapper {

    public FollowUpDTO toDTO(FollowUp followUp) {
        if (followUp == null) {
            return null;
        }
        FollowUpDTO dto = new FollowUpDTO();
        dto.setId(followUp.getId());
        if (followUp.getLead() != null) {
            dto.setLeadId(followUp.getLead().getId());
        }
        dto.setDiscussion(followUp.getDiscussion());
        dto.setNextFollowUpDate(followUp.getNextFollowUpDate());
        dto.setStatus(followUp.getStatus());
        dto.setCreatedDate(followUp.getCreatedDate());
        return dto;
    }

    public FollowUp toEntity(FollowUpDTO dto) {
        if (dto == null) {
            return null;
        }
        FollowUp followUp = new FollowUp();
        followUp.setId(dto.getId());
        if (dto.getLeadId() != null) {
            CustomerLead lead = new CustomerLead();
            lead.setId(dto.getLeadId());
            followUp.setLead(lead);
        }
        followUp.setDiscussion(dto.getDiscussion());
        followUp.setNextFollowUpDate(dto.getNextFollowUpDate());
        followUp.setStatus(dto.getStatus());
        return followUp;
    }
}
