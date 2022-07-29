package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.builder.CommunicationHistoryBuilder;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.history.CommunicationHistoryRepository;
import by.mifort.automation.hr.dev.history.CommunicationHistoryService;
import by.mifort.automation.hr.dev.history.CommunicationHistoryServiceImpl;
import by.mifort.automation.hr.dev.history.data.CommunicationHistory;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import by.mifort.automation.hr.dev.shared.differences.AssertDifferencesUpdates;
import by.mifort.automation.hr.dev.shared.differences.impl.AssertDifferencesUpdatesImpl;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CommunicationHistoryServiceTest {

    @Mock
    private CommunicationHistoryRepository repository;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private AssertDifferencesUpdates assertDifference;
    private CommunicationHistoryService service;

    @BeforeEach
    void setup() {
        assertDifference = new AssertDifferencesUpdatesImpl();
        service = new CommunicationHistoryServiceImpl(repository, candidateRepository, assertDifference);
    }

    @AfterEach
    void clean() {
        repository.deleteAll();
        candidateRepository.deleteAll();
    }

    @Test
    @DisplayName("Check communication history by not exists candidate id")
    void checkCommunicationHistoryByNotExistsCandidateId() {
        String candidateId = anyString();
        assertThatThrownBy(() -> service.getByCandidateId(candidateId, new FilterDto()))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Check archived communication history by exists candidate id")
    void checkArchivedCommunicationHistoryByExistsCandidateId() {
        boolean isArchived = true;
        String candidateId = anyString();
        FilterDto filterDto = new FilterDto();
        filterDto.setIsArchived(isArchived);
        given(candidateRepository.existsCandidateById(candidateId))
                .willReturn(isArchived);

        service.getByCandidateId(candidateId, filterDto);

        verify(repository).findCommunicationHistoriesByCandidateIdAndIsArchivedEquals(candidateId, isArchived);

    }

    @Test
    @DisplayName("Check not archived communication history by exists candidate id")
    void checkNotArchivedCommunicationHistoryByExistsCandidateId() {
        boolean isArchived = false;
        String candidateId = anyString();
        FilterDto filterDto = new FilterDto();
        filterDto.setIsArchived(isArchived);
        given(candidateRepository.existsCandidateById(candidateId))
                .willReturn(true);

        service.getByCandidateId(candidateId, filterDto);

        verify(repository).findCommunicationHistoriesByCandidateIdAndIsArchivedEquals(candidateId, isArchived);

    }

    @Test
    @DisplayName("Create communication history by not exists candidate id")
    void createCommunicationHistoryByNotExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = anyString();
        assertThatThrownBy(() -> service.createByCandidateId(candidateId, communicationHistory))
                .isInstanceOf(DataNotFoundException.class);
    }


    @Test
    @DisplayName("Create communication history by exists candidate id")
    void createCommunicationHistoryByExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = anyString();

        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));

        CommunicationHistory createdCommunicationHistory = service.createByCandidateId(candidateId, communicationHistory);
        verify(repository).save(createdCommunicationHistory);
    }


    @Test
    @DisplayName("Update communication history by not exists candidate id")
    void updateCommunicationHistoryByNotExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = RandomString.make();
        assertThatThrownBy(() -> service.updateByCandidateId(candidateId, communicationHistory))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Update not exists communication history by exists candidate id")
    void updateNotExistsCommunicationHistoryByExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = anyString();

        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));

        assertThatThrownBy(() -> service.updateByCandidateId(candidateId, communicationHistory))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Update exists communication history by exists candidate id")
    void updateExistsCommunicationHistoryByExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = anyString();

        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));
        given(repository.findCommunicationHistoryByCandidateIdAndId(candidateId, communicationHistory.getId()))
                .willReturn(Optional.of(communicationHistory));

        CommunicationHistory updatedHistory = service.updateByCandidateId(candidateId, communicationHistory);
        verify(repository).save(updatedHistory);
    }

    @Test
    @DisplayName("Archive communication history by not exists candidate id")
    void archiveCommunicationHistoryByNotExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = RandomString.make();
        assertThatThrownBy(() -> service.archiveByCandidateId(candidateId, communicationHistory.getId()))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Archive not exists communication history by exists candidate id")
    void archiveNotExistsCommunicationHistoryByExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = anyString();

        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));

        assertThatThrownBy(() -> service.archiveByCandidateId(candidateId, communicationHistory.getId()))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Archive exists communication history by exists candidate id")
    void archiveExistsCommunicationHistoryByExistsCandidateId() {
        CommunicationHistory communicationHistory = new CommunicationHistoryBuilder()
                .plain()
                .createCommunicationHistory();
        String candidateId = anyString();

        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));
        given(repository.findCommunicationHistoryByCandidateIdAndId(candidateId, communicationHistory.getId()))
                .willReturn(Optional.of(communicationHistory));

        CommunicationHistory updatedHistory = service.archiveByCandidateId(candidateId, communicationHistory.getId());
        verify(repository).save(updatedHistory);
    }
}