package com.crm.customerlead.mapper;

import com.crm.customerlead.dto.LeadTypeDTO;
import com.crm.customerlead.entity.LeadType;
import org.springframework.stereotype.Component;

@Component
public class LeadTypeMapper {

    public LeadTypeDTO toDTO(LeadType leadType) {
        if (leadType == null) {
            return null;
        }
        return new LeadTypeDTO(
                leadType.getId(),
                leadType.getName(),
                leadType.getDescription(),
                leadType.getCreatedDate()
        );
    }

    public LeadType toEntity(LeadTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        LeadType leadType = new LeadType();
        leadType.setId(dto.getId());
        leadType.setName(dto.getName());
        leadType.setDescription(dto.getDescription());
        return leadType;
    }
}
