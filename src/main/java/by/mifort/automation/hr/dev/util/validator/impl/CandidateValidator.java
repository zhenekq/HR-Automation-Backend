package by.mifort.automation.hr.dev.util.validator.impl;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.stereotype.Component;

@Component
public class CandidateValidator implements EntityValidator<Candidate> {
    @Override
    public boolean isValidParams(Candidate candidate) {
        return  candidate.getStatus() != null &&
                candidate.getId() != null;
    }
}
