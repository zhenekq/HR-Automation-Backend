package by.mifort.automation.hr.dev.merge.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class CreateMergeRequest {
    @NotEmpty
    private String status;

    @NotEmpty
    private String reason;

    @NotEmpty
    private String reasonComment;

    @NotEmpty
    private String candidate1;

    @NotEmpty
    private String candidate2;
}
