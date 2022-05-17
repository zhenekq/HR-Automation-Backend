package by.mifort.automation.hr.dev.util.differences;

import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CommunicationHistory;

/**
 * Assert differences between 2 entities
 * Used for Patch updating entities
 *
 * @author yauheni_vozny
 * @version 1.0
 */

public interface AssertDifferencesUpdates {

    /**
     * Assert differences between history and historyDto
     *
     * @param history    history entity
     * @param historyDto historyDto entity
     * @return entity with common and different values
     */
    CommunicationHistory assertCommunicationHistoryAndDto(CommunicationHistory history, CommunicationHistoryDto historyDto);

    /**
     * Assert differences between history and historyDto
     *
     * @param types attributeTypes entity
     * @param dto   attributeTypesDto entity
     * @return entity with common and different values
     */
    AttributeTypes assertAttributeTypesAndDto(AttributeTypes types, AttributeTypesDto dto);

    /**
     * Get candidate with common information of 2 candidates
     *
     * @param firstCandidate first candidate
     * @param secondCandidate secondCandidate
     * @return Result common candidate of two candidates
     */
    Candidate assertCommonCandidate(Candidate firstCandidate, Candidate secondCandidate);

}
