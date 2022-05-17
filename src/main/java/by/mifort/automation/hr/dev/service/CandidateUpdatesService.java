package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.entity.CandidateUpdate;

import java.util.List;

public interface CandidateUpdatesService {

    List<CandidateUpdate> getByCandidateId(String candidateId);

    CandidateUpdate createByCandidateId(String candidateId, CandidateUpdate update);

}
