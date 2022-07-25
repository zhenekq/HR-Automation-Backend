package by.mifort.automation.hr.dev.duplicate.separate;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.candidate.Candidate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("all")
public interface SeparateService {

    List<List<Candidate>> separateByOneAttribute(List<CandidateAttributes> attributes, Comparator<CandidateAttributes> comparator);

    default List<List<Candidate>> separateBySeveralAttributes(List<Candidate> candidates, Comparator<Candidate> comparator) {
        List<List<Candidate>> result = new ArrayList<>();
        for (int i = 0; i < candidates.size(); i++) {
            List<Candidate> logCandidates = new ArrayList<>();
            Candidate iCandidate = candidates.get(i);
            logCandidates.add(iCandidate);
            for (int j = 0; j < candidates.size(); j++) {
                Candidate jCandidate = candidates.get(j);
                if (comparator.compare(iCandidate, jCandidate) == 0 && i != j) {
                    logCandidates.add(jCandidate);
                    candidates.remove(j);
                    j--;
                }
            }
            if (logCandidates.size() > 1) {
                result.add(logCandidates);
            }
        }
        return result;
    }

}
