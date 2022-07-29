package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.builder.CandidateUpdateBuilder;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.update.CandidateUpdateRepository;
import by.mifort.automation.hr.dev.update.CandidateUpdateServiceImpl;
import by.mifort.automation.hr.dev.update.CandidateUpdatesService;
import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
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
public class CandidateUpdateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private CandidateUpdateRepository candidateUpdateRepository;
    private CandidateUpdatesService service;


    @BeforeEach
    void setup() {
        service = new CandidateUpdateServiceImpl(candidateUpdateRepository, candidateRepository);
    }

    @AfterEach
    void clean() {
        candidateUpdateRepository.deleteAll();
        candidateRepository.deleteAll();
    }

    @Test
    @DisplayName("Check updates by not exists candidate id")
    void checkUpdatesByNotExistsCandidate() {
        String candidateId = anyString();
        assertThatThrownBy(() -> service.getByCandidateId(candidateId))
                .isInstanceOf((DataNotFoundException.class));
    }

    @Test
    @DisplayName("Check updates by not exists candidate id")
    void checkUpdatesByExistsCandidate() {
        String candidateId = anyString();
        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));

        service.getByCandidateId(candidateId);
        verify(candidateUpdateRepository).findAllByCandidateId(candidateId);
    }

    @Test
    @DisplayName("Create updates by not exists candidate id")
    void createUpdatesByNotExistsCandidate() {
        CandidateUpdate update = new CandidateUpdateBuilder().plain().build();
        String candidateId = anyString();
        assertThatThrownBy(() -> service.createByCandidateId(candidateId, update))
                .isInstanceOf((DataNotFoundException.class));
    }

    @Test
    @DisplayName("Create updates by not exists candidate id")
    void createUpdatesByExistsCandidate() {
        CandidateUpdate update = new CandidateUpdateBuilder().plain().build();
        String candidateId = anyString();
        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));

        service.createByCandidateId(candidateId, update);
        verify(candidateUpdateRepository).save(update);
    }
}
