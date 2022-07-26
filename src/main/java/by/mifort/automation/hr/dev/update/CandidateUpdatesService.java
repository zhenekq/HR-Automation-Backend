package by.mifort.automation.hr.dev.update;

import by.mifort.automation.hr.dev.update.data.CandidateUpdate;

import java.util.List;

public interface CandidateUpdatesService {

    List<CandidateUpdate> getByCandidateId(String candidateId);

    CandidateUpdate createByCandidateId(String candidateId, CandidateUpdate update);

}
