package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateMerge;
import by.mifort.automation.hr.dev.repository.CandidateMergeRepository;
import by.mifort.automation.hr.dev.repository.CandidateRepository;
import by.mifort.automation.hr.dev.service.CandidateMergeService;
import by.mifort.automation.hr.dev.util.differences.AssertDifferencesUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException("Merge not found with id: " + id));
    }

    @Override
    public CandidateMerge create(CandidateMerge candidateMerge) {
        Candidate firstCandidate = candidateRepository
                .findById(candidateMerge.getCandidate1().getId())
                .orElseThrow(() -> new EntityNotFoundException("First candidate do not exists!"));
        Candidate secondCandidate = candidateRepository
                .findById(candidateMerge.getCandidate2().getId())
                .orElseThrow(() -> new EntityNotFoundException("Second candidate do not exists!"));
        return repository.save(candidateMerge);
    }

    @Override
    @Transactional
    public CandidateMerge mergeById(Integer id) {
        CandidateMerge candidateMerge = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Merge information do not exists!"));
        Candidate firstCandidate = candidateMerge.getCandidate1();
        Candidate secondCandidate = candidateMerge.getCandidate2();
        Candidate resultCandidate = assertDifferences.assertCommonCandidate(firstCandidate, secondCandidate);
        candidateRepository.save(resultCandidate);
        return candidateMerge;
    }
}
