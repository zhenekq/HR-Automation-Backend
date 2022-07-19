package by.mifort.automation.hr.dev.dto;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangeSet)) return false;
        ChangeSet changeSet = (ChangeSet) o;
        return Objects.equals(getType(), changeSet.getType()) && Objects.equals(getOldValue(), changeSet.getOldValue()) && Objects.equals(getNewValue(), changeSet.getNewValue());
    }
}
