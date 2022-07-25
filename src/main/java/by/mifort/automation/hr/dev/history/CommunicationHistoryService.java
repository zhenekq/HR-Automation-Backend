package by.mifort.automation.hr.dev.history;

import by.mifort.automation.hr.dev.candidate.dto.FilterDto;
import by.mifort.automation.hr.dev.history.data.CommunicationHistory;

import java.util.List;

/**
 * Service layer for validation and business logic CommunicationHistoryRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CommunicationHistoryRepository
 */


public interface CommunicationHistoryService {

    /**
     * Get history of candidate by id
     *
     * @param candidateId candidate's identifier
     * @return List of archived or not histories by candidate's identifier
     */
    List<CommunicationHistory> getByCandidateId(String candidateId, FilterDto filterDto);

    /**
     * Create new communication history with candidate
     *
     * @param candidateId candidate's identifier
     * @param history     body of history
     */
    CommunicationHistory createByCandidateId(String candidateId, CommunicationHistory history);

    /**
     * Update exists communication history with candidate
     *
     * @param candidateId candidate's identifier
     * @param historyDto  body of history
     */
    CommunicationHistory updateByCandidateId(String candidateId, CommunicationHistory historyDto);

    /**
     * Archive exists communication history with candidate
     *
     * @param candidateId candidate's identifier
     * @param historyId   history's identifier
     */
    CommunicationHistory archiveByCandidateId(String candidateId, Integer historyId);
}
