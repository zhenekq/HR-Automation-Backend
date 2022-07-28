package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.builder.CommunicationHistoryBuilder;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.history.CommunicationHistoryRepository;
import by.mifort.automation.hr.dev.history.CommunicationHistoryService;
import by.mifort.automation.hr.dev.history.CommunicationHistoryServiceImpl;
import by.mifort.automation.hr.dev.history.data.CommunicationHistory;
import by.mifort.automation.hr.dev.shared.differences.AssertDifferencesUpdates;
import by.mifort.automation.hr.dev.shared.differences.impl.AssertDifferencesUpdatesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    @DisplayName("Check EXISTS communication history by EXISTS candidate id")
    void checkExistsCommunicationHistoryByExistsCandidateId() {
        CommunicationHistory history = new CommunicationHistoryBuilder().plain().createCommunicationHistory();
    }

    @Test
    void createByCandidateId() {
    }

    @Test
    void updateByCandidateId() {
    }

    @Test
    void archiveByCandidateId() {
    }
}