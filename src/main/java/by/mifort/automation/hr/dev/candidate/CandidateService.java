package by.mifort.automation.hr.dev.candidate;

import by.mifort.automation.hr.dev.dto.FilterDto;

import java.util.List;

/**
 * Service layer for validation and business logic for CandidateRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CandidateRepository
 */

public interface CandidateService {

    /**
     * @param filterDto dto with pagination, sortTypes
     * @return List of all exists paginated candidates
     * @see FilterDto
     */
    List<Candidate> getAll(FilterDto filterDto);

    /**
     * @param id identifier of candidate
     * @return information about candidate
     */
    Candidate getById(String id);

    /**
     * Create new candidate in database
     *
     * @param candidate body of our candidate
     */
    Candidate create(Candidate candidate);
}
