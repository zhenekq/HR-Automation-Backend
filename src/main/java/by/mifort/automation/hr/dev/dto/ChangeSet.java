package by.mifort.automation.hr.dev.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChangeSet {

    private Type type;
    private String oldValue;
    private String newValue;

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + oldValue.hashCode();
        result = 31 * result + newValue.hashCode();
        return result;
    }
}
