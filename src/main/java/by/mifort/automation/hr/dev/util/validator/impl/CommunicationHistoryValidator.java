package by.mifort.automation.hr.dev.util.validator.impl;

import by.mifort.automation.hr.dev.entity.CommunicationHistory;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.stereotype.Component;

@Component
public class CommunicationHistoryValidator implements EntityValidator<CommunicationHistory> {
    @Override
    public boolean isValidParams(CommunicationHistory communicationHistory) {
        return  communicationHistory.getComment() != null &&
                communicationHistory.getCreateDate() != null &&
                communicationHistory.getUpdateDate() != null;
    }
}
