package by.mifort.automation.hr.dev.entity;

import by.mifort.automation.hr.dev.dto.ChangeSet;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Updates about candidate
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@Entity
@Table(name = "peopleupdates")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
})
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

    public CandidateUpdate() {
    }

    public CandidateUpdate(Integer id, String source, Timestamp updateDate, List<ChangeSet> changeSet, Candidate candidate) {
        this.id = id;
        this.source = source;
        this.updateDate = updateDate;
        this.changeSet = changeSet;
        this.candidate = candidate;
    }

    public CandidateUpdate(String source, Timestamp updateDate, List<ChangeSet> changeSet, Candidate candidate) {
        this.source = source;
        this.updateDate = updateDate;
        this.changeSet = changeSet;
        this.candidate = candidate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @JsonAnyGetter
    public List<ChangeSet> getChangeSet() {
        return changeSet;
    }

    public void setChangeSet(List<ChangeSet> changeSet) {
        this.changeSet = changeSet;
    }

    @JsonIgnore
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
