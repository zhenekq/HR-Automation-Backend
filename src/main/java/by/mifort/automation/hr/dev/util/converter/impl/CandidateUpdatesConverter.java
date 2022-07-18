package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.CandidateUpdateDto;
import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateUpdatesConverter implements EntityConverter<CandidateUpdate, CandidateUpdateDto> {
    @Override
    public CandidateUpdateDto convertToEntityDto(CandidateUpdate update) {
        CandidateUpdateDto dto = new CandidateUpdateDto();
        dto.setId(update.getId());
        dto.setSource(update.getSource());
        dto.setUpdateDate(update.getUpdateDate());
        dto.setChangeSet(update.getChangeSet());
        return dto;
    }

    @Override
    public List<CandidateUpdateDto> convertToListEntityDto(List<CandidateUpdate> candidateUpdates) {
        return candidateUpdates
                .stream()
                .map(this::convertToEntityDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateUpdate convertToEntity(CandidateUpdateDto dto) {
        CandidateUpdate update = new CandidateUpdate();
        return update;
    }

    @Override
    public List<CandidateUpdate> convertToListEntity(List<CandidateUpdateDto> candidateUpdateDtos) {
        return null;
    }
}
