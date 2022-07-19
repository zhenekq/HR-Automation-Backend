package by.mifort.automation.hr.dev.service.duplicates.separate.impl;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.service.duplicates.separate.SeparateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SeparateServiceImpl implements SeparateService {

    @Override
    public List<List<Candidate>> separateByOneAttribute(List<CandidateAttributes> attributes, Comparator<CandidateAttributes> comparator) {
        List<List<Candidate>> result = new ArrayList<>();
        for(int i=0;i<attributes.size();i++){
            List<Candidate> logCandidates = new ArrayList<>();
            logCandidates.add(attributes.get(i).getCandidate());
            for(int j=0;j<attributes.size();j++){
                if(comparator.compare(attributes.get(i), attributes.get(j)) == 0 && i != j){
                    logCandidates.add(attributes.get(j).getCandidate());
                    if (!attributes.isEmpty()) {
                        attributes.remove(j);
                        j--;
                    }
                }
            }
            if(logCandidates.size() > 1) {
                result.add(logCandidates);
            }
        }
        return result;
    }
}
