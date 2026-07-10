package com.crm.customerlead.dto;

public class DashboardStatsDTO {
    private Long totalLeads;
    private Long todayFollowups;
    private Long pendingFollowups;
    private Long hotCustomers;
    private Long closedWon;
    private Long closedLost;
    private Long todayLeads;
    private Long monthlyLeads;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(Long totalLeads, Long todayFollowups, Long pendingFollowups, Long hotCustomers, Long closedWon, Long closedLost, Long todayLeads, Long monthlyLeads) {
        this.totalLeads = totalLeads;
        this.todayFollowups = todayFollowups;
        this.pendingFollowups = pendingFollowups;
        this.hotCustomers = hotCustomers;
        this.closedWon = closedWon;
        this.closedLost = closedLost;
        this.todayLeads = todayLeads;
        this.monthlyLeads = monthlyLeads;
    }

    public Long getTotalLeads() {
        return totalLeads;
    }

    public void setTotalLeads(Long totalLeads) {
        this.totalLeads = totalLeads;
    }

    public Long getTodayFollowups() {
        return todayFollowups;
    }

    public void setTodayFollowups(Long todayFollowups) {
        this.todayFollowups = todayFollowups;
    }

    public Long getPendingFollowups() {
        return pendingFollowups;
    }

    public void setPendingFollowups(Long pendingFollowups) {
        this.pendingFollowups = pendingFollowups;
    }

    public Long getHotCustomers() {
        return hotCustomers;
    }

    public void setHotCustomers(Long hotCustomers) {
        this.hotCustomers = hotCustomers;
    }

    public Long getClosedWon() {
        return closedWon;
    }

    public void setClosedWon(Long closedWon) {
        this.closedWon = closedWon;
    }

    public Long getClosedLost() {
        return closedLost;
    }

    public void setClosedLost(Long closedLost) {
        this.closedLost = closedLost;
    }

    public Long getTodayLeads() {
        return todayLeads;
    }

    public void setTodayLeads(Long todayLeads) {
        this.todayLeads = todayLeads;
    }

    public Long getMonthlyLeads() {
        return monthlyLeads;
    }

    public void setMonthlyLeads(Long monthlyLeads) {
        this.monthlyLeads = monthlyLeads;
    }
}
