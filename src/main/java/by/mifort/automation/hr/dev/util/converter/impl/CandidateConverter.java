package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CandidateConverter implements EntityConverter<Candidate, CandidateDto> {
    @Override
    public CandidateDto convertToEntityDto(Candidate candidate) {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(candidate.getId());
        candidateDto.setStatus(candidate.getStatus());
        candidateDto.setLastContact(candidate.getLastContact());
        if (candidate.getKeywords() != null) {
            candidateDto.setKeywords(candidate.getKeywords());
        } else {
            candidateDto.setKeywords(Collections.emptyList());
        }
        if (candidate.getCommunicationHistory() != null) {
            candidateDto.setCommunicationHistory(candidate.getCommunicationHistory());
        } else {
            candidateDto.setCommunicationHistory(Collections.emptyList());
        }
        if (candidate.getAttributes() != null) {
            candidateDto.setCandidateAttributes(candidate.getAttributes());
        } else {
            candidateDto.setCandidateAttributes(Collections.emptyList());
        }
        return candidateDto;
    }

    @Override
    public List<CandidateDto> convertToListEntityDto(List<Candidate> candidates) {
        return candidates.stream()
                .map(this::convertToEntityDto)
                .toList();
    }

    @Override
    public Candidate convertToEntity(CandidateDto candidateDto) {
        Candidate candidate = new Candidate();
        candidate.setId(candidateDto.getId());
        candidate.setStatus(candidateDto.getStatus());
        candidate.setLastContact(candidateDto.getLastContact());
        return candidate;
    }

    @Override
    public List<Candidate> convertToListEntity(List<CandidateDto> candidateDtos) {
        return candidateDtos.stream()
                .map(this::convertToEntity)
                .toList();
    }
}
