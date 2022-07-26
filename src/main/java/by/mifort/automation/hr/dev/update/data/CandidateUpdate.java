package by.mifort.automation.hr.dev.update.data;

import by.mifort.automation.hr.dev.candidate.Candidate;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Updates about candidate
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@Entity
@Table(name = "peopleupdates")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CandidateUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "updatedate")
    private Timestamp updateDate;

    @Type(type = "jsonb")
    @Column(name = "changeset", columnDefinition = "json")
    private List<ChangeSet> changeSet;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Candidate candidate;

    public CandidateUpdate(String source, Timestamp updateDate, List<ChangeSet> changeSet, Candidate candidate) {
        this.source = source;
        this.updateDate = updateDate;
        this.changeSet = changeSet;
        this.candidate = candidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CandidateUpdate that = (CandidateUpdate) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSource(), getUpdateDate(), getChangeSet(), getCandidate());
    }
}
