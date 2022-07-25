package by.mifort.automation.hr.dev.merge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateMergeRepository extends JpaRepository<CandidateMerge, Integer> {
}
