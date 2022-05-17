package by.mifort.automation.hr.dev.util.validator.impl;

import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.stereotype.Component;

@Component
public class AttributeTypeValidator implements EntityValidator<AttributeTypes> {
    @Override
    public boolean isValidParams(AttributeTypes attributeTypes) {
        return  attributeTypes.getBasicType() != null &&
                attributeTypes.getName() != null &&
                attributeTypes.getIdentifier() != null &&
                attributeTypes.getValidation() != null;
    }
}
