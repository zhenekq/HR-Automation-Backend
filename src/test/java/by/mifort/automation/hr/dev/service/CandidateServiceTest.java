package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.attribute.CandidateAttributesRepository;
import by.mifort.automation.hr.dev.builder.CandidateBuilder;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.candidate.CandidateService;
import by.mifort.automation.hr.dev.candidate.CandidateServiceImpl;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataCreateException;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private CandidateAttributesRepository candidateAttributesRepository;
    private CandidateService candidateService;

    @BeforeEach
    void setup() {
        candidateService = new CandidateServiceImpl(candidateRepository, candidateAttributesRepository);
    }

    @Test
    @DisplayName("Does save without attributes works")
    void checkIsCreateWithoutAttributesWorks() {
        Candidate candidate = new CandidateBuilder().plain();
        candidateService.create(candidate);
        verify(candidateRepository).save(candidate);
    }

    @Test
    @DisplayName("Does save with attributes works")
    void checkIsCreateWithAttributesWorks() {
        Candidate candidate = new CandidateBuilder().plain();
        candidateService.create(candidate);
        verify(candidateRepository).save(candidate);
        verify(candidateAttributesRepository).saveAll(TestUtil.emptyList(candidate));
    }

    @Test
    @DisplayName("Does save with attributes throw exception")
    void checkIsCreateWithAttributesThrowException() {
        Candidate unique = new CandidateBuilder().plain();
        given(candidateRepository.existsCandidateById(anyString())).willReturn(true);
        assertThatThrownBy(() -> candidateService.create(unique))
                .isInstanceOf((DataCreateException.class));
    }

    @Test
    @DisplayName("Check by exists candidate")
    void checkByExistsCandidateId() {
        String candidateId = anyString();
        given(candidateRepository.findById(candidateId)).willReturn(Optional.of(new Candidate()));

        candidateService.getById(candidateId);
        verify(candidateRepository).findById(candidateId);
    }

    @Test
    @DisplayName("Check by not exists candidate throw exception")
    void checkByNotExistsCandidateIdThrow() {
        String randomNotExistsCandidateId = anyString();
        assertThatThrownBy(() -> candidateService.getById(randomNotExistsCandidateId))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("Check by correct input data return paginated candidates")
    void getCorrectPaginatedCandidates() {
        int pageNumber = 1;
        int pageSize = 2;
        FilterDto filter = new FilterDto(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        List<String> keywords = filter.getKeyword();

        given(keywords).willReturn(notNull());
        given(candidateRepository.findAll(pageable)).willReturn(Page.empty());

        candidateService.getAll(filter);
        verify(candidateRepository).findAll(pageable);
    }

    @Test
    @DisplayName("Get candidates by keywords")
    void getCorrectPaginatedCandidatesWithNotNullKeywordQueryParameter() {
        int pageNumber = 1;
        int pageSize = 2;
        FilterDto filter = new FilterDto(pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        filter.setKeyword(List.of(
                anyString(),
                anyString()
        ));

        candidateService.getAll(filter);
        String firstKeyword = filter.getKeyword().get(0);
        verify(candidateRepository).findCandidatesByKeywordsContaining(firstKeyword, pageable);
    }
}