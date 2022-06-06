package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.CandidateAttributesDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import liquibase.pro.packaged.A;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateAttributesConverter implements EntityConverter<CandidateAttributes, CandidateAttributesDto> {

    private static final String DOB_LABEL = "Date of birth";
    private static final String DOB_FACEBOOK_ID = "Facebook";
    private static final String DOB_GITHUB_ID = "Github";
    private static final String DOB_PASSPORT= "Passport number";

    @Override
    public CandidateAttributesDto convertToEntityDto(CandidateAttributes attributes) {
        CandidateAttributesDto dto = new CandidateAttributesDto();
        dto.setId(attributes.getId());
        dto.setValue(attributes.getValue());
        dto.setValueSource(attributes.getValueSource());
        dto.setName(firstUpperCase(attributes.getAttributeTypes().getName()));
        if(attributes.getAttributeTypes().getName() != null) {
            switch (attributes.getAttributeTypes().getName()) {
                case "date_of_birth" -> dto.setName(DOB_LABEL);
                case "facebookid" -> dto.setName(DOB_FACEBOOK_ID);
                case "githubid" -> dto.setName(DOB_GITHUB_ID);
                case "passport_number" -> dto.setName(DOB_PASSPORT);
                default -> dto.setName(firstUpperCase(attributes.getAttributeTypes().getName()));
            }
        }
        dto.setArchived(attributes.getIsArchived());
        return dto;
    }

    @Override
    public List<CandidateAttributesDto> convertToListEntityDto(List<CandidateAttributes> attributes) {
        return attributes.stream()
                .map(this::convertToEntityDto)
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());
    }

    private String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
