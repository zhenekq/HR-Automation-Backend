package by.mifort.automation.hr.dev.dto;

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
    private String candidate1;
    private String candidate2;
}
