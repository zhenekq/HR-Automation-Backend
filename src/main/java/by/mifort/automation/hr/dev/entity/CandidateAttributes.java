package by.mifort.automation.hr.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * People attributes that connected with candidate
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see AttributeTypes connected attributes with types
 */
@Entity
@Table(name = "peopleattributes")
public class CandidateAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private String value;

    @Column(name = "valuesource")
    private Integer valueSource;

    @Column(name = "is_archived")
    private Boolean isArchived;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private AttributeTypes attributeTypes;

    public CandidateAttributes() {
    }

    public CandidateAttributes(Integer id, String value, Integer valueSource, Candidate candidate, AttributeTypes attributeTypes) {
        this.id = id;
        this.value = value;
        this.valueSource = valueSource;
        this.candidate = candidate;
        this.attributeTypes = attributeTypes;
    }

    public CandidateAttributes(String value, Integer valueSource) {
        this.value = value;
        this.valueSource = valueSource;
    }

    public CandidateAttributes(String value, Integer valueSource, Candidate candidate, AttributeTypes attributeTypes, Boolean isArchived) {
        this.value = value;
        this.valueSource = valueSource;
        this.candidate = candidate;
        this.attributeTypes = attributeTypes;
        this.isArchived = isArchived;
    }

    public CandidateAttributes(Integer id, String value, Integer valueSource, Boolean isArchived, Candidate candidate, AttributeTypes attributeTypes) {
        this.id = id;
        this.value = value;
        this.valueSource = valueSource;
        this.isArchived = isArchived;
        this.candidate = candidate;
        this.attributeTypes = attributeTypes;
    }

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

    public Boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    @JsonIgnore
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public AttributeTypes getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(AttributeTypes attributeTypes) {
        this.attributeTypes = attributeTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidateAttributes that = (CandidateAttributes) o;

        return attributeTypes.equals(that.attributeTypes);
    }

    @Override
    public int hashCode() {
        return attributeTypes.getId().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CandidateAttributes{");
        sb.append("id=").append(id);
        sb.append(", value='").append(value).append('\'');
        sb.append(", valueSource=").append(valueSource);
        sb.append(", isArchived=").append(isArchived);
        sb.append(", candidate=").append(candidate);
        sb.append(", attributeTypes=").append(attributeTypes);
        sb.append('}');
        return sb.toString();
    }
}
