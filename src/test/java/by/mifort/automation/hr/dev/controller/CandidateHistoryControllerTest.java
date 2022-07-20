package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.db.H2Database;
import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.dto.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CommunicationHistory;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CandidateHistoryControllerTest {

    private final CandidateHistoryController historyController;
    private final CandidateController candidateController;
    private final AttributeTypesController attributeTypesController;
    private List<CommunicationHistory> histories = H2Database.getInstance().initializeHistories();
    private final EntityConverter<CommunicationHistory, CommunicationHistoryDto> converter;
    private final EntityConverter<AttributeTypes, AttributeTypesDto> attributeTypesConverter;
    private final EntityConverter<Candidate, CandidateDto> candidateConverter;

    static int flag = 0;

    @Autowired
    CandidateHistoryControllerTest(CandidateHistoryController historyController, CandidateController candidateController, AttributeTypesController attributeTypesController, EntityConverter<CommunicationHistory, CommunicationHistoryDto> converter, EntityConverter<AttributeTypes, AttributeTypesDto> attributeTypesConverter, EntityConverter<Candidate, CandidateDto> candidateConverter) {
        this.historyController = historyController;
        this.candidateController = candidateController;
        this.attributeTypesController = attributeTypesController;
        this.converter = converter;
        this.attributeTypesConverter = attributeTypesConverter;
        this.candidateConverter = candidateConverter;
    }

    @BeforeEach
    public void init(){
        if(flag == 0) {
            List<AttributeTypesDto> types = attributeTypesConverter.convertToListEntityDto(H2Database.getInstance().initializeAttributeTypes());
            types.forEach(attributeTypesController::create);
            List<CandidateDto> candidateDto = candidateConverter.convertToListEntityDto(H2Database.getInstance().initializeCandidates());
            //candidateDto.forEach(candidateController::create);
            H2Database.getInstance().initializeHistories().forEach(
                    history -> historyController.createByCandidateId(history.getCandidate().getId(), converter.convertToEntityDto(history))
            );
            flag++;
        }
        histories = H2Database.getInstance().initializeHistories();
    }

    @Test
    @DisplayName("Get candidate's exists history_not_archived")
    void checkWhenCandidateExistsCommunicationHistory_ArchivedFalse() {
        List<CommunicationHistory> expected = new ArrayList<>(List.of(
                histories.get(0)
        ));
        List<CommunicationHistoryDto> actualHistories = historyController.getByCandidateId("yauheni_vozny",new FilterDto());
        List<CommunicationHistoryDto> expectedHistories = converter.convertToListEntityDto(expected);
        for(int i=0;i<actualHistories.size();i++){
            assertEquals(actualHistories.get(i).getId(), expectedHistories.get(i).getId());
        }
    }

    @Test
    @DisplayName("Check communication history of not founded candidate")
    void checkWhenCandidateDoNotExistsCommunicationHistory_ArchivedFalse(){
        String randomId = RandomString.make();
        FilterDto emptyFilter = new FilterDto();
        assertThrows(EntityNotFoundException.class,
                () -> historyController.getByCandidateId(randomId, emptyFilter),
                "Entity not found!");
    }

    @Test
    @DisplayName("Check empty history of exists candidate")
    void checkWhenCandidateExistsCommunicationHistoryDoNotExists_ArchivedFalse(){
        List<CommunicationHistoryDto> histories = historyController.getByCandidateId("empty_candidate", new FilterDto());
        assertEquals(Collections.emptyList(), histories);
    }

    @Test
    @DisplayName("Get candidate's exists history_archived")
    void checkWhenCandidateExistsCommunicationHistory_ArchivedTrue(){
        List<CommunicationHistory> expected = new ArrayList<>(List.of(
                histories.get(5),
                histories.get(6)
        ));
        FilterDto filterDto = new FilterDto();
        filterDto.setIsArchived(Boolean.TRUE);
        List<CommunicationHistoryDto> actualHistory = historyController.getByCandidateId("yauheni_vozny",filterDto);
        List<CommunicationHistoryDto> expectedHistory = converter.convertToListEntityDto(expected);
        for(int i=0;i<actualHistory.size();i++){
            assertEquals(actualHistory.get(i).getId(), expectedHistory.get(i).getId());
        }
    }


    @Test
    @DisplayName("Create communication history with exists candidate")
    void checkWhenCommunicationHistoryCreate_CandidateExists() {
        CommunicationHistory createHistory = new CommunicationHistory(new Timestamp(RandomUtils.nextLong()),
                new Timestamp(RandomUtils.nextLong()), RandomString.make(), Boolean.FALSE, new Candidate("artem_skrebets"));
        CommunicationHistoryDto actualHistory = historyController.createByCandidateId("artem_skrebets", converter.convertToEntityDto(createHistory));
        CommunicationHistoryDto expectedHistory = converter.convertToEntityDto(createHistory);
        assertEquals(actualHistory.getComment(), expectedHistory.getComment());
    }

    @Test
    @DisplayName("Create communication history with do not exists candidate")
    void checkWhenCommunicationHistoryCreate_CandidateDoNotExists() {
        String randomCandidate = RandomString.make();
        CommunicationHistory createHistory = new CommunicationHistory(new Timestamp(RandomUtils.nextLong()),
                new Timestamp(RandomUtils.nextLong()), RandomString.make(), Boolean.FALSE, new Candidate(randomCandidate));
        CommunicationHistoryDto errorHistory = converter.convertToEntityDto(createHistory);
        assertThrows(EntityNotFoundException.class,
                () -> historyController.createByCandidateId(randomCandidate, errorHistory),
                "Candidate do not exists!");
    }

    @Test
    @DisplayName("Update exists candidate with connected history, with all fields")
    void checkWhenUpdateCommunicationHistory_CandidateNotNullTruth(){
        CommunicationHistory history = histories.get(0);
        history.setComment("newComment");
        history.setUpdateDate(new Timestamp(new Date().getTime()));
        history.setCreateDate(new Timestamp(new Date().getTime()));
        CommunicationHistoryDto actualHistory = converter.convertToEntityDto(history);
        CommunicationHistoryDto expectedHistory = historyController.updateByCandidateId("yauheni_vozny", actualHistory);
        assertEquals(actualHistory.getComment(), expectedHistory.getComment());
        assertEquals(actualHistory.getCreateDate().getTime(), expectedHistory.getCreateDate().getTime());
    }

    @Test
    @DisplayName("Update exists candidate with connected history, with only one update field")
    void checkWhenUpdateCommunicationHistoryOneField_CandidateNotNullTruth(){
        CommunicationHistory history = histories.get(0);
        history.setComment("newComment");
        CommunicationHistoryDto actualHistory = converter.convertToEntityDto(history);
        CommunicationHistoryDto expectedHistory = historyController.updateByCandidateId("yauheni_vozny", actualHistory);
        assertEquals(actualHistory.getComment(), expectedHistory.getComment());
    }

    @Test
    @DisplayName("Update exists candidate with not connected to candidate another history id, with all fields")
    void checkWhenUpdateCommunicationHistory_CandidateNotNullFake() {
        CommunicationHistory history = histories.get(1);
        history.setComment("newComment");
        history.setUpdateDate(new Timestamp(RandomUtils.nextLong()));
        history.setCreateDate(new Timestamp(RandomUtils.nextLong()));
        CommunicationHistoryDto actualHistory = converter.convertToEntityDto(history);
        assertThrows(EntityNotFoundException.class,
                () -> historyController.updateByCandidateId("yauheni_vozny", actualHistory),
                "Communication history not found!");
    }

    @Test
    @DisplayName("Update do not exists candidate with exists history that connected to other candidate")
    void checkWhenUpdateCommunicationHistory_CandidateDoNotExists() {
        CommunicationHistory history = histories.get(0);
        history.setComment("newComment");
        history.setUpdateDate(new Timestamp(RandomUtils.nextLong()));
        history.setCreateDate(new Timestamp(RandomUtils.nextLong()));
        CommunicationHistoryDto actualHistory = converter.convertToEntityDto(history);
        String doNotExistsCandidateId = RandomString.make();
        assertThrows(EntityNotFoundException.class,
                () -> historyController.updateByCandidateId(doNotExistsCandidateId, actualHistory),
                "Communication history not found!");
    }

    @Test
    @DisplayName("Archive exists candidate and connected to him history")
    void checkArchiveCommunicationHistory_CandidateExists(){
        CommunicationHistory expectedHistory = histories.get(0);
        expectedHistory.setArchived(Boolean.TRUE);
        String candidateId = "yauheni_vozny";
        CommunicationHistoryDto actualHistory = historyController.archiveHistoryWithCandidate(candidateId, expectedHistory.getId());
        assertEquals(expectedHistory.getArchived(), actualHistory.getIsArchived());
    }

    @Test
    @DisplayName("Archive exists candidate and not connected to him history")
    void checkArchiveCommunicationHistoryFake_CandidateExists(){
        CommunicationHistory expectedHistory = histories.get(1);
        String candidateId = "yauheni_vozny";
        Integer historyId = expectedHistory.getId();
        assertThrows(EntityNotFoundException.class,
                () -> historyController.archiveHistoryWithCandidate(candidateId, historyId),
                "Communication history not found!");
    }

    @Test
    @DisplayName("Archive do not exists candidate and not connected to him history")
    void checkArchiveCommunicationHistoryFake_CandidateFake(){
        CommunicationHistory expectedHistory = histories.get(1);
        String candidateId = RandomString.make();
        Integer historyId = expectedHistory.getId();
        assertThrows(EntityNotFoundException.class,
                () -> historyController.archiveHistoryWithCandidate(candidateId, historyId),
                "Communication history not found!");
    }
}