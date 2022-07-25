package by.mifort.automation.hr.dev.history.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class CreateNewCommunicationHistory {
    @NotEmpty
    private String comment;
}
