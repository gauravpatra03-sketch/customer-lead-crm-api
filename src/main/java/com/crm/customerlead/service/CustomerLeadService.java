package com.crm.customerlead.service;

import com.crm.customerlead.dto.CustomerLeadDTO;
import com.crm.customerlead.dto.DashboardStatsDTO;
import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.dto.NotesDTO;
import com.crm.customerlead.dto.SearchRequest;

import java.util.List;

public interface CustomerLeadService {
    CustomerLeadDTO createLead(CustomerLeadDTO leadDTO);
    CustomerLeadDTO getLeadById(Long id);
    List<CustomerLeadDTO> getAllLeads();
    CustomerLeadDTO updateLead(Long id, CustomerLeadDTO leadDTO);
    void deleteLead(Long id);
    List<CustomerLeadDTO> searchLeads(SearchRequest searchRequest);
    DashboardStatsDTO getDashboardStats();
    List<CustomerLeadDTO> getRecentLeads();
    List<FollowUpDTO> getFollowUpsByLeadId(Long leadId);
    List<NotesDTO> getNotesByLeadId(Long leadId);
}
