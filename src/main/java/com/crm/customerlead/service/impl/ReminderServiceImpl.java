package com.crm.customerlead.service.impl;

import com.crm.customerlead.dto.FollowUpDTO;
import com.crm.customerlead.entity.FollowUp;
import com.crm.customerlead.mapper.FollowUpMapper;
import com.crm.customerlead.repository.FollowUpRepository;
import com.crm.customerlead.service.ReminderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReminderServiceImpl implements ReminderService {

    private final FollowUpRepository followUpRepository;
    private final FollowUpMapper followUpMapper;

    public ReminderServiceImpl(FollowUpRepository followUpRepository, FollowUpMapper followUpMapper) {
        this.followUpRepository = followUpRepository;
        this.followUpMapper = followUpMapper;
    }

    @Override
    public List<FollowUpDTO> getTodayReminders() {
        LocalDate today = LocalDate.now();
        List<FollowUp> followUps = followUpRepository.findTodayFollowUps(today);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getUpcomingReminders() {
        LocalDate today = LocalDate.now();
        List<FollowUp> followUps = followUpRepository.findUpcomingFollowUps(today);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowUpDTO> getOverdueReminders() {
        LocalDate today = LocalDate.now();
        List<FollowUp> followUps = followUpRepository.findOverdueFollowUps(today);
        return followUps.stream()
                .map(followUpMapper::toDTO)
                .collect(Collectors.toList());
    }
}
