package by.mifort.automation.hr.dev.candidate.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class CreateNewCandidateRequest {
    @NotEmpty
    private String id;
}
