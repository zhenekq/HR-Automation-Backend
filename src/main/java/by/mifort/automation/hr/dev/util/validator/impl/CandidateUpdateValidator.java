package by.mifort.automation.hr.dev.util.validator.impl;

import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.stereotype.Component;

@Component
public class CandidateUpdateValidator implements EntityValidator<CandidateUpdate> {
    @Override
    public boolean isValidParams(CandidateUpdate update) {
        return  update.getSource() != null &&
                update.getUpdateDate() != null &&
                update.getChangeSet() != null;
    }
}
