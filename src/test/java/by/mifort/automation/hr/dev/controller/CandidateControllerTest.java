package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.db.H2Database;
import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.h2.tools.DeleteDbFiles;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase
public class CandidateControllerTest {

    private final CandidateController controller;
    private final EntityConverter<Candidate, CandidateDto> converter;
    private final AttributeTypesController attributeTypesController;
    private final CandidateAttributeController candidateAttributeController;
    private final H2Database h2Database = H2Database.getInstance();
    private List<Candidate> dbCandidates = new ArrayList<>();
    static int a = 0;

    @Autowired
    public CandidateControllerTest(CandidateController controller, EntityConverter<Candidate, CandidateDto> converter, AttributeTypesController attributeTypesController, CandidateAttributeController candidateAttributeController) {
        this.controller = controller;
        this.converter = converter;
        this.attributeTypesController = attributeTypesController;
        this.candidateAttributeController = candidateAttributeController;
    }

    @BeforeEach
    void init() {
        if(a == 0) {
            this.dbCandidates = h2Database.initializeCandidates();
            h2Database.initializeAttributeTypes().forEach(attributeTypesController::create);
            h2Database.initializeCandidates().forEach(controller::create);
            a++;
        }
        this.dbCandidates = h2Database.initializeCandidates();
    }

    @Test
    @DisplayName("Check not null paginated candidates")
    void assertPaginatedCandidates_NotNull() {
        FilterDto filterDto = new FilterDto(1, 1000);
        List<CandidateDto> candidates = controller.getAll(filterDto);
        System.out.println(candidates);
        assertNotNull(candidates);
    }

    @Test
    @DisplayName("Check equals paginated candidates")
    void assertPaginatedCandidates_WithFilterDto_Equals() {
        FilterDto filterDto = new FilterDto(1, 3);
        List<CandidateDto> actualCandidates = controller.getAll(filterDto);
        List<CandidateDto> expectedCandidates = converter.convertToListEntityDto(dbCandidates.subList(0, 3));
        assertEquals(actualCandidates.size(), expectedCandidates.size());
    }

    @Test
    @DisplayName("Check exists candidate by id")
    void checkExistsCandidateByIdExpected_NotNull() {
        String expectedCandidateId = dbCandidates.get(0).getId();
        CandidateDto actualCandidate = controller.getById(expectedCandidateId);
        assertNotNull(actualCandidate);
    }

    @Test
    @DisplayName("Check do not exist candidate by id, throw exception")
    void checkCandidateByIdExpected_ThrowEntityNotFoundException() {
        String randomString = RandomString.make();
        assertThrows(
                EntityNotFoundException.class,
                () -> controller.getById(randomString),
                "Entity not found!");
    }


    @Test
    @DisplayName("Check is created candidate not null")
    void checkIsCandidateCreatedInDatabase_NotNull() {
        String identifierRandom = RandomString.make();
        CandidateDto actualCandidate = controller.create(new Candidate(identifierRandom, new Timestamp(RandomUtils.nextLong()), identifierRandom));
        assertNotNull(actualCandidate);
    }

    @Test
    @DisplayName("Check is exists candidate cannot be created")
    void checkIsCandidateCreatedWithExistsId() {
        Candidate existsCandidate = h2Database.getCandidateWithRandomValues();
        existsCandidate.setStatus(null);
        assertThrows(IllegalArgumentException.class,
                    () -> controller.create(existsCandidate),
                "Fields cannot be nullable");
    }

    @Test
    @DisplayName("Check create keywords to candidate, without exists keywords")
    void addKeywordsToCandidateWithoutKeywords() {
        List<String> expectedKeywords = List.of("javaBackend", "pythonBackend", "react");
        Candidate expectedCandidate = dbCandidates.get(0);
        FilterDto filterDto = new FilterDto();
        filterDto.setKeyword(expectedKeywords);
        List<String> actualKeywords =  controller.addKeywords(expectedCandidate.getId(), filterDto)
                .stream()
                .map(object -> Objects.toString(object.getId(), null))
                .toList();
        assertEquals(expectedKeywords,actualKeywords);
    }

    @Test
    @DisplayName("Check are connected keyboards will be ignored when connected away, with new")
    void addKeywordsToCandidateWithKeywords(){
        List<String> addedKeywords = new ArrayList<>(List.of("java", "react"));
        Candidate expectedCandidate = dbCandidates.get(2);
        FilterDto filterDto = new FilterDto();
        filterDto.setKeyword(addedKeywords);
        controller.addKeywords(expectedCandidate.getId(), filterDto);
        addedKeywords.add("python");
        filterDto.setKeyword(addedKeywords);
        List<String> actualKeywords = controller.addKeywords(expectedCandidate.getId(), filterDto)
                .stream()
                .map(object -> Objects.toString(object.getId(), null))
                .toList();
        List<String> expectedKeywords = List.of("python");
        assertEquals(actualKeywords, expectedKeywords);
    }

}