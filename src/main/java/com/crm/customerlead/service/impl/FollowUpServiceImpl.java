package com.crm.customerlead.service.impl;

import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.FollowUp;
import com.crm.customerlead.exception.ResourceNotFoundException;
import com.crm.customerlead.mapper.FollowUpMapper;
import com.crm.customerlead.repository.CustomerLeadRepository;
import com.crm.customerlead.repository.FollowUpRepository;
import com.crm.customerlead.service.FollowUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowUpServiceImpl implements FollowUpService {

    private final FollowUpRepository followUpRepository;
    private final CustomerLeadRepository customerLeadRepository;
    private final FollowUpMapper followUpMapper;

    public FollowUpServiceImpl(FollowUpRepository followUpRepository, CustomerLeadRepository customerLeadRepository, FollowUpMapper followUpMapper) {
        this.followUpRepository = followUpRepository;
        this.customerLeadRepository = customerLeadRepository;
        this.followUpMapper = followUpMapper;
    }

    @Override
    public FollowUpDTO createFollowUp(FollowUpDTO followUpDTO) {
        CustomerLead lead = customerLeadRepository.findById(followUpDTO.getLeadId())
                .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", followUpDTO.getLeadId()));
        
        FollowUp followUp = followUpMapper.toEntity(followUpDTO);
        followUp.setLead(lead);
        FollowUp savedFollowUp = followUpRepository.save(followUp);
        
        // Update lead's next follow-up date
        lead.setNextFollowUpDate(followUpDTO.getNextFollowUpDate());
        customerLeadRepository.save(lead);
        
        return followUpMapper.toDTO(savedFollowUp);
    }

    @Override
    public FollowUpDTO getFollowUpById(Long id) {
        FollowUp followUp = followUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FollowUp", id));
        return followUpMapper.toDTO(followUp);
    }

    @Override
    public List<FollowUpDTO> getFollowUpsByLeadId(Long leadId) {
        List<FollowUp> followUps = followUpRepository.findByLeadId(leadId);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getAllFollowUps() {
        List<FollowUp> followUps = followUpRepository.findAll();
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FollowUpDTO updateFollowUp(Long id, FollowUpDTO followUpDTO) {
        FollowUp existingFollowUp = followUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FollowUp", id));
        
        if (followUpDTO.getLeadId() != null) {
            CustomerLead lead = customerLeadRepository.findById(followUpDTO.getLeadId())
                    .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", followUpDTO.getLeadId()));
            existingFollowUp.setLead(lead);
        }
        
        existingFollowUp.setDiscussion(followUpDTO.getDiscussion());
        existingFollowUp.setNextFollowUpDate(followUpDTO.getNextFollowUpDate());
        existingFollowUp.setStatus(followUpDTO.getStatus());
        
        FollowUp updatedFollowUp = followUpRepository.save(existingFollowUp);
        return followUpMapper.toDTO(updatedFollowUp);
    }

    @Override
    public void deleteFollowUp(Long id) {
        FollowUp followUp = followUpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FollowUp", id));
        followUpRepository.delete(followUp);
    }

    @Override
    public List<FollowUpDTO> getTodayFollowUps() {
        LocalDate today = LocalDate.now();
        List<FollowUp> followUps = followUpRepository.findTodayFollowUps(today);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getOverdueFollowUps() {
        LocalDate today = LocalDate.now();
        List<FollowUp> followUps = followUpRepository.findOverdueFollowUps(today);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getTomorrowFollowUps() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<FollowUp> followUps = followUpRepository.findTomorrowFollowUps(tomorrow);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getRecentFollowUps() {
        List<FollowUp> followUps = followUpRepository.findTop10ByOrderByCreatedDateDesc();
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }
}
