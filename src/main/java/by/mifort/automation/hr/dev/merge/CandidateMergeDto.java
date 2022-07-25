package by.mifort.automation.hr.dev.merge;

import by.mifort.automation.hr.dev.candidate.CandidateDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CandidateMergeDto {
    private Integer id;
    private String status;
    private String reason;
    private String reasonComment;
    private CandidateDto candidate1;
    private CandidateDto candidate2;
}
