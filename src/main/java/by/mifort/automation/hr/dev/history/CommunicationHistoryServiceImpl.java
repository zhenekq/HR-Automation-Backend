package by.mifort.automation.hr.dev.history;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.history.data.CommunicationHistory;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import by.mifort.automation.hr.dev.shared.differences.AssertDifferencesUpdates;
import by.mifort.automation.hr.dev.shared.exception.storage.CandidateExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.storage.HistoryExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommunicationHistoryServiceImpl implements CommunicationHistoryService {

    private final CommunicationHistoryRepository repository;
    private final CandidateRepository candidateRepository;
    private final AssertDifferencesUpdates assertDifferencesUpdates;

    @Autowired
    public CommunicationHistoryServiceImpl(CommunicationHistoryRepository repository, CandidateRepository candidateRepository, AssertDifferencesUpdates assertDifferencesUpdates) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
        this.assertDifferencesUpdates = assertDifferencesUpdates;
    }

    @Override
    public List<CommunicationHistory> getByCandidateId(String candidateId, FilterDto filterDto) {
        if (!candidateRepository.existsCandidateById(candidateId)) {
            throw new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND);
        }
        List<CommunicationHistory> communicationHistory;
        if (filterDto.getIsArchived() == null || !filterDto.getIsArchived()) {
            communicationHistory = repository
                    .findCommunicationHistoriesByCandidateIdAndIsArchivedEquals(candidateId, Boolean.FALSE);
            return communicationHistory;
        }
        communicationHistory = repository
                .findCommunicationHistoriesByCandidateIdAndIsArchivedEquals(candidateId, Boolean.TRUE);
        return communicationHistory;
    }

    @Override
    @Transactional
    public CommunicationHistory createByCandidateId(String candidateId, CommunicationHistory history) {
        Candidate candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND));
        String comment = history.getComment();
        history = new CommunicationHistory();
        history.setComment(comment);
        history.setCandidate(candidate);
        history.setIsArchived(Boolean.FALSE);
        history.setCreateDate(new Timestamp(new Date().getTime()));
        history.setUpdateDate(new Timestamp(new Date().getTime()));
        repository.save(history);
        return history;
    }

    @Override
    @Transactional
    public CommunicationHistory updateByCandidateId(String candidateId, CommunicationHistory history) {
        candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND));
        CommunicationHistory communicationHistory = repository
                .findCommunicationHistoryByCandidateIdAndId(candidateId, history.getId())
                .orElseThrow(() -> new DataNotFoundException(HistoryExceptionResponseStorage.historyNotFound(history.getId()), HttpStatus.NOT_FOUND));
        communicationHistory = assertDifferencesUpdates.assertCommunicationHistoryAndDto(communicationHistory, history);
        communicationHistory.setUpdateDate(new Timestamp(new Date().getTime()));
        repository.save(communicationHistory);
        return communicationHistory;
    }

    @Override
    public CommunicationHistory archiveByCandidateId(String candidateId, Integer historyId) {
        candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND));
        CommunicationHistory communicationHistory = repository
                .findCommunicationHistoryByCandidateIdAndId(candidateId, historyId)
                .orElseThrow(() -> new DataNotFoundException(HistoryExceptionResponseStorage.historyNotFound(historyId), HttpStatus.NOT_FOUND));
        communicationHistory.setIsArchived(Boolean.TRUE);
        repository.save(communicationHistory);
        return communicationHistory;
    }
}
