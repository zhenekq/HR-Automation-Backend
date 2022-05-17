package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.CandidateAttributesDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import liquibase.pro.packaged.A;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateAttributesConverter implements EntityConverter<CandidateAttributes, CandidateAttributesDto> {

    @Override
    public CandidateAttributesDto convertToEntityDto(CandidateAttributes attributes) {
        CandidateAttributesDto dto = new CandidateAttributesDto();
        dto.setId(attributes.getId());
        dto.setValue(attributes.getValue());
        dto.setValueSource(attributes.getValueSource());
        dto.setName(attributes.getAttributeTypes().getName());
        dto.setArchived(attributes.getIsArchived());
        return dto;
    }

    @Override
    public List<CandidateAttributesDto> convertToListEntityDto(List<CandidateAttributes> attributes) {
        return attributes.stream()
                .map(this::convertToEntityDto)
                .toList();
    }

    @Override
    public CandidateAttributes convertToEntity(CandidateAttributesDto candidateAttributesDto) {
        return new CandidateAttributes(candidateAttributesDto.getId(),
                candidateAttributesDto.getValue(), candidateAttributesDto.getValueSource(), new Candidate(), new AttributeTypes());
    }

    @Override
    public List<CandidateAttributes> convertToListEntity(List<CandidateAttributesDto> candidateAttributesDtos) {
        return candidateAttributesDtos
                .stream()
                .map(this::convertToEntity)
                .toList();
    }
}
