package by.mifort.automation.hr.dev.duplicate;

import by.mifort.automation.hr.dev.type.AttributeTypesRepository;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Factory for putting ways finding duplicates and their identifiers
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see AttributeTypesRepository
 */
@Component
public class DuplicatesStrategyFactory {

    private final Map<DuplicatesStrategyName, DuplicatesStrategy> duplicatesStrategies = new EnumMap<>(DuplicatesStrategyName.class);

    public DuplicatesStrategyFactory(Set<DuplicatesStrategy> duplicatesStrategies) {
        fillStrategies(duplicatesStrategies);
    }

    public DuplicatesStrategy findStrategy(DuplicatesStrategyName strategyName) {
        isStrategyExists(strategyName);
        return duplicatesStrategies.get(strategyName);
    }

    private void fillStrategies(Set<DuplicatesStrategy> strategies) {
        strategies.forEach(
                strategy -> duplicatesStrategies.put(strategy.getStrategyName(), strategy)
        );
    }

    private void isStrategyExists(DuplicatesStrategyName strategyName) {
        boolean isExists = false;
        for (DuplicatesStrategyName strategy : DuplicatesStrategyName.values()) {
            if (strategy.equals(strategyName)) {
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            throw new IllegalArgumentException("Strategy with name: " + strategyName.name() + " not exists!");
        }
    }

}
