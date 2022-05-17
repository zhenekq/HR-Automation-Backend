package by.mifort.automation.hr.dev.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * People merge candidates entity
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@Entity
@Table(name = "people_merge_candidates")
public class CandidateMerge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "reasoncomment")
    private String reasonComment;

    /**
     * @see Candidate Join 2 primary keys like a foreign key for 1 primary key
     */

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Candidate candidate1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Candidate candidate2;

    public CandidateMerge() {
    }

    public CandidateMerge(String status, String reason, String reasonComment) {
        this.status = status;
        this.reason = reason;
        this.reasonComment = reasonComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonComment() {
        return reasonComment;
    }

    public void setReasonComment(String reasonComment) {
        this.reasonComment = reasonComment;
    }

    public Candidate getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(Candidate candidate1) {
        this.candidate1 = candidate1;
    }

    public Candidate getCandidate2() {
        return candidate2;
    }

    public void setCandidate2(Candidate candidate2) {
        this.candidate2 = candidate2;
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
