package by.mifort.automation.hr.dev.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CandidateMergeDto {

    private Integer id;
    private String status;
    private String reason;
    private String reasonComment;
    private String candidate1;
    private String candidate2;

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

    public String getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(String candidate1) {
        this.candidate1 = candidate1;
    }

    public String getCandidate2() {
        return candidate2;
    }

    public void setCandidate2(String candidate2) {
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
        return new ToStringBuilder(this)
                .append("id", id)
                .append("status", status)
                .append("reason", reason)
                .append("reasonComment", reasonComment)
                .append("candidate1", candidate1)
                .append("candidate2", candidate2)
                .toString();
    }
}
