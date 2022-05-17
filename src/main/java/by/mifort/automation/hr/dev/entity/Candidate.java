package by.mifort.automation.hr.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Head entity "people" that has all information
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@Entity
@Table(name = "people", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {

    @Id
    private String id;

    @Column(name = "lastcontact")
    private Timestamp lastContact;

    @Column(name = "status")
    private String status;

    /**
     * Information about candidate updates
     *
     * @see CandidateUpdate
     */
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CandidateUpdate> updates;

    /**
     * Keyboards that connection with candidate
     *
     * @see Keyword
     */
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Keyword> keywords;

    /**
     * Communication history with candidate
     *
     * @see CommunicationHistory
     */
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CommunicationHistory> communicationHistory;

    /**
     * Attributes that connected with candidate
     *
     * @see CandidateAttributes
     */
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CandidateAttributes> attributes;


    /**
     * Merged candidates
     *
     * @see CandidateMerge
     */
    @OneToMany(mappedBy = "candidate1")
    private List<CandidateMerge> mergeCandidate1;

    @OneToMany(mappedBy = "candidate2")
    private List<CandidateMerge> mergeCandidate2;

    public Candidate() {
    }

    public Candidate(String id) {
        this.id = id;
    }

    public Candidate(String id, Timestamp lastContact, String status) {
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

    @JsonIgnore
    public List<CandidateUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(List<CandidateUpdate> updates) {
        this.updates = updates;
    }

    @JsonIgnore
    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @JsonIgnore
    public List<CommunicationHistory> getCommunicationHistory() {
        return communicationHistory;
    }

    public void setCommunicationHistory(List<CommunicationHistory> communicationHistory) {
        this.communicationHistory = communicationHistory;
    }

    @JsonIgnore
    public List<CandidateAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CandidateAttributes> attributes) {
        this.attributes = attributes;
    }

    public List<CandidateMerge> getMergeCandidate1() {
        return mergeCandidate1;
    }

    public List<CandidateMerge> getMergeCandidate2() {
        return mergeCandidate2;
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
                .append("updates", updates)
                .append("keywords", keywords)
                .append("communicationHistory", communicationHistory)
                .append("attributes", attributes)
                .append("mergeCandidate1", mergeCandidate1)
                .append("mergeCandidate2", mergeCandidate2)
                .toString();
    }
}
