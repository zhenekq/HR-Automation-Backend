package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.builder.AttributeTypeBuilder;
import by.mifort.automation.hr.dev.shared.differences.AssertDifferencesUpdates;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataCreateException;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.type.AttributeTypes;
import by.mifort.automation.hr.dev.type.AttributeTypesRepository;
import by.mifort.automation.hr.dev.type.AttributeTypesService;
import by.mifort.automation.hr.dev.type.AttributeTypesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AttributeTypeServiceTest {

    @Mock
    private AttributeTypesRepository attributeTypesRepository;
    @Mock
    private AssertDifferencesUpdates updates;
    private AttributeTypesService attributeTypesService;

    @BeforeEach
    void setup() {
        attributeTypesService = new AttributeTypesServiceImpl(attributeTypesRepository, updates);
    }

    @Test
    @DisplayName("Get all exists attribute types")
    void checkGetAllAttributeTypes() {
        attributeTypesService.getAll();
        verify(attributeTypesRepository).findAll();
    }

    @Test
    @DisplayName("Check get exists attribute type by id")
    void checkGetByIdExistsAttributeType() {
        Integer attributeTypeId = anyInt();
        given(attributeTypesRepository.findById(attributeTypeId))
                .willReturn(Optional.of(new AttributeTypes(attributeTypeId)));
        attributeTypesService.getById(attributeTypeId);
        verify(attributeTypesRepository).findById(attributeTypeId);

    }

    @Test
    @DisplayName("Check get not exists attribute type by id and throw error")
    void checkGetByIdNotExistsAttributeTypeThrowException() {
        Integer attributeTypeId = anyInt();
        assertThatThrownBy(() -> attributeTypesService.getById(attributeTypeId))
                .isInstanceOf(DataNotFoundException.class);

    }

    @Test
    @DisplayName("Create unique Attribute Type")
    void createUniqueAttributeType() {
        AttributeTypes attributeTypes = new AttributeTypeBuilder().plain().build();

        given(attributeTypesRepository.existsByName(attributeTypes.getName()))
                .willReturn(false);

        attributeTypesService.create(attributeTypes);
        verify(attributeTypesRepository).save(attributeTypes);
    }

    @Test
    @DisplayName("Create exists Attribute Type and throw Exception")
    void createExistsAttributeTypeThrowException() {
        AttributeTypes attributeTypes = new AttributeTypeBuilder().plain().build();
        String attributeTypeName = attributeTypes.getName();
        given(attributeTypesRepository.existsByName(attributeTypeName))
                .willReturn(true);
        assertThatThrownBy(() -> attributeTypesService.create(attributeTypes))
                .isInstanceOf(DataCreateException.class);


    }

    @Test
    @Disabled
    void updateById() {
    }

    @Test
    @Disabled
    void archiveById() {
    }

}
