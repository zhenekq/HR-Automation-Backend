package by.mifort.automation.hr.dev.builder;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
import by.mifort.automation.hr.dev.update.data.ChangeSet;
import net.bytebuddy.utility.RandomString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CandidateUpdateBuilder {
    private Integer id;
    private String source;
    private Timestamp updateDate;
    private List<ChangeSet> changeSet;
    private Candidate candidate;

    public CandidateUpdateBuilder() {
    }

    public CandidateUpdateBuilder(CandidateUpdate copy) {
        this.id = copy.getId();
        this.source = copy.getSource();
        this.updateDate = copy.getUpdateDate();
        this.changeSet = copy.getChangeSet();
        this.candidate = copy.getCandidate();
    }

    public CandidateUpdateBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public CandidateUpdateBuilder source(String source) {
        this.source = source;
        return this;
    }

    public CandidateUpdateBuilder updateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public CandidateUpdateBuilder changeSet(List<ChangeSet> changeSet) {
        this.changeSet = changeSet;
        return this;
    }

    public CandidateUpdateBuilder candidate(Candidate candidate) {
        this.candidate = candidate;
        return this;
    }

    public CandidateUpdateBuilder plain() {
        this.updateDate = new Timestamp(new Date().getTime());
        this.candidate = new CandidateBuilder().plain();
        this.source = RandomString.make();
        this.changeSet = new ArrayList<>();

        return this;
    }

    public CandidateUpdate build() {
        return new CandidateUpdate(id, source, updateDate, changeSet, candidate);
    }
}

