package by.mifort.automation.hr.dev.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Data transfer object for entity AttributeTypes
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.entity.AttributeTypes
 */
public class AttributeTypesDto {

    private Integer id;
    private String name;
    private String basicType;
    private String validation;
    private String label;
    private Boolean isIdentifier;

    public AttributeTypesDto() {
    }

    public AttributeTypesDto(Integer id, String name, String basicType, String validation, Boolean isIdentifier) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("basicType", basicType)
                .append("validation", validation)
                .append("isIdentifier", isIdentifier)
                .toString();
    }
}
