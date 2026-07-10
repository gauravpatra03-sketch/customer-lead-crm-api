package com.crm.customerlead.mapper;

import com.crm.customerlead.dto.CustomerLeadDTO;
import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.LeadType;
import org.springframework.stereotype.Component;

@Component
public class CustomerLeadMapper {

    public CustomerLeadDTO toDTO(CustomerLead lead) {
        if (lead == null) {
            return null;
        }
        CustomerLeadDTO dto = new CustomerLeadDTO();
        dto.setId(lead.getId());
        dto.setCustomerName(lead.getCustomerName());
        dto.setMobile(lead.getMobile());
        dto.setAlternateMobile(lead.getAlternateMobile());
        dto.setEmail(lead.getEmail());
        if (lead.getLeadType() != null) {
            dto.setLeadTypeId(lead.getLeadType().getId());
            dto.setLeadTypeName(lead.getLeadType().getName());
        }
        dto.setCity(lead.getCity());
        dto.setAddress(lead.getAddress());
        dto.setRequirement(lead.getRequirement());
        dto.setLeadSource(lead.getLeadSource());
        dto.setAssignedExecutive(lead.getAssignedExecutive());
        dto.setDiscussionDetails(lead.getDiscussionDetails());
        dto.setVisitDate(lead.getVisitDate());
        dto.setNextFollowUpDate(lead.getNextFollowUpDate());
        dto.setStatus(lead.getStatus());
        dto.setPriority(lead.getPriority());
        dto.setCreatedDate(lead.getCreatedDate());
        return dto;
    }

    public CustomerLead toEntity(CustomerLeadDTO dto) {
        if (dto == null) {
            return null;
        }
        CustomerLead lead = new CustomerLead();
        lead.setId(dto.getId());
        lead.setCustomerName(dto.getCustomerName());
        lead.setMobile(dto.getMobile());
        lead.setAlternateMobile(dto.getAlternateMobile());
        lead.setEmail(dto.getEmail());
        if (dto.getLeadTypeId() != null) {
            LeadType leadType = new LeadType();
            leadType.setId(dto.getLeadTypeId());
            lead.setLeadType(leadType);
        }
        lead.setCity(dto.getCity());
        lead.setAddress(dto.getAddress());
        lead.setRequirement(dto.getRequirement());
        lead.setLeadSource(dto.getLeadSource());
        lead.setAssignedExecutive(dto.getAssignedExecutive());
        lead.setDiscussionDetails(dto.getDiscussionDetails());
        lead.setVisitDate(dto.getVisitDate());
        lead.setNextFollowUpDate(dto.getNextFollowUpDate());
        lead.setStatus(dto.getStatus());
        lead.setPriority(dto.getPriority());
        return lead;
    }
}
