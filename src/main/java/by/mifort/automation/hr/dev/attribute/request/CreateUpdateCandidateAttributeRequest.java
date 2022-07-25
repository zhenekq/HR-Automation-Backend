package by.mifort.automation.hr.dev.attribute.request;

import by.mifort.automation.hr.dev.type.AttributeTypes;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class CreateUpdateCandidateAttributeRequest {
    @NotNull
    private AttributeTypes attributeTypes;
    @NotEmpty
    private String value;
    @NotEmpty
    private String valueSource;
    @NotNull
    private Boolean isArchived;
}
