package com.crm.customerlead.service;

import com.crm.customerlead.dto.LeadTypeDTO;

import java.util.List;

public interface LeadTypeService {
    LeadTypeDTO createLeadType(LeadTypeDTO leadTypeDTO);
    LeadTypeDTO getLeadTypeById(Long id);
    List<LeadTypeDTO> getAllLeadTypes();
    LeadTypeDTO updateLeadType(Long id, LeadTypeDTO leadTypeDTO);
    void deleteLeadType(Long id);
}
