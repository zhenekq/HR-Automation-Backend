package by.mifort.automation.hr.dev.dto;

import lombok.*;

/**
 * Data transfer object for entity CandidateAttributes
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.entity.CandidateAttributes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CandidateAttributesDto {
    private Integer id;
    private String value;
    private Integer valueSource;
    private String name;
    private Boolean isArchived;
}
