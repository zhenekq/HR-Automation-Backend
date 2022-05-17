package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttributeTypesConverter implements EntityConverter<AttributeTypes, AttributeTypesDto> {
    @Override
    public AttributeTypesDto convertToEntityDto(AttributeTypes attributeTypes) {
        AttributeTypesDto dto = new AttributeTypesDto();
        dto.setId(attributeTypes.getId());
        dto.setBasicType(attributeTypes.getBasicType());
        dto.setIdentifier(attributeTypes.getIdentifier());
        dto.setName(attributeTypes.getName());
        dto.setValidation(attributeTypes.getValidation());
        return dto;
    }

    @Override
    public List<AttributeTypesDto> convertToListEntityDto(List<AttributeTypes> attributeTypes) {
        return attributeTypes.stream()
                .map(this::convertToEntityDto)
                .toList();
    }

    @Override
    public AttributeTypes convertToEntity(AttributeTypesDto dto) {
        AttributeTypes types = new AttributeTypes();
        types.setId(dto.getId());
        types.setBasicType(dto.getBasicType());
        types.setIdentifier(dto.getIdentifier());
        types.setName(dto.getName());
        types.setValidation(dto.getValidation());
        return types;
    }

    @Override
    public List<AttributeTypes> convertToListEntity(List<AttributeTypesDto> attributeTypesDtos) {
        return attributeTypesDtos.stream()
                .map(this::convertToEntity)
                .toList();
    }
}
