package com.crm.customerlead.dto;

import java.util.Map;

public class ReportSummaryDTO {
    private Map<String, Long> leadsByStatus;
    private Map<String, Long> leadsBySource;
    private Map<String, Long> leadsByType;
    private Double conversionRate;
    private Long totalLeads;
    private Long convertedLeads;

    public ReportSummaryDTO() {
    }

    public ReportSummaryDTO(Map<String, Long> leadsByStatus, Map<String, Long> leadsBySource, 
                           Map<String, Long> leadsByType, Double conversionRate, 
                           Long totalLeads, Long convertedLeads) {
        this.leadsByStatus = leadsByStatus;
        this.leadsBySource = leadsBySource;
        this.leadsByType = leadsByType;
        this.conversionRate = conversionRate;
        this.totalLeads = totalLeads;
        this.convertedLeads = convertedLeads;
    }

    public Map<String, Long> getLeadsByStatus() {
        return leadsByStatus;
    }

    public void setLeadsByStatus(Map<String, Long> leadsByStatus) {
        this.leadsByStatus = leadsByStatus;
    }

    public Map<String, Long> getLeadsBySource() {
        return leadsBySource;
    }

    public void setLeadsBySource(Map<String, Long> leadsBySource) {
        this.leadsBySource = leadsBySource;
    }

    public Map<String, Long> getLeadsByType() {
        return leadsByType;
    }

    public void setLeadsByType(Map<String, Long> leadsByType) {
        this.leadsByType = leadsByType;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Long getTotalLeads() {
        return totalLeads;
    }

    public void setTotalLeads(Long totalLeads) {
        this.totalLeads = totalLeads;
    }

    public Long getConvertedLeads() {
        return convertedLeads;
    }

    public void setConvertedLeads(Long convertedLeads) {
        this.convertedLeads = convertedLeads;
    }
}
