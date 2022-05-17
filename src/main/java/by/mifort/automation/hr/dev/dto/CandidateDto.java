package by.mifort.automation.hr.dev.dto;

import by.mifort.automation.hr.dev.entity.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

public class CandidateDto {

    private String id;
    private Timestamp lastContact;
    private String status;
    private List<CandidateUpdate> candidateUpdates = new ArrayList<>();
    private List<Keyword> keywords = new ArrayList<>();
    private List<CommunicationHistory> communicationHistory = new ArrayList<>();
    private List<CandidateAttributes> candidateAttributes = new ArrayList<>();
    private List<CandidateMerge> mergeCandidates = new ArrayList<>();

    public CandidateDto() {
    }

    public CandidateDto(String id, Timestamp lastContact, String status) {
        this.id = id;
        this.lastContact = lastContact;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getLastContact() {
        return lastContact;
    }

    public void setLastContact(Timestamp lastContact) {
        this.lastContact = lastContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<CommunicationHistory> getCommunicationHistory() {
        return communicationHistory;
    }

    public void setCommunicationHistory(List<CommunicationHistory> communicationHistory) {
        this.communicationHistory = communicationHistory;
    }

    public List<CandidateUpdate> getCandidateUpdates() {
        return candidateUpdates;
    }

    public void setCandidateUpdates(List<CandidateUpdate> candidateUpdates) {
        this.candidateUpdates = candidateUpdates;
    }

    public List<CandidateAttributes> getCandidateAttributes() {
        return candidateAttributes;
    }

    public void setCandidateAttributes(List<CandidateAttributes> candidateAttributes) {
        this.candidateAttributes = candidateAttributes;
    }

    public List<CandidateMerge> getMergeCandidates() {
        return mergeCandidates;
    }

    public void setMergeCandidates(List<CandidateMerge> mergeCandidates) {
        this.mergeCandidates = mergeCandidates;
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
                .append("lastContact", lastContact)
                .append("status", status)
                .toString();
    }
}
