package by.mifort.automation.hr.dev.duplicate;

import by.mifort.automation.hr.dev.candidate.Candidate;

import java.util.List;

/**
 * Strategy pattern interface for different ways to find duplicates between candidates
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see DuplicatesStrategyFactory
 */
public interface DuplicatesStrategy {

    List<List<Candidate>> getDuplicates();

    DuplicatesStrategyName getStrategyName();
}
