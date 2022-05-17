package by.mifort.automation.hr.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Attribute types of candidate attributes
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@Entity
@Table(name = "attributetypes")
public class AttributeTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "basictype")
    private String basicType;

    @Column(name = "validation")
    private String validation;

    @Column(name = "isidentifier")
    private Boolean isIdentifier;

    @OneToMany(mappedBy = "attributeTypes")
    private List<CandidateAttributes> candidateAttributes;

    public AttributeTypes() {
    }

    public AttributeTypes(Integer id) {
        this.id = id;
    }

    public AttributeTypes(String name) {
        this.name = name;
    }

    public AttributeTypes(Integer id, String name, String basicType, String validation, Boolean isIdentifier) {
        this.id = id;
        this.name = name;
        this.basicType = basicType;
        this.validation = validation;
        this.isIdentifier = isIdentifier;
    }

    public AttributeTypes(String name, String basicType, String validation, Boolean isIdentifier) {
        this.name = name;
        this.basicType = basicType;
        this.validation = validation;
        this.isIdentifier = isIdentifier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasicType() {
        return basicType;
    }

    public void setBasicType(String basicType) {
        this.basicType = basicType;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Boolean getIdentifier() {
        return isIdentifier;
    }

    public void setIdentifier(Boolean identifier) {
        isIdentifier = identifier;
    }


    @JsonIgnore
    public List<CandidateAttributes> getCandidateAttributes() {
        return candidateAttributes;
    }

    public void setCandidateAttributes(List<CandidateAttributes> candidateAttributes) {
        this.candidateAttributes = candidateAttributes;
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
        final StringBuilder sb = new StringBuilder("AttributeTypes{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", basicType='").append(basicType).append('\'');
        sb.append(", validation='").append(validation).append('\'');
        sb.append(", isIdentifier=").append(isIdentifier);
        sb.append(", candidateAttributes=").append(candidateAttributes);
        sb.append('}');
        return sb.toString();
    }
}
