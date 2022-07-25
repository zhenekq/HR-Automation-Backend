package by.mifort.automation.hr.dev.merge;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import by.mifort.automation.hr.dev.shared.differences.AssertDifferencesUpdates;
import by.mifort.automation.hr.dev.shared.exception.storage.CandidateExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.storage.MergeExceptionResponseStatus;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CandidateMergeServiceImpl implements CandidateMergeService {

    private final CandidateMergeRepository repository;
    private final CandidateRepository candidateRepository;
    private final AssertDifferencesUpdates assertDifferences;

    @Autowired
    public CandidateMergeServiceImpl(CandidateMergeRepository repository, CandidateRepository candidateRepository, AssertDifferencesUpdates assertDifferences) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
        this.assertDifferences = assertDifferences;
    }

    @Override
    public List<CandidateMerge> getAll(FilterDto filterDto) {
        List<CandidateMerge> candidateMerges;

        Integer page = filterDto.getPageNumber();
        Integer amount = filterDto.getPageSize();
        Pageable pageable = PageRequest.of(page - 1, amount);

        candidateMerges = repository.findAll(pageable).toList();
        return candidateMerges;
    }

    @Override
    public CandidateMerge getById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(MergeExceptionResponseStatus.mergeNotFound(id), HttpStatus.NOT_FOUND));
    }

    @Override
    public CandidateMerge create(CandidateMerge candidateMerge) {
        String firstCandidateId = candidateMerge.getCandidate1().getId();
        String secondCandidateId = candidateMerge.getCandidate2().getId();

        candidateRepository
                .findById(firstCandidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(firstCandidateId), HttpStatus.NOT_FOUND));

        candidateRepository
                .findById(secondCandidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(secondCandidateId), HttpStatus.NOT_FOUND));

        return repository.save(candidateMerge);
    }

    @Override
    @Transactional
    public CandidateMerge mergeById(Integer id) {
        CandidateMerge candidateMerge = repository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(MergeExceptionResponseStatus.mergeNotFound(id), HttpStatus.NOT_FOUND));
        Candidate firstCandidate = candidateMerge.getCandidate1();
        Candidate secondCandidate = candidateMerge.getCandidate2();
        Candidate resultCandidate = assertDifferences.assertCommonCandidate(firstCandidate, secondCandidate);
        candidateRepository.save(resultCandidate);
        return candidateMerge;
    }
}
