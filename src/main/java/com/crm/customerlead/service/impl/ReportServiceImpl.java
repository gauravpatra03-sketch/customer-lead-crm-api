package com.crm.customerlead.service.impl;

import com.crm.customerlead.dto.ReportSummaryDTO;
import com.crm.customerlead.entity.Status;
import com.crm.customerlead.repository.CustomerLeadRepository;
import com.crm.customerlead.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final CustomerLeadRepository customerLeadRepository;

    public ReportServiceImpl(CustomerLeadRepository customerLeadRepository) {
        this.customerLeadRepository = customerLeadRepository;
    }

    @Override
    public ReportSummaryDTO getReportSummary() {
        ReportSummaryDTO summary = new ReportSummaryDTO();
        
        // Get total leads
        Long totalLeads = customerLeadRepository.countTotalLeads();
        summary.setTotalLeads(totalLeads);
        
        // Get converted leads (CLOSED_WON)
        Long convertedLeads = customerLeadRepository.countByStatus(Status.CLOSED_WON);
        summary.setConvertedLeads(convertedLeads);
        
        // Calculate conversion rate
        Double conversionRate = totalLeads > 0 ? (convertedLeads.doubleValue() / totalLeads.doubleValue()) * 100 : 0.0;
        summary.setConversionRate(conversionRate);
        
        // Get leads by status
        Map<String, Long> leadsByStatus = new HashMap<>();
        leadsByStatus.put("NEW", customerLeadRepository.countByStatus(Status.NEW));
        leadsByStatus.put("CONTACTED", customerLeadRepository.countByStatus(Status.CONTACTED));
        leadsByStatus.put("INTERESTED", customerLeadRepository.countByStatus(Status.INTERESTED));
        leadsByStatus.put("FOLLOW_UP", customerLeadRepository.countByStatus(Status.FOLLOW_UP));
        leadsByStatus.put("CLOSED_WON", customerLeadRepository.countByStatus(Status.CLOSED_WON));
        leadsByStatus.put("CLOSED_LOST", customerLeadRepository.countByStatus(Status.CLOSED_LOST));
        leadsByStatus.put("NOT_INTERESTED", customerLeadRepository.countByStatus(Status.NOT_INTERESTED));
        summary.setLeadsByStatus(leadsByStatus);
        
        // Get leads by source
        List<com.crm.customerlead.entity.CustomerLead> allLeads = customerLeadRepository.findAll();
        Map<String, Long> leadsBySource = allLeads.stream()
                .filter(lead -> lead.getLeadSource() != null)
                .collect(Collectors.groupingBy(
                        lead -> lead.getLeadSource(),
                        Collectors.counting()
                ));
        summary.setLeadsBySource(leadsBySource);
        
        // Get leads by type
        Map<String, Long> leadsByType = allLeads.stream()
                .filter(lead -> lead.getLeadType() != null)
                .collect(Collectors.groupingBy(
                        lead -> lead.getLeadType().getName(),
                        Collectors.counting()
                ));
        summary.setLeadsByType(leadsByType);
        
        return summary;
    }
}
