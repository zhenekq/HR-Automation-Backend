package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.dto.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CommunicationHistory;
import by.mifort.automation.hr.dev.repository.CandidateRepository;
import by.mifort.automation.hr.dev.repository.CommunicationHistoryRepository;
import by.mifort.automation.hr.dev.service.CommunicationHistoryService;
import by.mifort.automation.hr.dev.util.differences.AssertDifferencesUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
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
        candidateRepository
                .findById(candidateId)
                .orElseThrow(() ->  new EntityNotFoundException("Candidate with id: " + candidateId + " not found!"));
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
        Candidate candidate = candidateRepository.findById(candidateId)
                        .orElseThrow(() -> new EntityNotFoundException("Candidate not found!"));
        history.setCandidate(candidate);
        history.setCreateDate(new Timestamp(new Date().getTime()));
        history.setUpdateDate(new Timestamp(new Date().getTime()));
        repository.save(history);
        return history;
    }

    @Override
    @Transactional
    public CommunicationHistory updateByCandidateId(String candidateId, CommunicationHistoryDto historyDto) {
        if (historyDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        CommunicationHistory communicationHistory = repository
                .findCommunicationHistoryByCandidateIdAndId(candidateId, historyDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Communication history do not exists"));
        communicationHistory = assertDifferencesUpdates.assertCommunicationHistoryAndDto(communicationHistory, historyDto);
        communicationHistory.setUpdateDate(new Timestamp(new Date().getTime()));
        repository.save(communicationHistory);
        return communicationHistory;
    }

    @Override
    public CommunicationHistory archiveByCandidateId(String candidateId, Integer historyId) {
        CommunicationHistory communicationHistory = repository
                .findCommunicationHistoryByCandidateIdAndId(candidateId, historyId)
                .orElseThrow(() -> new EntityNotFoundException("Communication history do not exists"));
        communicationHistory.setArchived(Boolean.TRUE);
        repository.save(communicationHistory);
        return communicationHistory;
    }
}
