package by.mifort.automation.hr.dev.service.duplicates.strategies;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategy;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyName;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResultStrategy implements DuplicatesStrategy {

    private final CompanyCityFirstnameLastnameStrategy companyCityFirstnameLastnameStrategy;
    private final DobFirstnameLastnameCityStrategy dobFirstnameLastnameCityStrategy;
    private final KeyAttributeStrategy keyAttributeStrategy;
    private final PassportFirstnameLastnameDateCityStrategy passportFirstnameLastnameDateCityStrategy;

    public ResultStrategy(CompanyCityFirstnameLastnameStrategy companyCityFirstnameLastnameStrategy, DobFirstnameLastnameCityStrategy dobFirstnameLastnameCityStrategy, KeyAttributeStrategy keyAttributeStrategy, PassportFirstnameLastnameDateCityStrategy passportFirstnameLastnameDateCityStrategy) {
        this.companyCityFirstnameLastnameStrategy = companyCityFirstnameLastnameStrategy;
        this.dobFirstnameLastnameCityStrategy = dobFirstnameLastnameCityStrategy;
        this.keyAttributeStrategy = keyAttributeStrategy;
        this.passportFirstnameLastnameDateCityStrategy = passportFirstnameLastnameDateCityStrategy;
    }

    @Override
    public List<List<Candidate>> getDuplicates() {
        Set<List<Candidate>> result = new HashSet<>();
        result.addAll(companyCityFirstnameLastnameStrategy.getDuplicates());
        result.addAll(dobFirstnameLastnameCityStrategy.getDuplicates());
        result.addAll(keyAttributeStrategy.getDuplicates());
        result.addAll(passportFirstnameLastnameDateCityStrategy.getDuplicates());
        return new ArrayList<>(result);
    }

    @Override
    public DuplicatesStrategyName getStrategyName() {
        return DuplicatesStrategyName.DUPLICATES;
    }
}
