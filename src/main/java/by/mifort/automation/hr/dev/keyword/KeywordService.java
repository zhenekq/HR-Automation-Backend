package by.mifort.automation.hr.dev.keyword;

import by.mifort.automation.hr.dev.shared.data.FilterDto;

import java.util.List;

/**
 * Service layer for validation and business logic KeywordRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see KeywordRepository
 */

public interface KeywordService {

    /**
     * @param id  identifier of candidate
     * @param dto using for query with keywords
     * @return list of added unique keywords to candidate
     */
    List<Keyword> createByCandidateId(String id, FilterDto dto);

}
