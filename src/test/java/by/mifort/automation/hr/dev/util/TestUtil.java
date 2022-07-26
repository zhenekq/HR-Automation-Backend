package by.mifort.automation.hr.dev.util;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.type.AttributeTypes;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private TestUtil() {
    }

    public static List<CandidateAttributes> emptyList(Candidate candidate) {
        return new ArrayList<>(List.of(
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(1), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(2), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(3), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(4), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(5), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(6), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(7), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(8), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(9), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(10), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(11), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(12), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(13), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(14), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(15), Boolean.FALSE)
        ));
    }

}
