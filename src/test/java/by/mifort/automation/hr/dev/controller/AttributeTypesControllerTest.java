package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.db.H2Database;
import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class AttributeTypesControllerTest {

    private final AttributeTypesController controller;
    private final H2Database h2Database = H2Database.getInstance();
    private final EntityConverter<AttributeTypes, AttributeTypesDto> converter;
    private List<AttributeTypes> attributeTypes;

    @Autowired
    AttributeTypesControllerTest(AttributeTypesController controller, EntityConverter<AttributeTypes, AttributeTypesDto> converter) {
        this.controller = controller;
        this.converter = converter;
    }

    @BeforeEach
    void init(){
        attributeTypes = h2Database.initializeAttributeTypes();
        attributeTypes.forEach(controller::create);
    }

    @Test
    @DisplayName("Get paginated attribute types with INCORRECT request params")
    void checkAttributeTypesGetPaginated_IncorrectFilter(){
        FilterDto filterDto = new FilterDto();
        assertThrows(ResponseStatusException.class,
                () -> controller.getAll(filterDto),
                "Incorrect data!");
    }

    @Test
    @DisplayName("Create attribute type with correct data")
    void checkAttributeTypeCreate_CorrectData(){
        AttributeTypes createAttribute = h2Database.getAttributeTypeWithRandomValues();
        AttributeTypesDto actualAttributeType = controller.create(createAttribute);
        AttributeTypesDto expectedAttributeType = controller.getById(actualAttributeType.getId());

        assertEquals(actualAttributeType, expectedAttributeType);
    }

    @Test
    @DisplayName("Create attribute type with incorrect data")
    void checkAttributeTypeCreate_IncorrectData(){
        AttributeTypes createAttribute = h2Database.getAttributeTypeWithRandomValues();
        createAttribute.setName(null);
        assertThrows(IllegalArgumentException.class,
                () -> controller.create(createAttribute),
                "Parameters cannot be nullable!");
    }

    @Test
    @DisplayName("Check exists attribute type by id")
    void checkAttributeTypeById_NotNull(){
        AttributeTypes attribute = this.attributeTypes.get(0);
        AttributeTypesDto actualAttribute = controller.getById(1);
        AttributeTypesDto expectedAttribute = converter.convertToEntityDto(attribute);

        assertEquals(actualAttribute, expectedAttribute);
    }

    @Test
    @DisplayName("Check do not exists attribute type by id")
    void checkAttributeTypeById_Nullable() {
        Integer notExistsIdentifier = this.attributeTypes.size() + 1;
        assertThrows(EntityNotFoundException.class,
                () -> controller.getById(notExistsIdentifier),
                "Attribute do not exits");
    }

    @Test
    @DisplayName("Check update exists attribute type by id, all fields")
    void checkAttributeTypeUpdateById_ExistsNotNull_AllFields(){
        AttributeTypesDto expectedAttribute = converter.convertToEntityDto(this.attributeTypes.get(0));
        expectedAttribute.setName(RandomString.make());
        expectedAttribute.setBasicType(RandomString.make());
        expectedAttribute.setValidation(RandomString.make());
        expectedAttribute.setIdentifier(RandomUtils.nextBoolean());
        AttributeTypesDto actualAttribute = controller.updateByAttributeId(1, expectedAttribute);

        assertEquals(expectedAttribute, actualAttribute);
    }

    @Test
    @DisplayName("Check update exists attribute type by id, not all fields")
    void checkAttributeTypeUpdateById_ExistsNotNull_NotAllFields(){
        AttributeTypesDto expectedAttribute = converter.convertToEntityDto(this.attributeTypes.get(0));
        expectedAttribute.setName(RandomString.make());
        expectedAttribute.setBasicType(RandomString.make());
        AttributeTypesDto actualAttribute = controller.updateByAttributeId(1, expectedAttribute);

        assertEquals(expectedAttribute, actualAttribute);
    }

    @Test
    @DisplayName("Check update exists attribute type by id, not all fields")
    void checkAttributeTypeUpdateById_NotExists(){
        AttributeTypes randomAttribute = h2Database.getAttributeTypeWithRandomValues();
        randomAttribute.setName(RandomString.make());
        randomAttribute.setBasicType(RandomString.make());
        Integer invalidId = this.attributeTypes.size() + 1;
        AttributeTypesDto expectedAttribute = converter.convertToEntityDto(randomAttribute);
        assertThrows(EntityNotFoundException.class,
                () -> controller.updateByAttributeId(invalidId, expectedAttribute),
                "Attribute type not found!");
    }

    @Test
    @DisplayName("Check archive exists attribute type by id")
    void checkAttributeTypeArchive_Exists(){
        AttributeTypes expectedAttribute = this.attributeTypes.get(0);
        AttributeTypesDto actualAttribute = controller.deleteByAttributeId(expectedAttribute.getId());
    }

    @Test
    @DisplayName("Check archive exists attribute type by id")
    void checkAttributeTypeArchive_NotExists(){
        Integer notExistsAttributeType = this.attributeTypes.size() + RandomUtils.nextInt();
        assertThrows(EntityNotFoundException.class,
                () -> controller.deleteByAttributeId(notExistsAttributeType),
                "Attribute type not exists!");
    }
}