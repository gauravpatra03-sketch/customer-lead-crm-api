package com.crm.customerlead.service.impl;

import com.crm.customerlead.dto.LeadTypeDTO;
import com.crm.customerlead.entity.LeadType;
import com.crm.customerlead.exception.BadRequestException;
import com.crm.customerlead.exception.ResourceNotFoundException;
import com.crm.customerlead.mapper.LeadTypeMapper;
import com.crm.customerlead.repository.LeadTypeRepository;
import com.crm.customerlead.service.LeadTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeadTypeServiceImpl implements LeadTypeService {

    private final LeadTypeRepository leadTypeRepository;
    private final LeadTypeMapper leadTypeMapper;

    public LeadTypeServiceImpl(LeadTypeRepository leadTypeRepository, LeadTypeMapper leadTypeMapper) {
        this.leadTypeRepository = leadTypeRepository;
        this.leadTypeMapper = leadTypeMapper;
    }

    @Override
    public LeadTypeDTO createLeadType(LeadTypeDTO leadTypeDTO) {
        if (leadTypeRepository.existsByName(leadTypeDTO.getName())) {
            throw new BadRequestException("Lead type with this name already exists");
        }
        LeadType leadType = leadTypeMapper.toEntity(leadTypeDTO);
        LeadType savedLeadType = leadTypeRepository.save(leadType);
        return leadTypeMapper.toDTO(savedLeadType);
    }

    @Override
    public LeadTypeDTO getLeadTypeById(Long id) {
        LeadType leadType = leadTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeadType", id));
        return leadTypeMapper.toDTO(leadType);
    }

    @Override
    public List<LeadTypeDTO> getAllLeadTypes() {
        List<LeadType> leadTypes = leadTypeRepository.findAll();
        return leadTypes.stream()
                .map(leadTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LeadTypeDTO updateLeadType(Long id, LeadTypeDTO leadTypeDTO) {
        LeadType existingLeadType = leadTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeadType", id));
        
        if (!existingLeadType.getName().equals(leadTypeDTO.getName()) && 
            leadTypeRepository.existsByName(leadTypeDTO.getName())) {
            throw new BadRequestException("Lead type with this name already exists");
        }
        
        existingLeadType.setName(leadTypeDTO.getName());
        existingLeadType.setDescription(leadTypeDTO.getDescription());
        
        LeadType updatedLeadType = leadTypeRepository.save(existingLeadType);
        return leadTypeMapper.toDTO(updatedLeadType);
    }

    @Override
    public void deleteLeadType(Long id) {
        LeadType leadType = leadTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LeadType", id));
        leadTypeRepository.delete(leadType);
    }
}
