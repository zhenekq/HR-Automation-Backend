package by.mifort.automation.hr.dev.util.converter.impl;

import by.mifort.automation.hr.dev.dto.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.entity.CommunicationHistory;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommunicationHistoryConverter implements EntityConverter<CommunicationHistory, CommunicationHistoryDto> {
    @Override
    public CommunicationHistoryDto convertToEntityDto(CommunicationHistory history) {
        CommunicationHistoryDto dto = new CommunicationHistoryDto();
        dto.setId(history.getId());
        dto.setComment(history.getComment());
        dto.setCreateDate(history.getCreateDate());
        dto.setUpdateDate(history.getUpdateDate());
        dto.setIsArchived(history.getArchived());

        return dto;
    }

    @Override
    public List<CommunicationHistoryDto> convertToListEntityDto(List<CommunicationHistory> communicationHistories) {
        return communicationHistories.stream()
                .map(this::convertToEntityDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommunicationHistory convertToEntity(CommunicationHistoryDto historyDto) {
        CommunicationHistory history = new CommunicationHistory();
        history.setId(historyDto.getId());
        history.setComment(historyDto.getComment());
        history.setCreateDate(historyDto.getCreateDate());
        history.setUpdateDate(historyDto.getUpdateDate());
        history.setArchived(historyDto.getIsArchived());

        return history;
    }

    @Override
    public List<CommunicationHistory> convertToListEntity(List<CommunicationHistoryDto> communicationHistoryDtos) {
        return communicationHistoryDtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
