package by.mifort.automation.hr.dev.service.duplicates.comparator;

import by.mifort.automation.hr.dev.entity.CandidateAttributes;

import java.util.Comparator;

public class SingleAttributeComparator implements Comparator<CandidateAttributes> {
    @Override
    public int compare(CandidateAttributes o1, CandidateAttributes o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
