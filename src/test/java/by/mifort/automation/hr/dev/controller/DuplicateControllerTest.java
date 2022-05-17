package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.db.H2Database;
import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class DuplicateControllerTest {

    private final DuplicateController duplicateController;
    private final CandidateController candidateController;
    private final CandidateAttributeController candidateAttributeController;
    private final AttributeTypesController attributeTypesController;
    private final EntityConverter<Candidate, CandidateDto> converter;
    private final H2Database h2Database = H2Database.getInstance();
    static int flag = 0;
    @Autowired
    public DuplicateControllerTest(DuplicateController duplicateController, CandidateController candidateController, CandidateAttributeController candidateAttributeController, AttributeTypesController attributeTypesController, EntityConverter<Candidate, CandidateDto> converter) {
        this.duplicateController = duplicateController;
        this.candidateController = candidateController;
        this.candidateAttributeController = candidateAttributeController;
        this.attributeTypesController = attributeTypesController;
        this.converter = converter;
    }

    @BeforeEach
    void init(){
        if(flag == 0) {
            h2Database.initializeAttributeTypes().forEach(attributeTypesController::create);
            h2Database.initializeCandidates().forEach(candidateController::create);
            h2Database.initializeAttributeTypes().forEach(attributeTypesController::create);

            List<CandidateAttributes> yauheni_attributes = h2Database.initializeCandidateAttributes().subList(0, 14);
            candidateAttributeController.createByCandidateId("yauheni_vozny", yauheni_attributes);

            List<CandidateAttributes> uliana_attributes = h2Database.initializeCandidateAttributes().subList(15, 29);
            candidateAttributeController.createByCandidateId("uliana_fomina", uliana_attributes);

            List<CandidateAttributes> artem_attributes = h2Database.initializeCandidateAttributes().subList(30, 44);
            candidateAttributeController.createByCandidateId("artem_skrebets", artem_attributes);

            List<CandidateAttributes> yauheni_clone_attributes = h2Database.initializeCandidateAttributes().subList(45, 59);
            candidateAttributeController.createByCandidateId("yauheni_vozny_clone", yauheni_clone_attributes);

            List<CandidateAttributes> vladimir_zelmanchuk = h2Database.initializeCandidateAttributes().subList(60, 74);
            candidateAttributeController.createByCandidateId("vladimir_zelmanchuk", vladimir_zelmanchuk);

            flag++;
        }
    }

    @Test
    @DisplayName("Find duplicates by ONE key attribute")
    void findDuplicatesByOneKeyAttribute(){
        String keyStrategy = "key_strategy";
        List<List<CandidateDto>> actualCandidates = duplicateController.getByAttributes(keyStrategy);
        List<List<CandidateDto>> expectedCandidates = new ArrayList<>();
        List<Candidate> candidates = List.of(
                h2Database.initializeCandidates().get(0),
                h2Database.initializeCandidates().get(6)
                );
        expectedCandidates.add(converter.convertToListEntityDto(candidates));

        for(int i=0;i<expectedCandidates.size(); i++){
            assertEquals(actualCandidates.get(i).get(i).getId(), expectedCandidates.get(i).get(i).getId());
        }
    }

    @Test
    @DisplayName("Find duplicates by {Passport, Firstname, Lastname, Date, City} ")
    void findDuplicatesByPassportFirstnameLastnameDateCity(){
        String strategy = "firstname_lastname_city_dob_passport";
        List<List<CandidateDto>> actualCandidates = duplicateController.getByAttributes(strategy);
        System.out.println(actualCandidates);
        List<CandidateDto> candidateFirst = List.of(
                converter.convertToEntityDto(h2Database.initializeCandidates().get(0)),
                converter.convertToEntityDto(h2Database.initializeCandidates().get(6))
        );
        List<List<CandidateDto>> expectedCandidates = new ArrayList<>();
        expectedCandidates.add(candidateFirst);
        for(int i=0;i<actualCandidates.size();i++){
            for(int j=0;j<actualCandidates.get(i).size();j++){
                assertEquals(actualCandidates.get(i).get(j).getId(), expectedCandidates.get(i).get(j).getId());
            }
        }
    }

    @Test
    @DisplayName("Find duplicates by {Firstname, Lastname, Company, City}")
    void findDuplicatesByFirstnameLastnameCompanyCity(){
        String keyStrategy = "key_strategy";
        List<List<CandidateDto>> actualCandidates = duplicateController.getByAttributes(keyStrategy);
        List<List<CandidateDto>> expectedCandidates = new ArrayList<>();
        List<Candidate> candidates = List.of(
                h2Database.initializeCandidates().get(0),
                h2Database.initializeCandidates().get(6)
        );
        expectedCandidates.add(converter.convertToListEntityDto(candidates));

        for(int i=0;i<expectedCandidates.size(); i++){
            assertEquals(actualCandidates.get(i).get(i).getId(), expectedCandidates.get(i).get(i).getId());
        }
    }

    @Test
    @DisplayName("Find duplicates by {Date of birth, Firstname, Lastname, City}")
    void findDuplicatesByFirstnameLastnameCityDateOfBirth(){
        String strategy = "firstname_lastname_dob_city";
        List<List<CandidateDto>> actualCandidates = duplicateController.getByAttributes(strategy);
        List<CandidateDto> candidateFirst = List.of(
                converter.convertToEntityDto(h2Database.initializeCandidates().get(0)),
                converter.convertToEntityDto(h2Database.initializeCandidates().get(6))
        );
        List<CandidateDto> candidateSecond = List.of(
                converter.convertToEntityDto(h2Database.initializeCandidates().get(1)),
                converter.convertToEntityDto(h2Database.initializeCandidates().get(4))
        );
        List<List<CandidateDto>> expectedCandidates = new ArrayList<>();
        expectedCandidates.add(candidateFirst);
        expectedCandidates.add(candidateSecond);

        for(int i=0;i<actualCandidates.size();i++){
            for(int j=0;j<actualCandidates.get(i).size();j++){
                assertEquals(actualCandidates.get(i).get(j).getId(), expectedCandidates.get(i).get(j).getId());
            }
        }
    }

    @Test
    @DisplayName("Not exists strategy of finding duplicates")
    void wrongStrategyFindDuplicates(){
        String NOT_EXISTS_STRATEGY = "WRONG_STRATEGY";
        assertThrows(IllegalArgumentException.class,
                    () -> duplicateController.getByAttributes(NOT_EXISTS_STRATEGY),
                "Strategy not exists!");
    }
}
