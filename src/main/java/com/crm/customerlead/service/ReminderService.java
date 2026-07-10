package com.crm.customerlead.service;

import com.crm.customerlead.dto.FollowUpDTO;

import java.util.List;

public interface ReminderService {
    List<FollowUpDTO> getTodayReminders();
    List<FollowUpDTO> getUpcomingReminders();
    List<FollowUpDTO> getOverdueReminders();
}
