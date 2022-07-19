package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.CandidateMergeDto;
import by.mifort.automation.hr.dev.entity.CandidateMerge;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateMergeConverter implements EntityConverter<CandidateMerge, CandidateMergeDto> {

    @Override
    public CandidateMergeDto convertToEntityDto(CandidateMerge candidateMerge) {
        CandidateMergeDto dto = new CandidateMergeDto();
        dto.setId(candidateMerge.getId());
        dto.setStatus(candidateMerge.getStatus());
        dto.setReason(candidateMerge.getReason());
        dto.setReasonComment(candidateMerge.getReasonComment());
        dto.setCandidate1(candidateMerge.getCandidate1().getId());
        dto.setCandidate2(candidateMerge.getCandidate2().getId());

        return dto;
    }

    @Override
    public List<CandidateMergeDto> convertToListEntityDto(List<CandidateMerge> candidateMerges) {
        return candidateMerges
                .stream()
                .map(this::convertToEntityDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateMerge convertToEntity(CandidateMergeDto candidateMergeDto) {
        return null;
    }

    @Override
    public List<CandidateMerge> convertToListEntity(List<CandidateMergeDto> candidateMergeDtos) {
        return Collections.emptyList();
    }
}
