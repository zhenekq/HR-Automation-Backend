package by.mifort.automation.hr.dev.dto;

import lombok.*;

/**
 * Data transfer object for entity AttributeTypes
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.entity.AttributeTypes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AttributeTypesDto {
    private Integer id;
    private String name;
    private String basicType;
    private String validation;
    private String label;
    private Boolean isIdentifier;
}
