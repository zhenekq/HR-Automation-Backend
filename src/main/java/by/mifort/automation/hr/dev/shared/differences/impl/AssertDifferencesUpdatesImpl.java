package by.mifort.automation.hr.dev.shared.differences.impl;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.history.data.CommunicationHistory;
import by.mifort.automation.hr.dev.keyword.Keyword;
import by.mifort.automation.hr.dev.shared.differences.AssertDifferencesUpdates;
import by.mifort.automation.hr.dev.type.AttributeTypes;
import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssertDifferencesUpdatesImpl implements AssertDifferencesUpdates {

    @Override
    public CommunicationHistory assertCommunicationHistoryAndDto(CommunicationHistory history, CommunicationHistory historyDto) {
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
    public AttributeTypes assertAttributeTypesAndDto(AttributeTypes types, AttributeTypes dto) {
        if (dto.getIsIdentifier() != null) {
            types.setIsIdentifier(dto.getIsIdentifier());
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
