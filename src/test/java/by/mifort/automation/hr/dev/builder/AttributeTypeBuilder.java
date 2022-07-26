package by.mifort.automation.hr.dev.builder;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.type.AttributeTypes;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class AttributeTypeBuilder {
    private Integer id;
    private String name;
    private String basicType;
    private String validation;
    private Boolean isIdentifier;
    private Boolean isMultivalued;
    private List<CandidateAttributes> candidateAttributes;

    public AttributeTypeBuilder() {
    }

    public AttributeTypeBuilder(AttributeTypes copy) {
        this.id = copy.getId();
        this.name = copy.getName();
        this.basicType = copy.getBasicType();
        this.validation = copy.getValidation();
        this.isIdentifier = copy.getIsIdentifier();
        this.isMultivalued = copy.getIsMultivalued();
        this.candidateAttributes = copy.getCandidateAttributes();
    }

    public AttributeTypeBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public AttributeTypeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public AttributeTypeBuilder basicType(String basicType) {
        this.basicType = basicType;
        return this;
    }

    public AttributeTypeBuilder validation(String validation) {
        this.validation = validation;
        return this;
    }

    public AttributeTypeBuilder isIdentifier(Boolean isIdentifier) {
        this.isIdentifier = isIdentifier;
        return this;
    }

    public AttributeTypeBuilder isMultivalued(Boolean isMultivalued) {
        this.isMultivalued = isMultivalued;
        return this;
    }

    public AttributeTypeBuilder candidateAttributes(List<CandidateAttributes> candidateAttributes) {
        this.candidateAttributes = candidateAttributes;
        return this;
    }

    public AttributeTypeBuilder plain() {
        this.id = RandomUtils.nextInt();
        this.basicType = RandomString.make();
        this.validation = RandomString.make();
        this.isIdentifier = RandomUtils.nextBoolean();
        this.isMultivalued = RandomUtils.nextBoolean();

        return this;
    }

    public AttributeTypes build() {
        return new AttributeTypes(id, name, basicType, validation, isIdentifier, isMultivalued, candidateAttributes);
    }
}
