package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Keyword;

import java.util.List;

/**
 * Service layer for validation and business logic KeywordRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.repository.KeywordRepository
 */

public interface KeywordService {

    /**
     * @param id  identifier of candidate
     * @param dto using for query with keywords
     * @return list of added unique keywords to candidate
     */
    List<Keyword> createByCandidateId(String id, FilterDto dto);

}
