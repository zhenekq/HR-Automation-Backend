package by.mifort.automation.hr.dev.attribute;

import lombok.*;

/**
 * Data transfer object for entity CandidateAttributes
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CandidateAttributes
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
    private String label;
    private Boolean isArchived;
}
