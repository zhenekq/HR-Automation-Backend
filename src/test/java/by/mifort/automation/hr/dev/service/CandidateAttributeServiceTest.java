package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.attribute.CandidateAttributesRepository;
import by.mifort.automation.hr.dev.attribute.CandidateAttributesService;
import by.mifort.automation.hr.dev.attribute.CandidateAttributesServiceImpl;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.type.AttributeTypesRepository;
import by.mifort.automation.hr.dev.update.CandidateUpdateRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CandidateAttributeServiceTest {

    @Mock
    private CandidateAttributesRepository repository;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private AttributeTypesRepository typesRepository;
    @Mock
    private CandidateUpdateRepository candidateUpdateRepository;
    private CandidateAttributesService service;

    @BeforeEach
    void setup() {
        service = new CandidateAttributesServiceImpl(
                repository,
                candidateRepository,
                typesRepository,
                candidateUpdateRepository
        );
    }

    @AfterEach
    void clean() {
        repository.deleteAll();
        candidateRepository.deleteAll();
        typesRepository.deleteAll();
        candidateUpdateRepository.deleteAll();
    }

    @Test
    @DisplayName("Check candidate attributes by not exists candidate id")
    void checkCandidateAttributesByNotExistsCandidate() {
        String candidateId = anyString();
        assertThatThrownBy(() -> service.getByCandidateId(candidateId))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Check candidate attributes by exists candidate id")
    void checkCandidateAttributesByExistsCandidate() {
        String candidateId = anyString();

        given(candidateRepository.findById(candidateId))
                .willReturn(Optional.of(new Candidate()));

        service.getByCandidateId(candidateId);

        verify(repository).findAllByCandidateId(candidateId);
    }

    @Test
    @Disabled
    void createByCandidateId() {
    }

    @Test
    @Disabled
    void archiveByCandidateId() {
    }
}
