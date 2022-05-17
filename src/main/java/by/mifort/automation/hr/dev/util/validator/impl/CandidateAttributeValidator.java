package by.mifort.automation.hr.dev.util.validator.impl;

import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateAttributeValidator implements EntityValidator<List<CandidateAttributes>> {
    @Override
    public boolean isValidParams(List<CandidateAttributes> attributes) {
        for (CandidateAttributes attribute: attributes){
            if(attribute.getAttributeTypes().getId() == null ||
               attribute.getValue() == null || attribute.getValueSource() == null){
                return false;
            }
        }
        return true;
    }
}
