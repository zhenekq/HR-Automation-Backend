package by.mifort.automation.hr.dev.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Data transfer object for entity CandidateUpdates
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.entity.CandidateUpdate
 */
public class CandidateUpdateDto {
    private Integer id;
    private String source;
    private Timestamp updateDate;
    private List<ChangeSet> changeSet;

    public CandidateUpdateDto() {
    }

    public CandidateUpdateDto(Integer id, String source, Timestamp updateDate, List<ChangeSet> changeSet) {
        this.id = id;
        this.source = source;
        this.updateDate = updateDate;
        this.changeSet = changeSet;
    }

    public CandidateUpdateDto(String source, Timestamp updateDate, List<ChangeSet> changeSet) {
        this.source = source;
        this.updateDate = updateDate;
        this.changeSet = changeSet;
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

    public List<ChangeSet> getChangeSet() {
        return changeSet;
    }

    public void setChangeSet(List<ChangeSet> changeSet) {
        this.changeSet = changeSet;
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
                .append("source", source)
                .append("updateDate", updateDate)
                .append("changeSet", changeSet)
                .toString();
    }
}
