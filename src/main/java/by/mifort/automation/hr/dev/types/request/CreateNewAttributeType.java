package by.mifort.automation.hr.dev.types.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class CreateNewAttributeType {
    @NotEmpty
    private String name;

    @NotEmpty
    private String basicType;

    @NotEmpty
    private String validation;

    @NotNull
    private Boolean isIdentifier;

    @NotNull
    private Boolean isMultivalued;
}
