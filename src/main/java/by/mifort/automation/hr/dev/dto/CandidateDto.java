package by.mifort.automation.hr.dev.dto;

import by.mifort.automation.hr.dev.entity.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object for entity Candidate
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see Candidate
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CandidateDto {
    private String id;
    private Timestamp lastContact;
    private String status;
    private List<CandidateUpdate> candidateUpdates = new ArrayList<>();
    private List<Keyword> keywords = new ArrayList<>();
    private List<CommunicationHistory> communicationHistory = new ArrayList<>();
    private List<CandidateAttributes> candidateAttributes = new ArrayList<>();
    private List<CandidateMerge> mergeCandidates = new ArrayList<>();

    public CandidateDto(String id, Timestamp lastContact, String status) {
        this.id = id;
        this.lastContact = lastContact;
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CandidateDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", lastContact=").append(lastContact);
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
