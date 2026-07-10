package com.crm.customerlead.service;

import com.crm.customerlead.dto.FollowUpDTO;

import java.util.List;

public interface FollowUpService {
    FollowUpDTO createFollowUp(FollowUpDTO followUpDTO);
    FollowUpDTO getFollowUpById(Long id);
    List<FollowUpDTO> getFollowUpsByLeadId(Long leadId);
    List<FollowUpDTO> getAllFollowUps();
    FollowUpDTO updateFollowUp(Long id, FollowUpDTO followUpDTO);
    void deleteFollowUp(Long id);
    List<FollowUpDTO> getTodayFollowUps();
    List<FollowUpDTO> getOverdueFollowUps();
    List<FollowUpDTO> getTomorrowFollowUps();
    List<FollowUpDTO> getRecentFollowUps();
}
