package by.mifort.automation.hr.dev.history.data;

import by.mifort.automation.hr.dev.candidate.Candidate;

import java.sql.Timestamp;

public class CommunicationHistoryBuilder {
    private Integer id;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String comment;
    private Boolean isArchived;
    private Candidate candidate;

    public CommunicationHistoryBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public CommunicationHistoryBuilder setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    public CommunicationHistoryBuilder setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public CommunicationHistoryBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommunicationHistoryBuilder setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
        return this;
    }

    public CommunicationHistoryBuilder setCandidate(Candidate candidate) {
        this.candidate = candidate;
        return this;
    }

    public CommunicationHistory createCommunicationHistory() {
        return new CommunicationHistory(id, createDate, updateDate, comment, isArchived, candidate);
    }
}