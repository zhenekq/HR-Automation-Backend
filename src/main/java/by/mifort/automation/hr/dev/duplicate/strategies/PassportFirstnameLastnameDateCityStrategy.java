package by.mifort.automation.hr.dev.duplicate.strategies;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateService;
import by.mifort.automation.hr.dev.duplicate.DuplicatesStrategy;
import by.mifort.automation.hr.dev.duplicate.DuplicatesStrategyName;
import by.mifort.automation.hr.dev.duplicate.comparator.PassportFirstnameLastnameDateCityComparator;
import by.mifort.automation.hr.dev.duplicate.separate.SeparateService;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassportFirstnameLastnameDateCityStrategy implements DuplicatesStrategy {

    private final CandidateService candidateService;
    private final SeparateService separateService;

    public PassportFirstnameLastnameDateCityStrategy(CandidateService candidateService, SeparateService separateService) {
        this.candidateService = candidateService;
        this.separateService = separateService;
    }

    @Override
    public List<List<Candidate>> getDuplicates() {
        List<Candidate> candidates = new ArrayList<>(candidateService.getAll(new FilterDto(1, Integer.MAX_VALUE)));
        return separateService.separateBySeveralAttributes(candidates, new PassportFirstnameLastnameDateCityComparator());
    }

    @Override
    public DuplicatesStrategyName getStrategyName() {
        return DuplicatesStrategyName.FIRSTNAME_LASTNAME_CITY_DOB_PASSPORT;
    }
}
