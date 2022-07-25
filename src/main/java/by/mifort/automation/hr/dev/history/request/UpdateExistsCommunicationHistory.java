package by.mifort.automation.hr.dev.history.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdateExistsCommunicationHistory {
    @NotNull
    private Integer id;
    @NotEmpty
    private String comment;
}
