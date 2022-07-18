package by.mifort.automation.hr.dev.dto;

public class ChangeSet {

    private Type type;
    private String oldValue;
    private String newValue;

    public ChangeSet() {
    }

    public ChangeSet(Type type, String oldValue, String newValue) {
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeSet changeSet = (ChangeSet) o;

        if (!type.equals(changeSet.type)) return false;
        if (!oldValue.equals(changeSet.oldValue)) return false;
        return newValue.equals(changeSet.newValue);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + oldValue.hashCode();
        result = 31 * result + newValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChangeSet{");
        sb.append("type='").append(type).append('\'');
        sb.append(", oldValue='").append(oldValue).append('\'');
        sb.append(", newValue='").append(newValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
