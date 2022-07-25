package by.mifort.automation.hr.dev.builder;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.history.data.CommunicationHistory;
import by.mifort.automation.hr.dev.keyword.Keyword;
import by.mifort.automation.hr.dev.merge.CandidateMerge;
import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
import net.bytebuddy.utility.RandomString;

import java.sql.Timestamp;
import java.util.List;

public class CandidateBuilder {
    private String id;
    private Timestamp lastContact;
    private String status;
    private List<CandidateUpdate> updates;
    private List<Keyword> keywords;
    private List<CommunicationHistory> communicationHistory;
    private List<CandidateAttributes> attributes;
    private List<CandidateMerge> mergeCandidate1;
    private List<CandidateMerge> mergeCandidate2;

    public CandidateBuilder() {
    }

    public CandidateBuilder(Candidate copy) {
        this.id = copy.getId();
        this.lastContact = copy.getLastContact();
        this.status = copy.getStatus();
        this.updates = copy.getUpdates();
        this.keywords = copy.getKeywords();
        this.communicationHistory = copy.getCommunicationHistory();
        this.attributes = copy.getAttributes();
        this.mergeCandidate1 = copy.getMergeCandidate1();
        this.mergeCandidate2 = copy.getMergeCandidate2();
    }

    public CandidateBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CandidateBuilder lastContact(Timestamp lastContact) {
        this.lastContact = lastContact;
        return this;
    }

    public CandidateBuilder status(String status) {
        this.status = status;
        return this;
    }

    public CandidateBuilder updates(List<CandidateUpdate> updates) {
        this.updates = updates;
        return this;
    }

    public CandidateBuilder keywords(List<Keyword> keywords) {
        this.keywords = keywords;
        return this;
    }

    public CandidateBuilder communicationHistory(List<CommunicationHistory> communicationHistory) {
        this.communicationHistory = communicationHistory;
        return this;
    }

    public CandidateBuilder attributes(List<CandidateAttributes> attributes) {
        this.attributes = attributes;
        return this;
    }

    public CandidateBuilder mergeCandidate1(List<CandidateMerge> mergeCandidate1) {
        this.mergeCandidate1 = mergeCandidate1;
        return this;
    }

    public CandidateBuilder mergeCandidate2(List<CandidateMerge> mergeCandidate2) {
        this.mergeCandidate2 = mergeCandidate2;
        return this;
    }

    public Candidate plain() {
        this.id = RandomString.make();
        return new Candidate(this.id);
    }

    public Candidate build() {
        return new Candidate(id, lastContact, status, updates, keywords, communicationHistory, attributes, mergeCandidate1, mergeCandidate2);
    }
}
