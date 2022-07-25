package by.mifort.automation.hr.dev.attribute;

import java.util.List;

/**
 * Service layer for validation and business logic for CandidateAttributesRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CandidateAttributesRepository
 */

public interface CandidateAttributesService {

    /**
     * Get attributes of candidate by id
     *
     * @param candidateId candidate's identifier
     */
    List<CandidateAttributes> getByCandidateId(String candidateId);

    /**
     * Create new attributes with candidate
     *
     * @param candidateId candidate's identifier
     * @param attributes  body of list attributes
     */
    List<CandidateAttributes> createByCandidateId(String candidateId, List<CandidateAttributes> attributes);

    /**
     * Archive attributes with candidate
     *
     * @param candidateId  candidate's identifier
     * @param attributesId body of list attributes need to be archived
     */
    List<CandidateAttributes> archiveByCandidateId(String candidateId, List<Integer> attributesId);
}
