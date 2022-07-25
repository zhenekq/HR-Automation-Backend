package by.mifort.automation.hr.dev.shared.exception.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseErrorBody {
    private String error;
    private int status;
    private String message;
}
