package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.db.H2Database;
import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.CandidateAttributesDto;
import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
    @ExtendWith(SpringExtension.class)
    @TestPropertySource(locations = "classpath:application-test.properties")
class CandidateAttributeControllerTest {

    private final CandidateAttributeController controller;
    private final CandidateController candidateController;
    private final AttributeTypesController typesController;
    private final EntityConverter<CandidateAttributes, CandidateAttributesDto> converter;
    private final EntityConverter<Candidate, CandidateDto> candidateConverter;

    private final H2Database h2Database = H2Database.getInstance();
    private List<CandidateAttributes> attributesList;

    private final EntityConverter<AttributeTypes, AttributeTypesDto> attributeTypesConverter;

    static int flag = 0;

    @Autowired
    CandidateAttributeControllerTest(CandidateAttributeController controller, CandidateController candidateController, AttributeTypesController typesController, EntityConverter<CandidateAttributes, CandidateAttributesDto> converter, EntityConverter<Candidate, CandidateDto> candidateConverter, EntityConverter<AttributeTypes, AttributeTypesDto> attributeTypesConverter) {
        this.controller = controller;
        this.candidateController = candidateController;
        this.typesController = typesController;
        this.converter = converter;
        this.candidateConverter = candidateConverter;
        this.attributeTypesConverter = attributeTypesConverter;
    }

    @BeforeEach
    void init(){
        if(flag == 0) {
            List<AttributeTypesDto> types = attributeTypesConverter.convertToListEntityDto(H2Database.getInstance().initializeAttributeTypes());
            types.forEach(typesController::create);
            List<CandidateDto> candidateDto = candidateConverter.convertToListEntityDto(h2Database.initializeCandidates());
            candidateDto.forEach(candidateController::create);
            attributesList = h2Database.initializeCandidateAttributes();
            List<CandidateAttributes> yauheni_attributes = attributesList.subList(0, 14);
            controller.createByCandidateId("yauheni_vozny", yauheni_attributes);
            List<CandidateAttributes> uliana_attributes = h2Database.initializeCandidateAttributes().subList(15, 29);
            controller.createByCandidateId("uliana_fomina", uliana_attributes);

            List<CandidateAttributes> artem_attributes = h2Database.initializeCandidateAttributes().subList(30, 44);
            controller.createByCandidateId("artem_skrebets", artem_attributes);

            List<CandidateAttributes> yauheni_clone_attributes = h2Database.initializeCandidateAttributes().subList(45, 59);
            controller.createByCandidateId("yauheni_vozny_clone", yauheni_clone_attributes);

            List<CandidateAttributes> vladimir_zelmanchuk = h2Database.initializeCandidateAttributes().subList(60, 74);
            controller.createByCandidateId("vladimir_zelmanchuk", vladimir_zelmanchuk);
            flag++;
        }
        attributesList = h2Database.initializeCandidateAttributes();
    }


    @Test
    @DisplayName("Check candidate attributes by identifier exists")
    void checkCandidateAttributesById_ExistsCandidate() {
        List<CandidateAttributes> attributes = attributesList.subList(0, 14);
        List<CandidateAttributesDto> actualAttributes = controller.getByCandidateId("yauheni_vozny");
        actualAttributes.forEach(name -> name.setName(null));
        List<CandidateAttributesDto> expectedAttributes = converter.convertToListEntityDto(attributes);
        for(int i=0;i<actualAttributes.size() - 1;i++){
            assertEquals(actualAttributes.get(i).getValue(), expectedAttributes.get(i).getValue());
        }
    }

    @Test
    @DisplayName("Check candidate attributes by identifier exists not equals")
    void checkCandidateAttributesById_ExistsCandidate_Fake() {
        List<CandidateAttributes> attributes = List.of(
                attributesList.get(0),
                attributesList.get(15)
        );
        List<CandidateAttributesDto> actualAttributes = controller.getByCandidateId("yauheni_vozny");
        List<CandidateAttributesDto> expectedAttributes = converter.convertToListEntityDto(attributes);

        assertNotEquals(actualAttributes, expectedAttributes);
    }

    @Test
    @DisplayName("Check candidate attributes by identifier exists not equals")
    void checkCandidateAttributesById_NotExistsCandidate() {
        String fakeIdentifier = RandomString.make();
        assertThrows(EntityNotFoundException.class,
                () -> controller.getByCandidateId(fakeIdentifier),
                "Candidate not found!");
    }

    @Test
    @DisplayName("Check candidate attributes by identifier exists but attribute not")
    void checkCandidateAttributesById_CandidateExists_AttributesNot(){
        String candidateId = "empty_candidate";
        List<CandidateAttributesDto> actualAttributes = controller.getByCandidateId(candidateId);
        assertEquals("", actualAttributes.get(0).getValue());
    }

    @Test
    @DisplayName("Create candidate attribute by identifier not exists and exists type")
    void checkCandidateAttributeCreateById_CandidateNotExists_AttributeTypeExists(){
        String existCandidateId = RandomString.make();
        Integer attributeType = 2;
        List<CandidateAttributes> createAttribute = new ArrayList<>();
        CandidateAttributes attributes = h2Database.getCandidateAttributesWithRandomValues(existCandidateId, attributeType);
        createAttribute.add(attributes);
        assertThrows(EntityNotFoundException.class,
                () -> controller.createByCandidateId(existCandidateId, createAttribute),
                "Candidate not found!");
    }

    @Test
    @DisplayName("Create candidate attribute by identifier not exists and not exists type")
    void checkCandidateAttributeCreateById_CandidateNotExists_AttributeTypeNotExists(){
        String existCandidateId = RandomString.make();
        Integer attributeType = Integer.MAX_VALUE;
        List<CandidateAttributes> createAttribute = new ArrayList<>();
        createAttribute.add(h2Database.getCandidateAttributesWithRandomValues(existCandidateId, attributeType));
        assertThrows(EntityNotFoundException.class,
                () -> controller.createByCandidateId(existCandidateId, createAttribute),
                "Attribute type not found!");
    }

    @Test
    @DisplayName("Create candidate attribute by identifier not exists and not exists type")
    void checkCandidateAttributeCreateById_CandidateNullableFields_AttributeTypeExists(){
        String existCandidateId = "yauheni_vozny";
        Integer attributeType = 1;
        List<CandidateAttributes> createAttribute = new ArrayList<>();
        CandidateAttributes attributes = h2Database.getCandidateAttributesWithRandomValues(existCandidateId, attributeType);
        attributes.setValue(null);
        createAttribute.add(attributes);
        assertThrows(IllegalArgumentException.class,
                () -> controller.createByCandidateId(existCandidateId, createAttribute),
                "Fields cannot be nullable");
    }
}
