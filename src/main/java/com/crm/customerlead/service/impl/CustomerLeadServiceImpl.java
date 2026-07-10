package com.crm.customerlead.service.impl;

import com.crm.customerlead.dto.CustomerLeadDTO;
import com.crm.customerlead.dto.DashboardStatsDTO;
import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.dto.NotesDTO;
import com.crm.customerlead.dto.SearchRequest;
import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.LeadType;
import com.crm.customerlead.entity.Priority;
import com.crm.customerlead.entity.Status;
import com.crm.customerlead.exception.ResourceNotFoundException;
import com.crm.customerlead.mapper.CustomerLeadMapper;
import com.crm.customerlead.mapper.FollowUpMapper;
import com.crm.customerlead.mapper.NotesMapper;
import com.crm.customerlead.repository.CustomerLeadRepository;
import com.crm.customerlead.repository.FollowUpRepository;
import com.crm.customerlead.repository.LeadTypeRepository;
import com.crm.customerlead.repository.NotesRepository;
import com.crm.customerlead.service.CustomerLeadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerLeadServiceImpl implements CustomerLeadService {

    private final CustomerLeadRepository customerLeadRepository;
    private final LeadTypeRepository leadTypeRepository;
    private final FollowUpRepository followUpRepository;
    private final NotesRepository notesRepository;
    private final CustomerLeadMapper customerLeadMapper;
    private final FollowUpMapper followUpMapper;
    private final NotesMapper notesMapper;

    public CustomerLeadServiceImpl(CustomerLeadRepository customerLeadRepository, LeadTypeRepository leadTypeRepository, 
                                   FollowUpRepository followUpRepository, NotesRepository notesRepository,
                                   CustomerLeadMapper customerLeadMapper, FollowUpMapper followUpMapper, NotesMapper notesMapper) {
        this.customerLeadRepository = customerLeadRepository;
        this.leadTypeRepository = leadTypeRepository;
        this.followUpRepository = followUpRepository;
        this.notesRepository = notesRepository;
        this.customerLeadMapper = customerLeadMapper;
        this.followUpMapper = followUpMapper;
        this.notesMapper = notesMapper;
    }

    @Override
    public CustomerLeadDTO createLead(CustomerLeadDTO leadDTO) {
        LeadType leadType = leadTypeRepository.findById(leadDTO.getLeadTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("LeadType", leadDTO.getLeadTypeId()));
        
        CustomerLead lead = customerLeadMapper.toEntity(leadDTO);
        lead.setLeadType(leadType);
        CustomerLead savedLead = customerLeadRepository.save(lead);
        return customerLeadMapper.toDTO(savedLead);
    }

    @Override
    public CustomerLeadDTO getLeadById(Long id) {
        CustomerLead lead = customerLeadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", id));
        return customerLeadMapper.toDTO(lead);
    }

    @Override
    public List<CustomerLeadDTO> getAllLeads() {
        List<CustomerLead> leads = customerLeadRepository.findAll();
        return leads.stream()
                .map(customerLeadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerLeadDTO updateLead(Long id, CustomerLeadDTO leadDTO) {
        CustomerLead existingLead = customerLeadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", id));
        
        if (leadDTO.getLeadTypeId() != null) {
            LeadType leadType = leadTypeRepository.findById(leadDTO.getLeadTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("LeadType", leadDTO.getLeadTypeId()));
            existingLead.setLeadType(leadType);
        }
        
        existingLead.setCustomerName(leadDTO.getCustomerName());
        existingLead.setMobile(leadDTO.getMobile());
        existingLead.setAlternateMobile(leadDTO.getAlternateMobile());
        existingLead.setEmail(leadDTO.getEmail());
        existingLead.setCity(leadDTO.getCity());
        existingLead.setAddress(leadDTO.getAddress());
        existingLead.setRequirement(leadDTO.getRequirement());
        existingLead.setLeadSource(leadDTO.getLeadSource());
        existingLead.setAssignedExecutive(leadDTO.getAssignedExecutive());
        existingLead.setDiscussionDetails(leadDTO.getDiscussionDetails());
        existingLead.setVisitDate(leadDTO.getVisitDate());
        existingLead.setNextFollowUpDate(leadDTO.getNextFollowUpDate());
        existingLead.setStatus(leadDTO.getStatus());
        existingLead.setPriority(leadDTO.getPriority());
        
        CustomerLead updatedLead = customerLeadRepository.save(existingLead);
        return customerLeadMapper.toDTO(updatedLead);
    }

    @Override
    public void deleteLead(Long id) {
        CustomerLead lead = customerLeadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerLead", id));
        customerLeadRepository.delete(lead);
    }

    @Override
    public List<CustomerLeadDTO> searchLeads(SearchRequest searchRequest) {
        List<CustomerLead> leads = customerLeadRepository.searchLeads(
                searchRequest.getCustomerName(),
                searchRequest.getMobile(),
                searchRequest.getCity(),
                searchRequest.getLeadTypeId(),
                searchRequest.getStatus(),
                searchRequest.getPriority(),
                searchRequest.getExecutive(),
                searchRequest.getStartDate(),
                searchRequest.getEndDate()
        );
        return leads.stream()
                .map(customerLeadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DashboardStatsDTO getDashboardStats() {
        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.now();
        
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setTotalLeads(customerLeadRepository.countTotalLeads());
        stats.setTodayFollowups(customerLeadRepository.countByStatus(Status.FOLLOW_UP));
        stats.setPendingFollowups(customerLeadRepository.countByStatus(Status.NEW) + 
                                  customerLeadRepository.countByStatus(Status.CONTACTED) +
                                  customerLeadRepository.countByStatus(Status.INTERESTED));
        stats.setHotCustomers(customerLeadRepository.countByPriority(Priority.HOT));
        stats.setClosedWon(customerLeadRepository.countByStatus(Status.CLOSED_WON));
        stats.setClosedLost(customerLeadRepository.countByStatus(Status.CLOSED_LOST));
        stats.setTodayLeads(customerLeadRepository.countByCreatedDate(today));
        stats.setMonthlyLeads(customerLeadRepository.countByMonth(currentMonth.getYear(), currentMonth.getMonthValue()));
        
        return stats;
    }

    @Override
    public List<CustomerLeadDTO> getRecentLeads() {
        List<CustomerLead> leads = customerLeadRepository.findTop10ByOrderByCreatedDateDesc();
        return leads.stream()
                .map(customerLeadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getFollowUpsByLeadId(Long leadId) {
        List<com.crm.customerlead.entity.FollowUp> followUps = followUpRepository.findByLeadId(leadId);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotesDTO> getNotesByLeadId(Long leadId) {
        List<com.crm.customerlead.entity.Notes> notes = notesRepository.findByLeadId(leadId);
        return notes.stream()
                .map(notesMapper::toDTO)
                .collect(Collectors.toList());
    }
}
