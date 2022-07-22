package by.mifort.automation.hr.dev.candidate.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateErrorBody {
    private String error;
    private int status;
    private String message;
}
