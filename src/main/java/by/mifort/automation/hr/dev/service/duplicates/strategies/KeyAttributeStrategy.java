package by.mifort.automation.hr.dev.service.duplicates.strategies;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.repository.CandidateAttributesRepository;
import by.mifort.automation.hr.dev.service.CandidateService;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategy;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyName;
import by.mifort.automation.hr.dev.service.duplicates.comparator.KeyAttributeComparator;
import by.mifort.automation.hr.dev.service.duplicates.comparator.SingleAttributeComparator;
import by.mifort.automation.hr.dev.service.duplicates.separate.SeparateService;
import liquibase.pro.packaged.F;
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
