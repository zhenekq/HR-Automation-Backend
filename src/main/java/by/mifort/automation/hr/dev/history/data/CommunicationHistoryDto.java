package by.mifort.automation.hr.dev.history.data;

import lombok.*;

import java.sql.Timestamp;

/**
 * Data transfer object for entity CommunicationHistory
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CommunicationHistory
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
