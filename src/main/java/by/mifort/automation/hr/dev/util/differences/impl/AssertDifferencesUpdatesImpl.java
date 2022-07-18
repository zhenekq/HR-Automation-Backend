package by.mifort.automation.hr.dev.util.differences.impl;

import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.entity.*;
import by.mifort.automation.hr.dev.util.differences.AssertDifferencesUpdates;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssertDifferencesUpdatesImpl implements AssertDifferencesUpdates {

    @Override
    public CommunicationHistory assertCommunicationHistoryAndDto(CommunicationHistory history, CommunicationHistoryDto historyDto) {
        if (historyDto.getComment() != null) {
            history.setComment(historyDto.getComment());
        }
        if (historyDto.getCreateDate() != null) {
            history.setCreateDate(historyDto.getCreateDate());
        }
        if (historyDto.getUpdateDate() != null) {
            history.setUpdateDate(historyDto.getUpdateDate());
        }
        return history;
    }

    @Override
    public AttributeTypes assertAttributeTypesAndDto(AttributeTypes types, AttributeTypesDto dto) {
        if (dto.getIdentifier() != null) {
            types.setIdentifier(dto.getIdentifier());
        }
        if (dto.getBasicType() != null) {
            types.setBasicType(dto.getBasicType());
        }
        if (dto.getName() != null) {
            types.setName(dto.getName());
        }
        if (dto.getValidation() != null) {
            types.setValidation(dto.getValidation());
        }
        return types;
    }

    @Override
    public Candidate assertCommonCandidate(Candidate firstCandidate, Candidate secondCandidate) {
        Candidate commonCandidate = new Candidate();

        List<CandidateUpdate> commonUpdates = firstCandidate.getUpdates();
        commonUpdates.addAll(secondCandidate.getUpdates());

        List<Keyword> commonKeywords = firstCandidate.getKeywords();
        commonKeywords.addAll(secondCandidate.getKeywords());

        List<CommunicationHistory> commonHistory = firstCandidate.getCommunicationHistory();
        commonHistory.addAll(secondCandidate.getCommunicationHistory());

        List<CandidateAttributes> commonAttributes = firstCandidate.getAttributes();
        commonAttributes.addAll(secondCandidate.getAttributes());

        commonCandidate.setId(firstCandidate.getId());
        commonCandidate.setStatus(firstCandidate.getStatus());
        commonCandidate.setLastContact(firstCandidate.getLastContact());

        commonCandidate.setUpdates(commonUpdates);
        commonCandidate.setKeywords(commonKeywords);
        commonCandidate.setCommunicationHistory(commonHistory);
        commonCandidate.setAttributes(commonAttributes);

        return commonCandidate;
    }
}
