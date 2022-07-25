package by.mifort.automation.hr.dev.update.data;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Data transfer object for entity CandidateUpdates
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CandidateUpdate
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CandidateUpdateDto {
    private Integer id;
    private String source;
    private Timestamp updateDate;
    private List<ChangeSet> changeSet;
}
