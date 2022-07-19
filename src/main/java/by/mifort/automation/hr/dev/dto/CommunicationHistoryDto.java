package by.mifort.automation.hr.dev.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * Data transfer object for entity CommunicationHistory
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.entity.CommunicationHistory
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CommunicationHistoryDto {
    private Integer id;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String comment;
    private Boolean isArchived;
}
