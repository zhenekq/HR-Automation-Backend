package by.mifort.automation.hr.dev.update;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.shared.exception.storage.CandidateExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateUpdateServiceImpl implements CandidateUpdatesService {

    private final CandidateUpdateRepository repository;
    private final CandidateRepository candidateRepository;

    public CandidateUpdateServiceImpl(CandidateUpdateRepository repository, CandidateRepository candidateRepository) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
    }


    @Override
    public List<CandidateUpdate> getByCandidateId(String candidateId) {
        candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND));
        return repository.findAllByCandidateId(candidateId);
    }

    @Override
    public CandidateUpdate createByCandidateId(String candidateId, CandidateUpdate update) {
        Candidate candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND));
        update.setCandidate(candidate);
        return repository.save(update);
    }
}
