package by.mifort.automation.hr.dev.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Data transfer object for entity CandidateAttributes
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.entity.CandidateAttributes
 */
public class CandidateAttributesDto {

    private Integer id;
    private String value;
    private Integer valueSource;
    private String name;
    private Boolean isArchived;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getValueSource() {
        return valueSource;
    }

    public void setValueSource(Integer valueSource) {
        this.valueSource = valueSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CandidateAttributesDto{");
        sb.append("id=").append(id);
        sb.append(", value='").append(value).append('\'');
        sb.append(", valueSource=").append(valueSource);
        sb.append(", name='").append(name).append('\'');
        sb.append(", isArchived=").append(isArchived);
        sb.append('}');
        return sb.toString();
    }
}
