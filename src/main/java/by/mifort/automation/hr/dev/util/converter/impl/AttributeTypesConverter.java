package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttributeTypesConverter implements EntityConverter<AttributeTypes, AttributeTypesDto> {

    private static final String DOB_LABEL = "Date of birth";
    private static final String DOB_FACEBOOK_ID = "Facebook";
    private static final String DOB_GITHUB_ID = "Github";
    private static final String DOB_PASSPORT= "Passport number";

    @Override
    public AttributeTypesDto convertToEntityDto(AttributeTypes attributeTypes) {
        AttributeTypesDto dto = new AttributeTypesDto();
        dto.setId(attributeTypes.getId());
        dto.setBasicType(attributeTypes.getBasicType());
        dto.setIsIdentifier(attributeTypes.getIdentifier());
        dto.setName(attributeTypes.getName());
        dto.setLabel(firstUpperCase(attributeTypes.getName()));
        switch (attributeTypes.getName()){
            case "date_of_birth":
                dto.setLabel(DOB_LABEL);
                break;
            case "facebookid":
                dto.setLabel(DOB_FACEBOOK_ID);
                break;
            case "githubid":
                dto.setLabel(DOB_GITHUB_ID);
                break;
            case "passport_number":
                dto.setLabel(DOB_PASSPORT);
                break;
            default:
                break;
        }
        dto.setValidation(attributeTypes.getValidation());
        return dto;
    }

    @Override
    public List<AttributeTypesDto> convertToListEntityDto(List<AttributeTypes> attributeTypes) {
        return attributeTypes.stream()
                .map(this::convertToEntityDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttributeTypes convertToEntity(AttributeTypesDto dto) {
        AttributeTypes types = new AttributeTypes();
        types.setId(dto.getId());
        types.setBasicType(dto.getBasicType());
        types.setIdentifier(dto.getIsIdentifier());
        types.setName(dto.getName());
        types.setValidation(dto.getValidation());
        return types;
    }

    @Override
    public List<AttributeTypes> convertToListEntity(List<AttributeTypesDto> attributeTypesDtos) {
        return attributeTypesDtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    private String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
