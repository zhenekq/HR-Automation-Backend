package by.mifort.automation.hr.dev.duplicate.strategies;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateService;
import by.mifort.automation.hr.dev.duplicate.DuplicatesStrategy;
import by.mifort.automation.hr.dev.duplicate.DuplicatesStrategyName;
import by.mifort.automation.hr.dev.duplicate.comparator.KeyAttributeComparator;
import by.mifort.automation.hr.dev.duplicate.separate.SeparateService;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyAttributeStrategy implements DuplicatesStrategy {

    private final CandidateService service;
    private final SeparateService separateService;

    public KeyAttributeStrategy(CandidateService service, SeparateService separateService) {
        this.service = service;
        this.separateService = separateService;
    }

    @Override
    public List<List<Candidate>> getDuplicates() {
        List<Candidate> candidates = new ArrayList<>(service.getAll(new FilterDto(1, Integer.MAX_VALUE)));
        return separateService.separateBySeveralAttributes(candidates, new KeyAttributeComparator());
    }

    @Override
    public DuplicatesStrategyName getStrategyName() {
        return DuplicatesStrategyName.KEY_STRATEGY;
    }

}
