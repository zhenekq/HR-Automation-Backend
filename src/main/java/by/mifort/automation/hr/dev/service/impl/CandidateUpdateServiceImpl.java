package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import by.mifort.automation.hr.dev.repository.CandidateRepository;
import by.mifort.automation.hr.dev.repository.CandidateUpdateRepository;
import by.mifort.automation.hr.dev.service.CandidateUpdatesService;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CandidateUpdateServiceImpl implements CandidateUpdatesService {

    private final CandidateUpdateRepository repository;
    private final CandidateRepository candidateRepository;
    private final EntityValidator<CandidateUpdate> validator;

    public CandidateUpdateServiceImpl(CandidateUpdateRepository repository, CandidateRepository candidateRepository, EntityValidator<CandidateUpdate> validator) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
        this.validator = validator;
    }


    @Override
    public List<CandidateUpdate> getByCandidateId(String candidateId) {
        return repository
                .findAllByCandidateId(candidateId);
    }

    @Override
    public CandidateUpdate createByCandidateId(String candidateId, CandidateUpdate update) {
        Candidate candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate with id: " + candidateId + " not found!"));
        update.setCandidate(candidate);
        if(validator.isValidParams(update)) {
            return repository.save(update);
        }
        throw new IllegalArgumentException("Parameters cannot be nullable!");
    }
}
